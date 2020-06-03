const path = require("path");
const merge = require("webpack-merge");
const Dotenv = require("dotenv-webpack");
const common = require("./webpack.common.js");

module.exports = merge(common, {
  mode: "production",
  output: {
    filename: "bundle.js",
    path: path.resolve(__dirname, "dist"),
    publicPath: './'
  },
  plugins: [
    new Dotenv({
      path: path.resolve(__dirname, "./.env.production"),
    }),
  ],
});