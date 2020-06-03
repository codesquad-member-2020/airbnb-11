module.exports = function (env) {
  console.log(env, env.mode);
  return require(`./webpack.${env.mode}.js`);
};