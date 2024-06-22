const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const Nikah = conn.define(
  "nikah",
  {
    id_user: {
      type: DataTypes.STRING(10),
      primaryKey: true,
    },
    biaya_final: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    uang_sekarang: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    invest: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    presentase: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    waktu: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    total_final: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    status: {
      type: DataTypes.BOOLEAN,
      allowNull: false,
    }
  },
  {
    freezeTableName: true,
    paranoid: true
  }
);

module.exports = Nikah;
