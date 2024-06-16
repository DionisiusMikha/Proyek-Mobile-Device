const express = require("express");
const app = express();
const env = require("./config/env.config")();

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

const apiRouter = require("./routes/indexRouter");
const conn = require("./database/connection");

app.use("/api", apiRouter);

app.use((req, res) => {
  res.status(404).json({ message: "Not Found" });
});

const initApp = async () => {
  try {
    await conn.authenticate();
    console.log("\x1b[32mDatabase connected!");
  } catch (err) {
    console.error("\x1b[31mDatabase connection failed: ", err);
  }
};

const port = env.PORT || 3000;
app.listen(port, () => {
  console.clear();
  console.log(`Listening on port ${port}`);
  initApp();
});
