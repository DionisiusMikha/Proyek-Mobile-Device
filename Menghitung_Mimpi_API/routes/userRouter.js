const express = require("express");
const router = express.Router();

const { register, login, authentication, getName, saveInvest } = require("../controllers/userController");

router.get("/get-name", authentication, getName);
router.post("/register", register);
router.post("/login", login);
router.post("/save-invest", authentication, saveInvest);

module.exports = router;
