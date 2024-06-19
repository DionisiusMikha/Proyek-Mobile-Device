const express = require("express");
const router = express.Router();

const { topup } = require("../controllers/topupController");
const { authentication } = require("../controllers/userController");

router.post("/topup", authentication, topup);

module.exports = router;
