const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const PasswordKey = conn.define(
  "passwordkey",
  {
    id_pwkey: {
      type: DataTypes.STRING,
      primaryKey: true,
    },
    id_user: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    password_key: {
      type: DataTypes.STRING,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  }
);

module.exports = PasswordKey;
