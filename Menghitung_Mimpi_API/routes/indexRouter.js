const express = require("express");
const apiRouter = express.Router();

const userRouter = require("./userRouter");
const topupRouter = require("./topupRouter");
const pocketRouter = require("./pocketRouter");
const topupHistoryRouter = require("./topupHistory");

apiRouter.use("/users", userRouter);
apiRouter.use("/topup", topupRouter);
apiRouter.use("/pocket", pocketRouter);
apiRouter.use("/historytopup", topupHistoryRouter);

module.exports = apiRouter;
