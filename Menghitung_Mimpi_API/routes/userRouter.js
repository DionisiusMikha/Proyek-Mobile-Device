const express = require("express");
const router = express.Router();

const rateLimit = require("express-rate-limit");

const {
  register,
  login,
  authentication,
  getName,
  forgotPassword,
  cekOtp,
  resetPassword,
} = require("../controllers/userController");

const limiter = rateLimit({
  windowMs: 10 * 60 * 1000,
  max: 3,
  message: {
    message: "Too many requests, please try again later",
  },
});

router.post("/forgot-password", limiter, forgotPassword);
router.get("/get-name", authentication, getName);
router.post("/register", register);
router.post("/login", login);
router.post("/cek-otp", cekOtp);
router.post("/reset-password", resetPassword);

module.exports = router;
