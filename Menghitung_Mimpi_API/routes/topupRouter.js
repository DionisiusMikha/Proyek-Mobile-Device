const express = require("express");
const router = express.Router();

const { topup, changeStatusTopup } = require("../controllers/topupController");
const { authentication } = require("../controllers/userController");

router.post("/topup", authentication, topup);
router.put("/updateTopup", authentication, changeStatusTopup);

module.exports = router;
