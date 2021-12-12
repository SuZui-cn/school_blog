<template>
  <el-container class="home-container">
    <!-- 顶部封面 start -->
    <el-header class="home-header">
      <div class="gocontent">
        <i class="bi bi-caret-down"></i>
      </div>
      <div class="mask"></div>
      <div class="sun-box">
        <i class="bi bi-sun-fill"></i>
      </div>
    </el-header>
    <!-- 顶部封面 end -->

    <!-- 内容 start -->
    <div class="home-content">
      <el-container class="home-content-box">
        <!-- 侧边栏 start-->
        <el-aside class="home-aside">
          <!--   <div class="aside-box" :style="'height:' + screeHeight * 0.8 + 'px'"> -->
          <div class="aside-box">
            <!-- 信息展示区 start -->
            <el-card class="info-box box-card">
              <div class="userInfo-box">
                <div class="userImg-box">
                  <el-avatar
                    :size="120"
                    :src="require('@/assets/logo.png')"
                  ></el-avatar>
                  <!--  <img src="../assets/logo.png" /> -->
                </div>
                <div
                  v-if="userInfo !== null && userInfo !== ''"
                  class="userName-box"
                >
                  <span>{{ userInfo.u_name }}</span>
                  <span>{{ userInfo.u_sex === '男' ? '先生' : '女士' }}</span>
                  <span>你好！</span>
                </div>
              </div>
              <div class="webInfo-box">
                <button
                  v-show="userInfo === null || userInfo === ''"
                  class="btn btn-primary"
                  @click="login"
                >登录</button>
                <button
                  class="btn btn-secondary"
                  @click="signoff"
                >注销</button>
              </div>
            </el-card>
            <!-- 信息展示区 end -->

            <!-- 近期文章 start -->
            <el-card class="recentAt-box box-card">
              <div class="tool-box">
                <h6 class="box-title">近期文章</h6>
                <span
                  class="fresh"
                  @click="findRecentAt()"
                ><i class="bi bi-arrow-clockwise"></i></span>
              </div>
              <div
                class="recentAt-cart"
                v-for="article in recentAtList"
                :key="article.atId"
                @click="toDetailPage(article)"
              >
                <div class="recentAt-img">
                  <img
                    src="../../src/assets/logo.png"
                    alt=""
                  />
                </div>
                <ul class="recentAt-info">
                  <li class="recentAt-title">{{ article.atTitle }}</li>
                  <li class="recentAt-abstract">{{ article.atAbstract }}</li>
                  <li class="recentAt-date">{{ article.atDate }}</li>
                </ul>
                <div class="recentAt-divider"></div>
              </div>
            </el-card>
            <!-- 近期文章 end -->

            <!-- 文章分类 start -->
            <el-card class="atType-box box-card">
              <div class="tool-box">
                <h6 class="box-title">文章分类</h6>
                <span
                  class="fresh"
                  @click="findAtType()"
                ><i class="bi bi-arrow-clockwise"></i></span>
              </div>
              <div
                v-for="type in atTypeList"
                :key="type.atType"
                @click="findAtByType(type)"
                class="text item type-item"
              >
                <span> {{ type.atType }}</span>
                <span> {{ type.count }}</span>
              </div>
            </el-card>
            <!-- 文章分类 start -->
          </div>
        </el-aside>
        <!-- 侧边栏 end-->

        <!-- 文章区 start-->
        <el-container>
          <!-- 主体 start -->
          <el-main class="home-main">
            <div
              v-for="(item, index) in articleList"
              :key="item.atId"
              @click="toDetailPage(item)"
              v-bind:class="[index % 2 === 0 ? 'row' : 'row-reverse', 'card article-card']"
            >
              <div class="img-box col-md-4">
                <img
                  v-lazy="require('@/assets/images/car.jpg')"
                  class="img-fluid rounded-start"
                  alt="..."
                />
              </div>
              <div class="card-body">
                <div class="card-title">
                  <h5>{{ item.atTitle }}</h5>
                  <ul>
                    <li>{{ item.atType }}类</li>
                    <!-- <li>作者：{{ item.u_name }}</li> -->
                  </ul>
                </div>
                <p class="card-text atAbstract">{{ item.atAbstract }}</p>
                <!-- <p class="card-text atContent">{{ item.atContent }}</p> -->
                <p class="card-text">
                  <small class="text-muted">{{ item.atDate }}</small>
                </p>
              </div>
            </div>
            <!-- 内容 end -->
            <div class="loading-box">
              <p
                v-if="loading"
                v-loading="loading"
                element-loading-text="拼命加载中"
                element-loading-spinner="el-icon-loading"
                element-loading-background="rgba(0, 0, 0, 0.8)"
              ></p>
              <p v-if="noMore">没有更多了</p>
            </div>
          </el-main>
          <!-- 主体 end -->
          <!-- 底部 start -->
          <el-footer class="home-footer footer">
            <div class="footer-box"></div>
          </el-footer>
          <!-- 底部 end -->
        </el-container>
        <!-- 文章区 end-->
      </el-container>
    </div>
    <!-- 内容 end -->

    <!-- 背景盒子 start -->
    <div class="bg-box"></div>
    <!-- 背景盒子 end -->
  </el-container>
