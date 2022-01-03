module.exports = {
  publicPath: './',
  lintOnSave: true,
  configureWebpack: {
    externals: {
      vue: 'Vue',
      'vue-router': 'VueRouter',
      'element-ui': 'ELEMENT',
      axios: 'axios',
      bootstrap: 'bootstrap',
      jquery: 'jQuery',
      'vue-lazyload': 'VueLazyload',
      valine: 'Valine',
      'highlight.js': 'hljs',
    },
  },
  devServer: {
    proxy: {
      '/api': {
        target: 'http://localhost:8843/',
        changeOrigin: true,
        ws: true,
      },
    },
  },
}
