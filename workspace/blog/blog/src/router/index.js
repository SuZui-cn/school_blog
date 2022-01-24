import Vue from 'vue'
import VueRouter from 'vue-router'
import axios from 'axios'

Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/home' },
  {
    path: '/sign',
    name: 'Sign',
    component: () => import('../views/Sign.vue'),
    children: [
      { path: '', redirect: '/sign/login' },
      { path: 'login', name: 'Login', component: () => import('../components/Login.vue') },
      { path: 'register', name: 'Register', component: () => import('../components/Register.vue') },
      { path: 'emailLogin', name: 'EmailLogin', component: () => import('../components/EmailLogin.vue') },
      { path: 'editPwd', name: 'EditPwd', component: () => import('../components/EditPwd.vue') },
    ],
  },
  { path: '/home', name: 'Home', component: () => import('../views/Home.vue') },
  { path: '/detail', name: 'Detail', component: () => import('../views/Detail.vue') },
  { path: '/public', name: 'Public', component: () => import('../views/Public.vue') },
  { path: '/Self', name: 'Self', component: () => import('../views/Self.vue') },
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
    if (token !== null || typeof token === 'undefined') {
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
