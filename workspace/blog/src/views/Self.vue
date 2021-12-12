<template>
  <div class="self-container">
    <div class="self-box">
      <div class="atList-box">
        <div class="search-box">
          <h5>文章列表</h5>
          <div class="search-frame">
            <div class="input-group flex-nowrap">
              <input
                v-model="searchField"
                type="text"
                class="search-input form-control"
                placeholder="搜索文章"
                aria-label="search"
                aria-describedby="addon-wrapping"
              />
              <span
                @click="search"
                class="search-icon input-group-text"
                id="addon-wrapping"
              ><i class="bi bi-search"></i></span>
            </div>
          </div>
        </div>
        <div class="atItem-box">
          <el-timeline>
            <el-timeline-item
              class="atItem"
              v-for="article in articleList"
              :key="article.atId"
              :timestamp="article.atDate"
              placement="top"
            >
              <el-card>
                <div class="atItem-head">
                  <h5>
                    {{ article.atTitle }}
                  </h5>
                  <span>{{ article.atType }}类</span>
                </div>
                <p>{{ article.atAbstract }}</p>
                <p>{{ article.atContent }}</p>
                <button
                  type="button"
                  class="btn btn-primary"
                  @click="toDetailPage(article)"
                >查看详情</button>
                <button
                  type="button"
                  class="btn btn-secondary"
                  @click="alterAt(article)"
                >编辑</button>
                <el-popconfirm
                  title="这是一段内容确定删除吗？"
                  @confirm="removeAt(article.atId)"
                >
                  <el-button
                    slot="reference"
                    type="button"
                    class="btn btn-success"
                  >删除</el-button>
                </el-popconfirm>
              </el-card>
            </el-timeline-item>
          </el-timeline>
        </div>
        <div class="paging-box">
          <el-pagination
            background
            @size-change="pageSizeChange"
            @current-change="getAtListByNo"
            :current-page="at_info.curPageNo"
            :total="at_info.at_total"
            :page-sizes="[3, 4, 5, 6]"
            :page-size="at_info.pageSize"
            layout="total, sizes, prev, pager, next, jumper"
          > </el-pagination>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: 'Self',
  data () {
    return {
      searchField: '',
      // 文章信息
      at_info: {
        // 总数
        at_total: 20,
        // 每页数量
        pageSize: 5,
        // 当前页
        curPageNo: 1,
      }, // 文章列表
      articleList: [
        { atId: 1, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了**B1111**\n## 寝室', atType: '娱乐' },
        { atId: 2, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 3, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 4, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
        { atId: 5, u_name: '高山流水', atDate: '2021-12-03 14:39:38', atTitle: '什么？隔壁B1111寝室惊现', atAbstract: '这是一段神奇的故事', atContent: '昨天去了B1111寝室', atType: '娱乐' },
      ],
    }
  },
  created () {
    // 查询文章
    this.findAtList()
  },
  methods: {
    // 查询文章
    findAtList () {
      // 调用接口查询文章
    },
    // 更新每页数量
    pageSizeChange (pageSize) {
      this.at_info.pageSize = pageSize
    },
    // 分页请求文章
    getAtListByNo (curpageNo) {
      // 更新当前页
      this.at_info.curPageNo = curpageNo
      // 调用接口返回数据
      console.log(curpageNo + '  ' + this.at_info.pageSize)
    },
    // 搜索文章
    search () {
      // 搜索内容
      console.log(this.searchField)
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
    // 编辑
    alterAt (article) {
      // 调用编辑接口编辑
      console.log(article)
      // 跳转发布文章页
      this.$router.push({
        name: 'Public',
        path: '/public',
        params: {
          article,
        },
      })
    },
    // 删除
    removeAt (id) {
      // 调用删除接口删除
      console.log(id)
    },
  },
}
</script>
<style lang="less" scoped>
.self-container {
  width: 100%;
  height: 100%;
  .self-box {
    display: flex;
    justify-content: center;
    align-items: center;
    width: 1200px;
    margin: 100px auto;
    .atList-box {
      width: 100%;
      height: 100%;
      display: flex;
      flex-direction: column;
      .search-box {
        display: flex;
        justify-content: space-between;
        h5 {
          color: rgb(163, 127, 26);
        }
      }
      .atItem-box {
        .atItem-head {
          display: flex;
          justify-content: space-between;
        }
      }
      .paging-box {
        display: flex;
        justify-content: center;
      }
      button {
        margin-right: 15px;
      }
    }
  }
}
</style>
