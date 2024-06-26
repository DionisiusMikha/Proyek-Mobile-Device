const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const PasswordKey = conn.define(
  "passwordkey",
  {
    id_pwkey: {
      type: DataTypes.STRING,
      primaryKey: true,
    },
    id_user_FK: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    password_key: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    status: {
      type: DataTypes.INTEGER, // 0 = not used, 1 = used
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
    paranoid: true,
  }
);

module.exports = PasswordKey;
