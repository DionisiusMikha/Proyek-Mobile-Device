// id_user, full_name, dob, phone_number, email, password

const { DataTypes } = require("sequelize");
const conn = require("../database/connection");

const Users = conn.define(
  "users",
  {
    id_user: {
      type: DataTypes.STRING,
      primaryKey: true,
    },
    full_name: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    dob: {
      type: DataTypes.DATE,
      allowNull: false,
    },
    phone_number: {
      type: DataTypes.STRING,
      allowNull: false,
    },
    email: {
      type: DataTypes.STRING,
      allowNull: false,
      unique: true,
    },
    password: {
      type: DataTypes.STRING,
      allowNull: false,
    },
  },
  {
    freezeTableName: true,
  }
);

module.exports = Users;
