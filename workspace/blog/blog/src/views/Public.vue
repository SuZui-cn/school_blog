<template>
  <div class="public-container">
    <div class="public-box">
      <div class="mb-3">
        <label for="atTitle" class="form-label">标题</label>
        <input v-model="article.atTitle" type="text" class="form-control" id="atTitle" placeholder="文章主题" required />
      </div>
      <div class="mb-3">
        <label for="atAbstract" class="form-label">描述</label>
        <input v-model="article.atAbstract" type="text" class="form-control" id="abstract" placeholder="描述信息" required />
      </div>
      <!-- <div class="mb-3">
        <label
          for="atDate"
          class="form-label"
        >日期</label>
        <input
          v-model="article.atDate"
          type="date"
          class="form-control"
          id="atDate"
          required
        />
      </div>
      <div class="mb-3">
        <label
          for="at_time"
          class="form-label"
        >时间</label>
        <input
          v-model="article.at_time"
          type="time"
          class="form-control"
          id="at_time"
          required
        />
      </div> -->
      <div class="mb-3">
        <label for="atType" class="form-label">文章类别</label>
        <select v-model="article.atType" class="form-select" aria-label="文学类">
          <option selected>请选择类型</option>
          <option value="文学类">文学类</option>
          <option value="生活类">生活类</option>
          <option value="校园类">校园类</option>
          <option value="学术类">学术类</option>
          <option value="文档类">文档类</option>
        </select>
      </div>
      <!-- <label
        for=""
        class="form-label"
      >添加背景图片</label>
      <div class="mb-3 border rounded-3">
        <el-upload
          action="https://jsonplaceholder.typicode.com/posts/"
          list-type="picture-card"
          :on-preview="handlePictureCardPreview"
          :on-remove="handleRemove"
        >
          <i class="el-icon-plus"></i>
        </el-upload>
      </div> -->
      <div class="mb-3">
        <el-button class="btn btn-light" type="text" @click="mdVisible = true">编写内容</el-button>
        <el-dialog title="开始编写你的文章吧！" :visible.sync="mdVisible" width="80%">
          <mavon-editor class="md-box" v-model="article.atContent" ref="md" @change="change" :ishljs="false" style="min-height: 600px" />
          <span slot="footer" class="dialog-footer">
            <el-button @click="mdVisible = false">取 消</el-button>
            <el-button type="primary" @click="mdVisible = false">保存</el-button>
          </span>
        </el-dialog>
      </div>
      <div class="mb-3 d-flex justify-content-center">
        <el-popconfirm title="这是一段内容确定重置吗？" @confirm="reset">
          <el-button slot="reference" type="button" class="reset-btn btn btn-secondary">重置</el-button>
        </el-popconfirm>
        <button type="button" class="submit-btn btn btn-primary" @click="submit">发布</button>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  // 注册组件
  data() {
    return {
      article: {
        atTitle: '',
        atContent: '',
        atAbstract: '',
        // atDate: '',
        atType: '',
      },
      // 转为html的文章内容
      html: '',
      // markdown编辑器开关
      mdVisible: false,
    }
  },
  created() {
    // 获取文章

    if (typeof this.$route.params.article !== 'undefined') {
      // 获取文章
      this.article = this.$route.params.article
      // 推入localstorage
      window.sessionStorage.setItem('article', JSON.stringify(this.article))
    } else if (typeof this.$route.params.article === 'undefined') {
      window.sessionStorage.removeItem('article')
    } else {
      if (window.sessionStorage.getItem('article')) {
        this.article = JSON.parse(window.sessionStorage.getItem('article'))
      }
    }
  },

  methods: {
    // 将输入转为html
    change(value, render) {
      // render 为 markdown 解析后的结果html
      this.html = render
    },
    // 发布文章
    submit() {
      // 调用接口上传文章
      // console.log(this.article)
      // console.log(this.html)
      // this.$message.success('提交成功，已打印至控制台！')
      const uid = JSON.parse(window.sessionStorage.getItem('userInfo')).id
      this.$axios.post(`/article/save/${uid}`, this.article).then((res) => {
        console.log(this.article)
        if (res.data.code === 400) {
          this.$message.error('提交失败')
          return
        }
        this.$message.success('提交成功')
      })
    },
    // 图片上传关闭
    handleRemove(file, fileList) {
      console.log(file, fileList)
    },
    // 图片上床预览
    handlePictureCardPreview(file) {
      console.log(file)
    },
    // 重置
    reset() {
      this.article = []
    },
  },
}
</script>
<style lang="less" scoped>
.public-container {
  width: 100%;
  height: 100%;
  margin: 60px 0;
  .public-box {
    width: 960px;
    margin: 60px auto;
    .md-box {
      box-shadow: none !important;
      border: 2px solid antiquewhite;
    }
  }
  .submit-btn {
    margin: 0 20px;
  }
}
</style>
