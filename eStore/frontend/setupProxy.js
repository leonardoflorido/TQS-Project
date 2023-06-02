const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    createProxyMiddleware({
      target: 'http://34.175.80.212:8080',
      pathRewrite: {
        '^/api': '', // Remove the '/api' prefix from the request
      },
      changeOrigin: true,
    })
  );
};
