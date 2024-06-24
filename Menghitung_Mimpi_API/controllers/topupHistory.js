const Topup = require("../models/Topup");
const Users = require("../models/UserModel");
const TopupHistory = require("../models/historytopuppocket");

const Joi = require("joi").extend(require("@joi/date"));

const addTopupHistory = async (req, res) => {
  const { id_user, id_pocket, jumlah_topup } = req.body;

  try {
    const schema = Joi.object({
      id_user: Joi.string().required(),
      id_pocket: Joi.string().required(),
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

    const pocket = await Topup.findOne({ where: { id_pocket } });
    if (!pocket) {
      return res.status(404).json({ message: "Pocket not found" });
    }

    const id_history = `HST-${(
      "000" +
      ((await TopupHistory.count()) + 1)
    ).slice(-3)}`;

    const newTopupHistory = await TopupHistory.create({
      id_history: id_history,
      id_user,
      id_pocket,
      jumlah_topup,
      createdAt: new Date(),
      updatedAt: new Date(),
    });
    res.status(201).send(newTopupHistory);
  } catch (err) {
    console.error(err.message);
    res.status(500).json({ message: "Internal server error" });
  }
};

module.exports = { addTopupHistory };
