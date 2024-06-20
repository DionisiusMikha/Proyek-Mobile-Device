const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const Investasi = conn.define(
  "investasi",
  {
    id_user: {
      type: DataTypes.STRING(10),
      primaryKey: true,
    },
    target: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    waktu: {
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
    final: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    type: {
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
    paranoid: true,
  }
);

module.exports = Investasi;
