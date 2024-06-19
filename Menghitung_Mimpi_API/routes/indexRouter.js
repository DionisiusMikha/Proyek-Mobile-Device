const express = require("express");
const apiRouter = express.Router();

const userRouter = require("./userRouter");
const topupRouter = require("./topupRouter");

apiRouter.use("/users", userRouter);
apiRouter.use("/topup", topupRouter);

module.exports = apiRouter;
