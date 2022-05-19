# 校园博客部署教程

## 前端

切换至blog目录下执行

```
npm install
```

安装前端对应依赖

修改main.js文件下的baseURL

```
# main.js文件下的此处URL改为后台地址
axios.defaults.baseURL = 'http://www.bloom-lmh.com:8880/api'
```

修改vue.config.js文件下的devserver

```
  devServer: {
    proxy: {
      '/api': {
      # 修改此处地址为后台地址
        target: 'http://www.bloom-lmh.com:8880/',
        changeOrigin: true,
        ws: true,
      },
    },
  },
```

如果需要前后台分离则需要在nginx中配置请求转发的地址

## 后端

导入数据库

执行school_blog目录下的resources/sql目录下的school_blog.sql

修改application-pro.yml中的数据库的配置信息

```
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: 
    username: 
    password: 
```

运行后台项目时需要开启redis



