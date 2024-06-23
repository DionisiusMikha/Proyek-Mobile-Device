const Topup = require("../models/Topup");
const Users = require("../models/UserModel");

const Joi = require("joi").extend(require("@joi/date"));

const formatRupiah = (angka) => {
  let reverse = angka.toString().split("").reverse().join(""),
    ribuan = reverse.match(/\d{1,3}/g);
  ribuan = ribuan.join(".").split("").reverse().join("");
  return `Rp. ${ribuan},00`;
};

const topup = async (req, res) => {
  const { id_user, jumlah_topup } = req.body;

  try {
    const schema = Joi.object({
      id_user: Joi.string().required(),
      jumlah_topup: Joi.number().required(),
    });

    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({ message: error.details[0].message });
    }

    const user = await Users.findOne({ where: { id_user } });
    if (!user) {
      return res.status(404).json({ message: "User not found" });
    }

    const id_topup = `TP-${("000" + ((await Topup.count()) + 1)).slice(-3)}`;

    const newTopup = await Topup.create({
      id_topup: id_topup,
      jumlah_topup,
      id_user,
      status_topup: 0,
      waktu_topup: new Date(),
    });

    const email = user.email;

    res.status(201).json({
      message: "Topup success",
      data: {
        id_topup: newTopup.id_topup,
        jumlah_topup: formatRupiah(newTopup.jumlah_topup),
        email: email,
        waktu_topup: newTopup.waktu_topup,
      },
    });
  } catch (err) {
    console.error(err.message);
    res.status(500).json({ message: "Internal server error" });
  }
};

const changeStatusTopup = async (req, res) => {
  const { id_topup } = req.body;

  try {
    const schema = Joi.object({
      id_topup: Joi.string().required(),
    });

    const { error } = schema.validate(req.body);
    if (error) {
      return res.status(400).json({ message: error.details[0].message });
    }

    const topup = await Topup.findOne({ where: { id_topup } });
    if (!topup) {
      return res.status(404).json({ message: "Topup not found" });
    }

    await Topup.update(
      { status_topup: 1 },
      {
        where: { id_topup },
      }
    );

    res.status(200).json({ message: "Topup status updated" });
  } catch (err) {
    console.error(err.message);
    res.status(500).json({ message: "Internal server error" });
  }
};

module.exports = { topup, changeStatusTopup };
