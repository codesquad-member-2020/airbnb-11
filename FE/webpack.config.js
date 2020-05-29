const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const { CleanWebpackPlugin } = require('clean-webpack-plugin')

module.exports = env => ({
  mode: env.mode,
  entry:  ['@babel/polyfill', './src/index.jsx'],
  output: {
    path: path.resolve(__dirname, 'dist'),
    filename: '[name].[hash].js',
    publicPath: '/'
  },
  devServer: {
    contentBase: path.resolve(__dirname, 'dist'),
    historyApiFallback: true,
    open: true,
  },
  resolve: {
    extensions: ['.js', '.jsx'],
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
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
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
  plugins: [
    new HtmlWebpackPlugin({
      template: 'public/index.html',
			filename: 'index.html'
    }),
		new CleanWebpackPlugin() 
  ],
});