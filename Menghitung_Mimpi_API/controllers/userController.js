const { Op } = require("sequelize")
const Users = require("../models/UserModel");
const Topup = require("../models/Topup");
const Invest = require("../models/Investasi");
const DanaDarurat = require("../models/DanaDarurat");
const PasswordKey = require("../models/PasswordKey");
const Nikah = require("../models/Nikah");
const jwt = require("jsonwebtoken");
const bcrypt = require("bcrypt");
const Joi = require("joi").extend(require("@joi/date"));
const nodemailer = require("nodemailer");
const dotenv = require("dotenv");

const sendEmail = (email, subject, text, callback) => {
  const transporter = nodemailer.createTransport({
    service: "gmail",
    host: "smtp.gmail.com",
    auth: {
      user: process.env.GMAIL,
      pass: process.env.PW_GMAIL,
    },
  });

  const mailOptions = {
    from: process.env.GMAIL,
    to: email,
    subject: subject,
    text: text,
  };

  transporter.sendMail(mailOptions, (err, info) => {
    if (err) {
      console.log(err);
      callback(false);
    } else {
      console.log("Email sent to: " + email + " " + info.response);
      callback(true);
    }
  });
};

const register = async (req, res) => {
  const { full_name, dob, phone_number, email, password, confirm_password } =
    req.body;
  try {
    const schema = Joi.object({
      full_name: Joi.string().required(),
      dob: Joi.date().format("DD-MM-YYYY").required().label("Date of Birth"),
      phone_number: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().min(8).required(),
      confirm_password: Joi.string().required(),
    });

    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({ message: error.details[0].message });
    }
    if (password !== confirm_password) {
      return res.status(400).json({ message: "Password doesn't match" });
    }

    const emailIsExist = await Users.findOne({ where: { email } });
    if (emailIsExist) {
      return res.status(400).json({ message: "Email already registered" });
    }
    const phoneIsExist = await Users.findOne({ where: { phone_number } });
    if (phoneIsExist) {
      return res
        .status(400)
        .json({ message: "Phone number already registered" });
    }

    const ctr = await Users.count();
    let id_user = `USR-${("000" + (ctr + 1)).slice(-3)}`;
    const hashedPassword = await bcrypt.hash(password, 10);
    const saldo = 0;
    let dobFormatted = dob.split("-").reverse().join("-");

    sendEmail(
      email,
      "Registration success",
      "Thank you for registering",
      (status) => {
        console.log("Email sent status: " + status);
      }
    );

    const user = await Users.create({
      id_user,
      full_name,
      dob: dobFormatted,
      phone_number,
      email,
      password: hashedPassword,
      saldo,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    res.status(201).json({
      message: "User created successfully",
    });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

const login = async (req, res) => {
  const { email, password } = req.body;

  try {
    if (!email || !password) {
      return res.status(400).json({ message: "Please fill all fields" });
    }

    const user = await Users.findOne({ where: { email } });
    if (!user) {
      return res.status(400).json({ message: "Email not registered" });
    }

    const validPass = await bcrypt.compare(password, user.password);
    if (!validPass) {
      return res.status(400).json({ message: "Invalid password" });
    }

    if (!process.env.JWT_SECRET) {
      throw new Error("JWT_SECRET environment variable not set");
    }

    const token = jwt.sign({ email: user.email }, process.env.JWT_SECRET);

    res.status(200).json({ Message: "Login success", token: token });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

const getName = async (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  const bulan = new Date().getMonth() + 1
  const tahun = new Date().getFullYear()

  const tabungBulanan = await Topup.findAll({
    where:{
      id_user: user.id_user,
      status_topup: 1,
      waktu_topup: {
        [Op.between]: [`${tahun}-${bulan}-01`, `${tahun}-${bulan}-31`]
      }
    }
  })

  let totaltabungan = 0
  tabungBulanan.forEach(element => {
    totaltabungan += element.jumlah_topup
  });

  return res.status(200).json({ 
    name: user.full_name,
    saldo: user.saldo,
    tabungan: totaltabungan
   });
};

const forgotPassword = async (req, res) => {
  const { email } = req.body;

  try {
    if (!email) {
      return res.status(400).json({ message: "Please fill all fields" });
    }

    const otp = Math.floor(100000 + Math.random() * 900000);
    const user = await Users.findOne({ where: { email } });
    if (!user) {
      return res.status(404).json({ message: "User is not registered" });
    }
    const passwordKey = await PasswordKey.create({
      id_pwkey: `PWK-${("000" + ((await PasswordKey.count()) + 1)).slice(-3)}`,
      id_user_FK: user.id_user,
      password_key: otp,
      status: 0,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    sendEmail(
      email,
      "OTP to reset password",
      `Your OTP to reset password is ${otp}`,
      (status) => {
        console.log("Email sent status: " + status);
      }
    );

    return res.status(200).json({ message: "OTP sent to " + email });
  } catch (error) {
    console.log(error);
    return res.status(500).json({ message: error.message });
  }
};

const cekOtp = async (req, res) => {
  const { email, otp } = req.body;

  try {
    if (!email || !otp) {
      return res.status(400).json({ message: "Please fill all fields" });
    }

    const user = await Users.findOne({ where: { email } });
    if (!user) {
      return res.status(404).json({ message: "User is not registered" });
    }

    const passwordKey = await PasswordKey.findOne({
      where: { id_user_FK: user.id_user, password_key: otp },
    });

    if (!passwordKey) {
      return res.status(400).json({ message: "OTP is invalid" });
    }

    passwordKey.status = 1;
    await passwordKey.save();

    return res.status(200).json({ message: "OTP is valid" });
  } catch (error) {
    return res.status(500).json({ message: error.message });
  }
};

const resetPassword = async (req, res) => {
  const { email, password, confirm_password } = req.body;

  try {
    if (!email || !password || !confirm_password) {
      return res.status(400).json({ message: "Please fill all fields" });
    }

    if (password !== confirm_password) {
      return res.status(400).json({ message: "Password doesn't match" });
    }

    const user = await Users.findOne({ where: { email } });
    if (!user) {
      return res.status(404).json({ message: "User is not registered" });
    }

    const hashedPassword = await bcrypt.hash(password, 10);

    user.password = hashedPassword;
    await user.save();

    return res.status(200).json({ message: "Password reset successful" });
  } catch (error) {
    return res.status(500).json({ message: error.message });
  }
};

const saveInvest = async (req, res) => {
  const {
    target,
    waktu,
    uang_sekarang,
    invest,
    presentase,
    final,
    type,
    status,
  } = req.body;
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const result = await Invest.create({
      id_user: user.id_user,
      target,
      waktu,
      uang_sekarang,
      invest,
      presentase,
      final,
      type,
      status,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    res.status(201).json({
      message: "Saving created successfully",
    });
  } catch (error) {
    res.status(500).json({ message: err.message });
  }
};

const saveDarurat = async (req, res) => {
  const {
    dana_darurat,
    dana_sekarang,
    lama,
    invest,
    presentase,
    total,
    status,
  } = req.body;
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const result = await DanaDarurat.create({
      id_user: user.id_user,
      dana_darurat,
      dana_sekarang,
      lama,
      invest,
      presentase,
      total,
      status,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    res.status(201).json({
      message: "Saving created successfully",
    });
  } catch (error) {
    res.status(500).json({ message: err.message });
  }
};

const saveNikah = async (req, res) => {
  const {
    biaya_final,
    uang_sekarang,
    invest,
    presentase,
    waktu,
    total_final,
    status,
  } = req.body;
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const result = await Nikah.create({
      id_user: user.id_user,
      biaya_final,
      uang_sekarang,
      invest,
      presentase,
      waktu,
      total_final,
      status,
      createdAt: new Date(),
      updatedAt: new Date(),
    });

    res.status(201).json({
      message: "Saving created successfully",
    });
  } catch (error) {
    res.status(500).json({ message: err.message });
  }
};

const getInvestasi = async (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const investasi = await Invest.findAll({ 
      where: { id_user: user.id_user },
      attributes: ["target", "waktu", "uang_sekarang", "invest", "presentase", "final", "type", "status", "createdAt"],
      order: [["createdAt", "ASC"]]
    })

    return res.status(200).json(investasi)
  } catch (error) {
    return res.status(500).json({message: error.message})
  }
}

const getDanaDarurat = async (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const dana_darurat = await DanaDarurat.findAll({ 
      where: { id_user: user.id_user },
      attributes: ["dana_darurat", "dana_sekarang", "lama", "invest", "presentase", "total", "status", "createdAt"],
      order: [["createdAt", "ASC"]]
    })

    return res.status(200).json({dana_darurat})
  } catch (error) {
    return res.status(500).json({message: error.message})
  }
}

const getNikah = async (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const nikah = await Nikah.findAll({ 
      where: { id_user: user.id_user },
      attributes: ["biaya_final", "uang_sekarang", "invest", "presentase", "waktu", "total_final", "status", "createdAt"],
      order: [["createdAt", "ASC"]]
    })
    return res.status(200).json({nikah})
  } catch (error) {
    return res.status(500).json({message: error.message})
  }
}

const authentication = async (req, res, next) => {
  // bearer token
  const authHeader = req.headers["authorization"];
  const token = authHeader && authHeader.split(" ")[1];
  if (!token) {
    return res.status(401).json({ message: "Access denied" });
  }

  if (!process.env.JWT_SECRET) {
    throw new Error("JWT_SECRET environment variable not set");
  }

  jwt.verify(token, process.env.JWT_SECRET, (err, user) => {
    if (err) {
      return res.status(403).json({ name: "Token is not valid" });
    }
    req.user = user;
    next();
  });
};

module.exports = {
  register,
  login,
  authentication,
  getName,
  saveInvest,
  saveDarurat,
  saveNikah,
  getInvestasi,
  getDanaDarurat,
  getNikah,
  forgotPassword,
  cekOtp,
  resetPassword,
};
