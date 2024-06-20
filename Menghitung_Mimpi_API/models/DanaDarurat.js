const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const DanaDarurat = conn.define(
  "dana_darurat",
  {
    id_user: {
      type: DataTypes.STRING(10),
      primaryKey: true,
    },
    dana_darurat: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    dana_sekarang: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    lama: {
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
    total: {
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

module.exports = DanaDarurat;
