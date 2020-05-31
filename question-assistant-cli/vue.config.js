const path = require('path')
console.log("process.env.NODE_ENV", process.env.NODE_ENV)
const debug = process.env.NODE_ENV !== 'production'   //环境标识
const resolve = dir => {
  return path.join(__dirname, dir)
}

module.exports = {
  // 基本路径
  publicPath: './',
  // 打包输出文件目录
  outputDir: 'dist',
  // 打包静态资源目录 (js, css, img, fonts)
  assetsDir: 'assets',
  // index.html 输出路径
  indexPath: 'index.html',
  // 保存时检查代码
  lintOnSave: !debug,
  // 生产环境 source map
  productionSourceMap: false,
  // webpack 链式操作
  chainWebpack: config => {
    config.resolve.alias.set('@', resolve('src'))
    config.resolve.symlinks(true) //修复HMR
  },
  // webpack-dev-server 相关配置
  devServer: {
    open: false, // 自动打开浏览器
    host: '0.0.0.0',
    port: 8080, /*代理端口*/
    https: false,
    hot:true,
    inline:true, //控制是否热更新
    proxy:{
      "/xhr":{
        target:"http://localhost:9090",
        changeOrigin: true, //是否跨域
        ws:true, //代理转发 websockets
        pathRewrite:{//路径重写
          "^/xhr": ""
        }
      }
    },
    disableHostCheck:true, //true可以解决invalid host header问题
    // historyApiFallback: {
    //   index: '/index.html'
    // },
    // 提供在服务器内部的其他中间件之前执行自定义中间件的能力
    before: app => {
    },
    overlay: {
      warnings: true,
      errors: true
    }
  },
  configureWebpack: config => {
    if (debug) { // 开发环境配置
      config.devtool = 'source-map'
    }
  }
}

// const debug = process.env.NODE_ENV !== 'production'
// module.exports = {
//   configureWebpack: config => {
//     if (debug) { // 开发环境配置
//       config.devtool = 'source-map'
//     }
//   }
// }
