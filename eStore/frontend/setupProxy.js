const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function (app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://34.175.80.212:8080',  // Replace with your backend server URL
      changeOrigin: true,
    })
  );
};
