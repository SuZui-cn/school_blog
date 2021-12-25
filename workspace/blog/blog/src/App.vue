<template>
  <el-container id="app">
    <!-- 导航 start -->
    <el-header class="header" v-if="['/sign/login', '/sign/register', '/sign/emailLogin', '/sign/editPwd'].indexOf(this.$route.path) === -1">
      <nav class="navbar navbar-expand-lg navbar-dark" style="background-color: #4d4646">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">SchoolBlog</a>
          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item rounded-3">
                <router-link to="/home" class="nav-link" aria-current="page">博客首页</router-link>
              </li>
              <li class="nav-item rounded-3">
                <router-link to="/self" class="nav-link">我的文章</router-link>
              </li>
              <li class="nav-item rounded-3">
                <router-link to="/public" class="nav-link">发布文章</router-link>
              </li>
            </ul>
            <form class="d-flex">
              <input class="form-control me-2" type="search" placeholder="搜点什么..." aria-label="Search" />
              <button style="white-space: nowrap" class="btn btn-outline-success" type="submit">Search</button>
            </form>
          </div>
        </div>
      </nav>
    </el-header>
    <!-- 导航 end -->

    <!-- 内容 start -->
    <router-view></router-view>
    <!-- 内容 end -->

    <!-- 尾部 start -->
    <!--  <el-footer class="footer">Footer </el-footer> -->
    <!-- 尾部 end -->

    <!-- 返回顶部 start -->
    <el-backtop class="goback"></el-backtop>
    <!-- 返回顶部 end -->
  </el-container>
</template>
<style lang="less" scoped>
#app {
  position: relative;
  width: 100%;
  height: 100%;
  /* padding: 60px 0 300px; */
  /* padding-top: 60px; */
  .header,
  .footer,
  .goback {
    z-index: 1300;
  }
  ::-webkit-scrollbar {
    width: 0px;
    height: 0px;
    background-color: #f5f5f5;
  }
  .header {
    position: absolute;
    top: 0;
    width: 100%;
    padding: 0;
    nav {
      width: 100%;
      /* .container-fluid {
        background-color: #4d4646;
      } */
      .navbar-brand {
        &:hover {
          color: #dbb13d;
        }
      }
      li {
        &:hover {
          background-color: #312d2d;
        }
      }
    }
  }

  /*  .footer {
    width: 100%;
      height: 200px !important;
    background: lightblue;
  } */
}
</style>
