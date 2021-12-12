<template>
  <div class="login-container">
    <div class="login-box">
      <div class="col-sm-6">
        <div class="login-card card">
          <div class="card-header">
            <h5>登录</h5>
          </div>
          <div class="card-body">
            <el-form
              :model="loginForm"
              status-icon
              :rules="rules"
              ref="loginForm"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item
                label="用户名"
                prop="username"
              >
                <el-input
                  v-model="loginForm.username"
                  placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item
                label="密码"
                prop="password"
              >
                <el-input
                  type="password"
                  v-model="loginForm.password"
                  autocomplete="off"
                  placeholder="请输入密码"
                ></el-input>
              </el-form-item>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="login('loginForm')"
                >登录</el-button>
                <el-button @click="register('loginForm')">注册</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
// import axios from 'axios'

export default {
  data () {
    return {
      loginForm: {
        username: '',
        password: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          // { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] },
        ],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
      },
    }
  },
  // created () {
  //   this.fastlogin()
  // },
  methods: {
    login (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 调用接口验证
          this.$axios.post('/login', this.loginForm).then((res) => {
            if (res.data.code === 400) {
              this.$message.error('账号或密码错误！')
              return
            }
            // 设置token
            window.sessionStorage.setItem('token', res.headers.authorization)
            // 保存用户信息

            window.sessionStorage.setItem('userInfo', JSON.stringify(res.data.data))
            // 显示登录成功
            this.$message({
              message: '登录成功！正在跳转...',
              type: 'success',
              duration: 1500,
              onClose: () => {
                this.$router.push('/home')
              },
            })
          })
          // 成功
          // 失败 提示错误
          /*  this.$message.error('账号或密码错误！') */
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 注册
    register () {
      this.$router.push('/sign/register')
    },
    // 快捷登录
    // fastlogin () {
    //   if (window.sessionStorage.getItem('userInfo')) {
    //     const email = JSON.parse(window.sessionStorage.getItem('userInfo')).u_email
    //     this.loginForm.email = email
    //   } else if (this.$route.params.loginForm) {
    //     console.log(this.$route.params.loginForm)
    //     this.loginForm = this.$route.params.loginForm
    //   }
    // },
  },
}
</script>
<style lang="less" scoped>
.login-container {
  width: 100%;
  height: 100vh;
  .login-box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 960px;
    margin: 0 auto;
    height: 100%;
  }
}
</style>
