<template>
  <div class="register-container">
    <div class="register-box">
      <div class="col-sm-6">
        <div class="register-card card">
          <div class="card-header">
            <h5>修改密码</h5>
          </div>
          <div class="card-img">
            <img
              src="../assets/images/bird_1.jpg"
              class="card-img-top"
              alt="..."
            />
          </div>
          <div class="card-body">
            <el-form
              :model="registerForm"
              status-icon
              :rules="rules"
              ref="registerForm"
              label-width="100px"
              class="demo-ruleForm"
            >
              <el-form-item
                label="邮箱"
                prop="email"
              >
                <el-input
                  v-model="registerForm.email"
                  placeholder="请输入邮箱"
                ></el-input>
              </el-form-item>
              <el-form-item
                label="新密码"
                prop="password"
              >
                <el-input
                  type="password"
                  v-model="registerForm.password"
                  autocomplete="off"
                  placeholder="请输入新密码"
                ></el-input>
              </el-form-item>
              <div class="check-code-box">
                <el-form-item
                  label="验证码"
                  prop="checkcode"
                >
                  <el-input
                    type="text"
                    v-model="registerForm.checkcode"
                    autocomplete="off"
                    placeholder="请输入验证码"
                  ></el-input>
                </el-form-item>
                <input
                  type="button"
                  class="code-btn btn btn-secondary"
                  v-model="time"
                  @click="getCheckCode()"
                />
              </div>
              <el-form-item>
                <el-button
                  type="primary"
                  @click="editPwd('registerForm')"
                >修改</el-button>
                <el-button
                  type="secondary"
                  @click="
                    () => {
                      this.$router.push('/sign/login')
                    }
                  "
                >返回</el-button>
              </el-form-item>
            </el-form>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  data () {
    return {
      registerForm: {
        email: '',
        password: '',
        checkcode: '',
      },
      rules: {
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] },
        ],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }],
        checkcode: [{ required: true, message: '请输入验证码', trigger: 'blur' }],
      },
      time: '获取验证码',
      timer: null,
    }
  },
  methods: {
    // 注册
    editPwd (formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 调用接口验证
          this.$axios.post('/forgetPwd', this.registerForm).then((res) => {
            if (res.data.code === 400) {
              this.$message.error(res.data.msg)
              return
            }
            // 成功 显示注册成功
            this.$message({
              message: '修改成功！正在跳转登录页...',
              type: 'success',
              duration: 800,
              onClose: () => {
                const loginForm = { email: this.registerForm.email, password: this.registerForm.password }
                this.$router.push({
                  path: '/sign',
                  name: 'Login',
                  params: {
                    loginForm,
                  },
                })
                // 清除记录
                this.$refs[formName].resetFields()
              },
            })
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 登录
    login () {
      // this.$router.push('/sign')
      this.$axios.post('/checkCode', this.registerForm).then((res) => {
        if (res.data.code === 400) {
          this.$message.error('验证码错误')
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
    },
    // 获取验证码
    getCheckCode () {
      // 验证码倒计时
      this.setTime()
      // 掉用接口向指定邮箱发送验证码
      // 邮箱
      console.log(this.registerForm.email)
      this.$axios.get(`/emailLogin/${this.registerForm.email}`).then((res) => {
        if (res.data.code === 400) {
          this.$message.error('验证码发送失败')
        }
        this.$message.success('验证码发送成功')
      })
    },
    // 验证码倒计时
    async setTime () {
      let num = 60
      this.time = num-- + 's'
      clearInterval(this.timer)
      this.timer = await setInterval(() => {
        // 获取元素
        this.time = num-- + 's'
        num === 0 && (this.time = '获取验证码') && clearInterval(this.timer)
      }, 1000)
    },
  },
}
</script>
<style lang="less" scoped>
.register-container {
  width: 100%;
  height: 100vh;
  .register-box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 960px;
    margin: 0 auto;
    height: 100%;
    .card-img {
      width: 100%;
      height: 200px;
      overflow: hidden;
      &:hover img {
        transform: scale(1.1);
      }
    }
    .check-code-box {
      display: flex;
      justify-content: space-between;
      .code-btn {
        width: 106px;
        height: 40px;
        margin-right: 15px;
      }
    }
  }
}
</style>
