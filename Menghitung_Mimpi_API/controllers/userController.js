const Users = require("../models/UserModel");
const jwt = require("jsonwebtoken");
const PasswordKey = require("../models/PasswordKey");
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
      return res.status(403).json({ message: "Token is not valid" });
    }
    req.user = user;
    next();
  });
};

module.exports = { register, login, authentication };
