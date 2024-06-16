const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const Topup = conn.define(
  "topup",
  {
    id_topup: {
      type: DataTypes.STRING,
      primaryKey: true,
    },
    jumlah_topup: {
      type: DataTypes.INTEGER,
      allowNull: false,
    },
    id_user: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    status_topup: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    waktu_topup: {
      type: DataTypes.DATE,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
    timestamps: false,
  }
);

module.exports = Topup;
