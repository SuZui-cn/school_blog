// 这个文件是 vue-cli 创建出来的项目的配置文件
// 在 vue.config.js 这个配置文件中，可以对整个项目的打包、构建进行全局性的配置

// webpack 在进行打包的时候，底层用到了 node.js
// 因此,在 vue.config.js 配置文件中，可以导入并使用 node.js 中的核心模块
/**
 * @param: ____dirname是该文件所处根目录
 * @param: 该目录下的文件
 * */
module.exports = {
  publicPath: './',
}
