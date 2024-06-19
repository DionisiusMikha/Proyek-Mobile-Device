const express = require("express");
const router = express.Router();

const {
  createPocket,
  getAllPockets,
  deletePocket,
  getPocket,
  updatePocket,
} = require("../controllers/pocketController");
const { authentication } = require("../controllers/userController");

router.post("/createpocket", authentication, createPocket);
router.get("/pockets", authentication, getAllPockets);
router.get("/:id_pocket", authentication, getPocket);
router.put("/updatepocket/:id_pocket", authentication, updatePocket);
router.delete("/deletepocket/:id_pocket", authentication, deletePocket);

module.exports = router;
