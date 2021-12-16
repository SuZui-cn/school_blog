import Vue from 'vue'
import App from './App.vue'
import router from './router'
// 导入element
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// 导入bootstrap
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap'
// 导入字体图标
import 'bootstrap-icons/font/bootstrap-icons.css'
// 导入懒加载
import VueLazyload from 'vue-lazyload'
// 导入jq
import $ from 'jquery'
// 导入axios
import axios from 'axios'
// 导入公共样式
import '../src/assets/css/common.css'
// 导入编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
// 在main.js引入qs
import qs from 'qs'
// 配全局属性配置，在任意组件内可以使用this.$qs获取qs对象
Vue.prototype.$qs = qs
Vue.config.productionTip = false
// 使用懒加载
Vue.use(VueLazyload, {
  // 加载错误时显示的图标
  error: require('@/assets/logo.png'),
  // 加载中显示的图标
  loading: require('@/assets/images/loading.gif'),
  // 预加载图片数
  preLoad: 1.3,
  // 尝试次数
  attempt: 1,
})
Vue.use(ElementUI)
Vue.use(mavonEditor)
Vue.prototype.$ = $
/* router.beforeEach(to,from,next){
  if(to.path.indexOf('/login'))
} */
// 添加token
// axios.interceptors.request.use((config) => {
//   // 为请求头对象，添加token验证的Authorization字段
//   // config.headers.Authorization = window.sessionStorage.getItem('token')
//   config.headers.Authorization = window.sessionStorage.getItem('token')
//   return config
// })
// 挂载axios
Vue.prototype.$axios = axios
axios.defaults.baseURL = 'http://localhost:8843/api'

new Vue({
  router,
  render: (h) => h(App),
}).$mount('#app')
