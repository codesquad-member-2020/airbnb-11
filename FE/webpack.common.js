const path = require("path");
const webpack = require("webpack");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
  entry:  ['@babel/polyfill', './src/index.jsx'],
  performance: {
    maxEntrypointSize: 1024000,
    maxAssetSize: 1024000,
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        use: ['babel-loader'],
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader', 'sass-loader'],
      },
      {
        test: /\.(ico|png|jpg|jpeg|gif|svg|woff|woff2|ttf|eot)(\?v=[0-9]\.[0-9]\.[0-9])?$/,
        loader: 'url-loader',
        options: {
          name: '[hash].[ext]',
          limit: 10000,
        },
      },
    ],
  },
  resolve: {
    extensions: [".js", ".jsx"],
    alias: {
      Components: path.resolve(__dirname, "src/components/"),
      Constants: path.resolve(__dirname, "src/constants/"),
      Actions: path.resolve(__dirname, "src/statement/actions"),
      Reducers: path.resolve(__dirname, "src/statement/reducers"),
      ActionNames: path.resolve(__dirname, "src/statement/actionNames/"),
      Routers: path.resolve(__dirname, "src/routers/"),
      MockData: path.resolve(__dirname, "src/mockData/"),
      Library: path.resolve(__dirname, "lib/"),
      CustomHooks: path.resolve(__dirname, "src/customHooks/"),
    },
  },
  plugins: [
    new webpack.HotModuleReplacementPlugin(),
    new HtmlWebpackPlugin({
      filename: "index.html",
      template: "public/index.html",
    }),
  ],
};