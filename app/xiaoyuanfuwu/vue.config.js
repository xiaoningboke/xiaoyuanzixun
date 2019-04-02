module.exports = {
  // 基本路径
  publicPath: './',

  // 输出文件目录
  outputDir: 'dist',

  devServer: {
    // 设置主机地址
    host: '0.0.0.0',
    // 设置默认端口
    port: 8080,
    // 设置代理
    proxy: {
      '/api': {
        // 目标 API 地址
        target: 'http://192.168.1.139:8090/api/', //接口地址
        changeOrigin: true,
        pathRewrite: {
          '^/api': '/' //这里的名称就是一个代理名称，叫什么都可以，具体通过API调用的时候，会将这个名字进行替换，
          //比如，真正的接口地址是http://1.2.3.4:8/mobile/login
          //     target就配置成http://1.2.3.4:8/mobile
          //     具体调用的时候，就写成/api/login
          //     会将api替换成target，即最终调用地址http://1.2.3.4:8/mobile/login
        }
        // 如果要代理 websockets
        // ws: true,

        // 将主机标头的原点更改为目标URL
        // changeOrigin: false
      }
    }
  }
};
