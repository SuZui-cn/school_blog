import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Detail from '../views/Detail.vue'
import Public from '../views/Public.vue'
import Self from '../views/Self.vue'
import Sign from '../views/Sign.vue'
import Register from '../components/Register.vue'
import Login from '../components/Login.vue'
import axios from 'axios'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/home' },
  {
    path: '/sign',
    name: 'Sign',
    component: Sign,
    children: [
      { path: '', redirect: '/sign/login' },
      { path: 'login', name: 'Login', component: Login },
      { path: 'register', name: 'Register', component: Register },
    ],
  },
  { path: '/home', name: 'Home', component: Home },
  { path: '/detail', name: 'Detail', component: Detail },
  { path: '/public', name: 'Public', component: Public },
  { path: '/Self', name: 'Self', component: Self },
]
const router = new VueRouter({
  routes,
})
router.beforeEach((to, from, next) => {
  // 不让跳转的页面
  const targetPaths = ['/public', '/self']
  // 判断是否要跳转到权限页
  if (targetPaths.indexOf(to.path) !== -1) {
    const token = sessionStorage.getItem('token')
    if (token !== null) {
      // 添加token
      axios.interceptors.request.use((config) => {
        // 为请求头对象，添加token验证的Authorization字段
        config.headers.Authorization = token
        return config
      })
    }
    // 有token 放行
    if (token) {
      next()
    } else {
      // 无token 跳转登录页
      next('/sign')
    }
  } else {
    // 不是访问权限页 放行
    next()
  }
})
export default router
