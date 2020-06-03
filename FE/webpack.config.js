module.exports = function (env) {
  return require(`./webpack.${env.mode}.js`);
};