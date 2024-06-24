const express = require("express");
const router = express.Router();

const { addTopupHistory } = require("../controllers/topupHistory");
const { authentication } = require("../controllers/userController");

router.post("/addtopuphistory", authentication, addTopupHistory);

module.exports = router;
