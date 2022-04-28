const express = require("express");
const app = express();
const http=require("http");
const path = require('path');

const port=3000;
app.listen(port, () => {
  console.log("Application started and Listening on port 3000");
});

// serve your css/js as static
app.use(express.static('./js'));
app.use(express.static('./html'));
app.use(express.static(__dirname));

app.get("/", (req, res) => {
  res.sendFile(path.resolve(__dirname, "./html/index.html"));
});

app.get("/index", (req, res) => {
  res.sendFile(path.resolve(__dirname, "./html/index.html"));
});

app.get("/manage_categories", (req, res) => {
  res.sendFile(path.resolve(__dirname, "./html/manage_categories.html"));
});