</template>

<script>
// import { marked } from '../../node_modules/marked/src/marked'
export default {
  name: 'Home',
  data () {
    return {
      // 文章列表
      articleList: [
        /* { atId: 1, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了**B1111**\n## 寝室', atType: '娱乐' },
        { atId: 2, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 3, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 4, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 5, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 6, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 7, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 8, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 9, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 10, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' }, */
      ],
      // 用户信息
      /*     userInfo: { uId: 1, u_name: '兰茂豪', u_password: '', u_sex: '男', u_phone: '', u_email: '', u_head_img: '' }, */
      userInfo: '',
      // 近期文章
      recentAtList: [
        { atId: 1, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了**B1111**\n## 寝室', atType: '娱乐' },
        { atId: 2, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 3, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 4, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 5, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      ],
      // 文章分类
      atTypeList: [
        { atType: '文学类', count: 5 },
        { atType: '生活类', count: 30 },
        { atType: '校园类', count: 5 },
        { atType: '学术类', count: 10 },
      ],
      // 是否加载中
      loading: false,
      // 每次加载最大文章数
      maxLen: 10,
      // 当前页数
      curPageNo: 0,
      // 最大文章数
      total: 10
    }
  },

  // dom加载完后
  mounted () {
    // 特效
    // 获取屏幕高
    const screeHeight = document.documentElement.clientHeight
    // 获取头部高
    const headerHeight = document.querySelector('.home-header').offsetHeight
    // 获取侧边栏滚动盒子元素
    const asideBox = document.querySelector('.aside-box')
    // 动态改变侧边栏盒子display
    window.onscroll = () => {
      // 滚动距离
      const sy = document.documentElement.scrollTop
      // 页面高度
      const documentHeight = document.body.scrollHeight
      // 如果到内容区 侧边栏跟随
      if (sy >= headerHeight) {
        asideBox.style.position = 'fixed'
        asideBox.style.top = 0 + 'px'
      } else {
        asideBox.style.position = ''
        asideBox.scrollTo(0, 0)
      }
      // 滑到低部触发文章加载
      if (sy + screeHeight >= documentHeight - screeHeight * 0.6) {
        this.load()
      }
    }
    // 为gocontent绑定事件，点击快速移到内容区
    document.querySelector('.gocontent').onclick = function () {
      // console.log('a')
      window.scrollTo(0, document.querySelector('.home-header').offsetHeight)
    }
    // 太阳上升
    this.$('.mask').animate({ opacity: 0 }, 2000)
    this.$('.sun-box').animate({ top: -100 }, 1000)
  },
  computed: {
    // 当大于这条时显示没有更多
    noMore () {
      return this.articleList.length >= this.total
    },
    // 不可滚动条件为加载时或者没有数据时
    disabled () {
      return this.loading || this.noMore
    },
  },
  created () {
    // 初始化用户信息
    this.initUserInfo()
    // 初始化时加载10个文章
    this.load()
    // 查询近期文章
    this.findRecentAt()
    // 查询文章类型
    this.findAtType()
  },
  methods: {
    // 登陆后初始化用户信息
    initUserInfo () {
      // 获取并设置用户信息
      this.userInfo = JSON.parse(window.sessionStorage.getItem('userInfo'))
    },
    // 跳转文章详情页
    toDetailPage (article) {
      this.$router.push({
        name: 'Detail',
        path: '/detail',
        params: {
          article,
        },
      })
    },
    // 加载文章
    load () {
      if (this.disabled) {
        return
      }
      // 加载中禁止滚动
      this.loading = true
      // 调用接口分页请求文章
      this.curPageNo++
      // console.log(this.curPageNo)
      // const num = this.articleList.length
      // 模拟异步请求
      this.$axios.get(`/article/getPage/${this.curPageNo}/${this.maxLen}`).then((res) => {
        this.total = res.data.data.total
        res.data.data.records.forEach(at => {
          // this.articleList.push(marked(at))
          this.articleList.push(at)
          // console.log(at)
        })
      })
      // console.log(this.articleList)
      // setTimeout(() => {
      //   this.articleList.push(
      //     { atId: num + 1, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 2, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 3, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 4, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 5, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 6, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 7, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 8, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 9, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      //     { atId: num + 10, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', ab_abstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' }
      //   )
      // }, 4000)
      this.loading = false
    },
    // 查询近期文章
    findRecentAt () {
      // 调用接口刷新近期文章
    },
    // 查询类型
    findAtType () {
      // 调用接口刷新每种类型文章数量
    },
    // 分类查询
    findAtByType (type) {
      // 调用接口查询
      console.log(type)
    },
    // 登录
    login () {
      this.$router.push('/sign')
    },
    // 注销
    signoff () {
      // 清除session
      window.sessionStorage.clear()
      // 返回登录页
      this.$router.push('/sign')
    },
  },
}
</script>
<style lang="less" scoped>
.row {
  flex-direction: row;
}
.row-reverse {
  flex-direction: row-reverse;
}

.home-container {
  width: 100%;
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;

  .home-header,
  .home-content {
    z-index: 2000;
  }
  // 背景
  .bg-box {
    position: fixed;
    width: 100%;
    height: 100%;
    z-index: 1000;
    top: 0;
    left: 0;
    background: url(../../src/assets/images/bl_bg1.jpg) no-repeat;
    background-size: 100% 100%;
    &::before {
      content: "";
      display: block;
      position: absolute;
      width: 100%;
      height: 100%;
      background-color: black;
      opacity: 0.2;
    }
  }
  .home-header {
    position: relative;
    width: 100%;
    height: 100vh !important;
    padding: 0;
    .gocontent {
      position: absolute;
      bottom: 50px;
      left: 50%;
      transform: translateX(-50%);
      font-size: 40px;
      color: #fff;
      cursor: pointer;
      z-index: 2000;
    }
    .mask {
      position: absolute;
      width: 100%;
      height: 100%;
      background-color: black;
    }
    .sun-box {
      position: absolute;
      bottom: -100px;
      right: 20%;
      i {
        font-size: 100px;
        color: rgb(220, 77, 20);
      }
    }
  }
  // 内容
  .home-content {
    width: 100%;
    background-color: #f5f5f5;
    .home-content-box {
      width: 1200px;
      margin: 20px auto;

      .home-aside {
        position: relative;
        width: "300px";
        cursor: pointer;
        // 文字不可选中
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
        ::-webkit-scrollbar {
          width: 0px;
          height: 0px;
          background-color: #f5f5f5;
        }
        .aside-box {
          width: 300px;
          height: 100vh;
          padding-bottom: 20px;
          margin-top: 20px;
          overflow: scroll;
          .el-card {
            margin: 20px 0 0 0;
            border-radius: 8px;
          }
          .tool-box {
            display: flex;
            justify-content: space-between;
            align-items: center;
            height: 30px;
            margin-bottom: 10px;
            .box-title {
              color: rgb(163, 127, 26);
            }
            .fresh {
              i {
                font-size: 18px;
              }
            }
          }
          .info-box {
            display: flex;
            flex-flow: column;
            justify-content: space-between;
            position: relative;
            min-height: 350px;
            margin: 0;
            div {
              display: flex;
              flex-direction: column;
              align-items: center;
            }
            .userInfo-box {
              .userImg-box {
                margin: 30px 0;
              }
              .userName-box {
                flex-direction: row;
              }
            }
            .webInfo-box {
              height: 100px;
              flex-direction: row;
              justify-content: center;
              align-items: flex-end;
              button {
                margin: 0 10px;
              }
            }
          }
          .recentAt-box {
            .recentAt-cart {
              position: relative;
              display: flex;
              width: 100%;
              height: 80px;
              max-height: 80px;
              overflow: hidden;
              font-size: 10px;
              .recentAt-img {
                width: 60px;
                height: 60px;
                min-width: 60px;
                &:hover img {
                  transform: scale(1.1);
                }
                img {
                  width: 100%;
                  height: 100%;
                }
              }
              .recentAt-info {
                display: flex;
                flex-direction: column;
                padding: 0 5px;
                .recentAt-abstract {
                  white-space: nowrap;
                  text-overflow: ellipsis;
                  color: #ccc;
                }
              }
              .recentAt-divider {
                position: absolute;
                bottom: 0;
                width: 100%;
                height: 20px;
                &::after {
                  content: "";
                  position: absolute;
                  top: 50%;
                  transform: translateY(-50%);
                  width: 100%;
                  border-top: 1px dashed rgb(199, 175, 67);
                }
              }
            }
          }
          .atType-box {
            .type-item {
              display: flex;
              justify-content: space-between;
              align-items: center;
              height: 40px;
              transition: all 0.1;
              &:hover {
                color: rgb(177, 102, 247);
              }
            }
          }
        }
      }
      .home-main {
        .card {
          display: flex;
          width: 100%;
          height: 300px;
          margin: 0 0 60px;
          max-width: none;
          cursor: pointer;
          box-shadow: 0px 0px 5px 2px #ccc;
          border-radius: 8px;
          &:hover {
            box-shadow: 0px 0px 10px 5px #ccc;
          }
          &:hover img {
            transform: scale(1.1);
          }
          .img-box {
            width: 45%;
            height: 100%;
            padding: 0;
            overflow: hidden;

            img {
              width: 100%;
              height: 100%;
            }
          }

          .card-body {
            width: 55%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            .card-title {
              display: flex;
              justify-content: space-between;
              ul {
                padding: 0;
                margin: 0;
                li {
                  float: left;
                  font-size: 14px;
                  color: #ccc;
                  margin: 0 5px;
                }
              }
            }
            .atAbstract {
              font-size: 20px;
              color: #ccc;
            }
            .atContent {
              overflow: hidden;
              text-overflow: ellipsis;
              white-space: nowrap;
            }
          }
        }
        .loading-box {
          height: 50px;
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
          p {
            width: 100%;
            margin: 10px 0;
            text-align: center;
          }
        }
      }
      .home-footer {
        height: 200px !important;
        .footer-box {
          width: 100%;
          height: 100%;
          background: rgb(182, 226, 252);
        }
      }
    }
  }
}
</style>
