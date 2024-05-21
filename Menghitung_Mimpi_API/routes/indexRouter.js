const express = require("express");
const apiRouter = express.Router();

const userRouter = require("./userRouter");

apiRouter.use("/users", userRouter);

module.exports = apiRouter;