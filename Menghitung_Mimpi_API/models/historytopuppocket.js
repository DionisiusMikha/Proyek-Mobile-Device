const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const historytopuppocket = conn.define(
  "historytopuppocket",
  {
    id: {
      type: DataTypes.INTEGER,
      primaryKey: true,
      autoIncrement: true,
    },
    id_user: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    id_pocket: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    jumlah_topup: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  }
);

module.exports = historytopuppocket;
