require("dotenv/config");

const dictionary = {
  HOST: process.env.HOST,
  BCRYPT_KEY: process.env.BCRYPT_KEY,
  PORT: process.env.PORT,
};

module.exports = () => {
  return dictionary;
};
