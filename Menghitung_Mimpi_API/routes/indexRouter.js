const express = require("express");
const apiRouter = express.Router();

const userRouter = require("./userRouter");
const topupRouter = require("./topupRouter");
const pocketRouter = require("./pocketRouter");

apiRouter.use("/users", userRouter);
apiRouter.use("/topup", topupRouter);
apiRouter.use("/pocket", pocketRouter);

module.exports = apiRouter;
