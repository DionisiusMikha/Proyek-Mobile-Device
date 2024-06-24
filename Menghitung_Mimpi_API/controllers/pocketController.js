const Pockets = require("../models/PocketModel");
const Users = require("../models/UserModel");
const Joi = require("joi").extend(require("@joi/date"));
const jwt = require("jsonwebtoken");

const formatRupiah = (angka) => {
  let reverse = angka.toString().split("").reverse().join(""),
    ribuan = reverse.match(/\d{1,3}/g);
  ribuan = ribuan.join(".").split("").reverse().join("");
  return `Rp. ${ribuan},00`;
};

const formatTanggal = (tanggal) => {
  let date = new Date(tanggal);
  let day = date.getDate();
  let month = date.getMonth() + 1;
  let year = date.getFullYear();
  let hour = date.getHours() < 10 ? `0${date.getHours()}` : date.getHours();
  let minute =
    date.getMinutes() < 10 ? `0${date.getMinutes()}` : date.getMinutes();
  let second =
    date.getSeconds() < 10 ? `0${date.getSeconds()}` : date.getSeconds();
  return `${day}-${month}-${year} ${hour}:${minute}:${second}`;
};

const createPocket = async (req, res) => {
  const { nama_pocket, saldo_pocket } = req.body;

  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const schema = Joi.object({
      nama_pocket: Joi.string().required(),
      saldo_pocket: Joi.number().required(),
    });

    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({ message: error.details[0].message });
    }

    const id_pocket = `PK-${("000" + ((await Pockets.count()) + 1)).slice(-3)}`;

    const newPocket = await Pockets.create({
      id_pocket: id_pocket,
      nama_pocket,
      saldo_pocket,
      id_user: user.id_user,
      createdAt: new Date(),
    });

    res.status(201).json({
      message: "Pocket created",
      data: {
        id_pocket: newPocket.id_pocket,
        nama_pocket: newPocket.nama_pocket,
        saldo_pocket: formatRupiah(newPocket.saldo_pocket),
      },
    });
  } catch (err) {
    console.error(err.message);
    return res.status(500).json({ message: "Internal server error" });
  }
};

const getAllPockets = async (req, res) => {
  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    let pockets = await Pockets.findAll({
      where: { id_user: user.id_user, deletedAt: null },
    });

    pockets = pockets.map((pocket) => {
      return {
        id_pocket: pocket.id_pocket,
        nama_pocket: pocket.nama_pocket,
        saldo_pocket: formatRupiah(pocket.saldo_pocket),
        createdAt: formatTanggal(pocket.createdAt),
      };
    });

    res.status(200).send(pockets);
  } catch (err) {
    console.error(err.message);
    return res.status(500).json({ message: "Internal server error" });
  }
};

const getPocket = async (req, res) => {
  const { id_pocket } = req.params;

  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const pocket = await Pockets.findOne({
      where: { id_pocket, id_user: user.id_user },
    });

    if (!pocket || pocket.deletedAt) {
      return res.status(404).json({ message: "Pocket not found" });
    }

    res.status(200).json({
      message: "Success",
      data: {
        id_pocket: pocket.id_pocket,
        nama_pocket: pocket.nama_pocket,
        saldo_pocket: formatRupiah(pocket.saldo_pocket),
        createdAt: formatTanggal(pocket.createdAt),
      },
    });
  } catch (err) {
    console.error(err.message);
    return res.status(500).json({ message: "Internal server error" });
  }
};

const updatePocket = async (req, res) => {
  const { id_pocket } = req.params;
  const { nama_pocket, saldo_pocket } = req.body;

  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const pocket = await Pockets.findOne({
      where: { id_pocket, id_user: user.id_user },
    });

    if (!pocket) {
      return res.status(404).json({ message: "Pocket not found" });
    }

    const schema = Joi.object({
      nama_pocket: Joi.string().required(),
      saldo_pocket: Joi.number().required(),
    });

    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({ message: error.details[0].message });
    }

    pocket.nama_pocket = nama_pocket;
    pocket.saldo_pocket = saldo_pocket;

    await pocket.save();

    res.status(200).json({
      message: "Pocket updated",
      data: {
        id_pocket: pocket.id_pocket,
        nama_pocket: pocket.nama_pocket,
        saldo_pocket: formatRupiah(pocket.saldo_pocket),
      },
    });
  } catch (err) {
    console.error(err.message);
    return res.status(500).json({ message: "Internal server error" });
  }
};

const deletePocket = async (req, res) => {
  const { id_pocket } = req.params;

  const token = req.headers.authorization.split(" ")[1];
  const decoded = jwt.verify(token, process.env.JWT_SECRET);

  const email = decoded.email;

  const user = await Users.findOne({ where: { email } });
  if (!user) {
    return res.status(404).json({ message: "User is not registered" });
  }

  try {
    const pocket = await Pockets.findOne({
      where: { id_pocket, id_user: user.id_user },
    });

    if (!pocket) {
      return res.status(404).json({ message: "Pocket not found" });
    }

    if (pocket.saldo_pocket > 0) {
      user.saldo += pocket.saldo_pocket;
      await user.save();
    }

    pocket.destroy();

    res.status(200).json({
      message: "Pocket deleted",
      data: {
        id_pocket: pocket.id_pocket,
        nama_pocket: pocket.nama_pocket,
        saldo_pocket: formatRupiah(pocket.saldo_pocket),
      },
    });
  } catch (err) {
    console.error(err.message);
    return res.status(500).json({ message: "Internal server error" });
  }
};

module.exports = {
  createPocket,
  getAllPockets,
  getPocket,
  updatePocket,
  deletePocket,
};
