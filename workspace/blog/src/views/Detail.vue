<template>
  <div class="detail-container">
    <div class="detail-box">
      <div class="card mb-3">
        <!-- 状态栏 start -->
        <div class="detail-header card-header">
          <div class="demo-basic--circle">
            <div class="head-img"><el-avatar :size="35" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar></div>
          </div>
          <span>作者：{{ article.u_name }}</span>
          <span>文章类型：{{ article.at_type }}类</span>
          <div class="back-box" @click="back">
            <label for="back-box">返回</label> <span id="back-box"><i class="bi bi-arrow-return-left"></i></span>
          </div>
        </div>
        <!-- 状态栏 end -->

        <img src="../assets/images/bl_bg1.jpg" class="card-img-top" alt="..." />

        <!-- 文章内容 start -->
        <div class="detail-content card-body">
          <h5 style="text-align: center" class="card-title">{{ article.at_title }}</h5>
          <p class="card-text" v-html="mark_at"></p>
        </div>
        <!-- 文章内容 end -->

        <!-- 版权 start -->
        <div class="detail-copyright card-body">
          <ul>
            <li>作者：{{ article.u_name }}</li>
            <li>版权声明：自由转载</li>
            <li>链接：wwww.asd1</li>
            <li></li>
          </ul>
          <!-- <div>二维码</div> -->
        </div>
        <!-- 版权 end -->

        <!-- 评论 start  -->
        <div class="comment-box card-body" id="comment-box"></div>
        <!-- 评论 end  -->
      </div>
    </div>
  </div>
</template>
<script>
import Valine from 'valine'
import { marked } from '../../node_modules/marked/src/marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/monokai-sublime.css'
export default {
  name: 'Detail',
  data() {
    return {
      article: {},
      text: '',
    }
  },
  methods: {
    // 返回上一页
    back() {
      this.$router.go(-1)
    },
  },
  computed: {
    /**
     *文章转为markdown后渲染
     *  */
    mark_at() {
      // 渲染内容为markdown格式
      return this.article.at_content ? marked(this.article.at_content) : ''
    },
  },
  created() {
    if (this.$route.params.article) {
      // 获取文章
      this.article = this.$route.params.article
      // 推入localstorage
      window.sessionStorage.setItem('article', JSON.stringify(this.article))
    }
    this.article = JSON.parse(window.sessionStorage.getItem('article'))
  },
  mounted() {
    // 滚动条回位置
    window.scrollTo(0, 0)
    // 使用评论系统
    Valine({ el: '#comment-box', appId: '2k3f2ugJHBf6cJ5Xo3KEU3wG-gzGzoHsz', appKey: 'ee1QWiclt1OYKGGFMumOJzla' })
    // marked配置
    marked.setOptions({
      renderer: new marked.Renderer(),
      highlight: function (code) {
        return hljs.highlightAuto(code).value
      },
      pedantic: false,
      gfm: true,
      tables: true,
      breaks: false,
      sanitize: false,
      smartLists: true,
      smartypants: false,
      xhtml: false,
    })
  },
}
</script>
<style lang="less" scoped>
.detail-container {
  width: 100%;
  height: 100%;
  .detail-box {
    width: 960px;
    margin: 100px auto;
    .detail-header {
      position: relative;
      display: flex;
      align-items: center;
      .head-img {
        display: flex;
        align-items: center;
        margin-right: 20px;
      }
      .back-box {
        position: absolute;
        right: 0;
        cursor: pointer;
        i {
          font-weight: 600;
          font-size: 20px;
        }
      }
      span {
        margin-right: 20px;
      }
    }
    .detail-copyright {
      display: flex;
      justify-content: flex-start;
      background-color: #f9fefb;
    }
  }
}
</style>
