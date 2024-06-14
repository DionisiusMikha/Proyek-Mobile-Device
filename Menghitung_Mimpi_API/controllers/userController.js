const Users = require("../models/UserModel");
const bcrypt = require("bcrypt");
const Joi = require("joi");

const register = async (req, res) => {
  const { full_name, dob, phone_number, email, password, confirm_password } =
    req.body;
  try {
    const schema = Joi.object({
      full_name: Joi.string().required(),
      dob: Joi.string().pattern(
        new RegExp("^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/[0-9]{4}$")
      ),
      phone_number: Joi.string().required(),
      email: Joi.string().email().required(),
      password: Joi.string().min(8).required(),
      confirm_password: Joi.ref("password"),
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
    const user = await Users.create({
      id_user,
      full_name,
      dob,
      phone_number,
      email,
      password: hashedPassword,
      createdAt: new Date(),
      updatedAt: new Date(),
    });
    res.status(201).json({
      message: "User created successfully",
      id_user: user.id_user,
    });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

const login = async (req, res) => {
  const { email, password } = req.body;
  try {
    if (!email || !password) {
      return res
        .status(400)
        .json({ message: "Please fill all required fields" });
    }

    const user = await Users.findOne({ where: { email } });
    if (!user) {
      return res.status(400).json({ message: "Email not registered" });
    }
    const validPassword = await bcrypt.compare(password, user.password);
    if (!validPassword) {
      return res.status(400).json({ message: "Invalid password" });
    }

    const token = jwt.sign({ id: user.id }, process.env.JWT_SECRET, {
      expiresIn: "1h",
    });

    res.status(200).json({
      message: "Login success",
      token,
      user: {
        id_user: user.id_user,
        full_name: user.full_name,
        email: user.email,
        phone_number: user.phone_number,
      },
    });
  } catch (err) {
    res.status(500).json({ message: err.message });
  }
};

module.exports = { register, login };
