const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const Pocket = conn.define(
  "pocket",
  {
    id_pocket: {
      type: DataTypes.STRING,
      primaryKey: true,
    },
    nama_pocket: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    saldo_pocket: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    id_user: {
      type: DataTypes.STRING,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
    paranoid: true,
  }
);

module.exports = Pocket;
