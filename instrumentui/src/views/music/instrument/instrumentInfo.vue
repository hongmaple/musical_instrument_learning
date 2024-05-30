<template>
  <el-row :gutter="20">
    <el-col :sm="3" class="hidden-xs-only" style="opacity:0;">左侧占位</el-col>
    <el-col :xs="24" :sm="18">
      <el-card style="background-color: rgba(255, 255, 255,1)" class="first-card">
        <div slot="header" class="total blog-info">
          <div class="user-info">
            <i class="el-icon-user"></i>
            <span class="header">  {{instrument.createBy}}</span>
          </div>
          <div class="blog-date">
            <i class="el-icon-date"></i>
            <span>  {{instrument.createTime}}</span>
          </div>
          <div class="blog-views">
            <i class="el-icon-view"></i>
            <span>  {{instrument.views}}</span>
          </div>
        </div>
        <h2 class="blog-title header">{{instrument.title}}
          {{instrument.instrumentCategory.name}}
        </h2>
        <div v-html="instrument.content"></div>
        <!-- <div class="appreciate">
          <el-popover
                  placement="bottom"
                  title=""
                  width="300"
                  trigger="hover"
                  content="这是一段内容,这是一段内容,这是一段内容,这是一段内容。">
            <el-button class="zanshang" slot="reference" type="danger" round plain>赞赏</el-button>
          </el-popover>
        </div> -->
        <div class="author">
          <ul>
            <li>作者 {{instrument.createBy}}</li>
            <li>发表时间 {{instrument.createTime}}</li>
          </ul>
        </div>
      </el-card>
    </el-col>
    <el-col :xs="24" :sm="0"></el-col>
    <el-col :sm="3" class="hidden-xs-only" style="opacity:0;">右侧占位</el-col>
    <!-- 设置底部距离的 -->
    <el-backtop :bottom="60">
      <div
        style="{
            height: 50px;
            width: 50px;
            background-color: rgba(240,239,241,1);
            box-shadow: 0 0 6px rgba(0,0,0, .12);
            text-align: center;
            line-height: 40px;
            border-radius:2px;
            color: #1989fa;
          }"
      >
        <svg-icon icon-class="top" />
      </div>
    </el-backtop>
  </el-row>
</template>

<script>
// import 'cherry-markdown/dist/cherry-markdown.min.css'
import {
  getInstrument,
} from "@/api/music/instrument";
import {mapState} from 'vuex'
import CherryMarkdown from '@/components/CherryMarkdown'

export default {
  name: "instrumentInfo",
  components: {
    CherryMarkdown,
  },
  data() {
    return {
      instrument: {
        instrumentCategory: {}
      },
      commentForm: {
        content: ''
      },
    }
  },
  watch: {
    '$route' (to, from) {
      this.$router.go(0);
    }
  },
  created() {
    this.getInstrumentInfomation()
  },
  computed: {
    ...mapState([
      'userInfo',
      'administrator',
    ])
  },
  methods: {
    // 获取博客详情信息
    async getInstrumentInfomation() {
      // 增加阅读量
      //addInstrumentViews(this.$route.query.id);
      getInstrument(this.$route.query.id).then(response => {
        const {data: res} = response;
        this.instrument = response.data
      });
    },
  },

}
</script>

<style scoped>

.el-card {
  width: 100%;
}

.el-popper /deep/ {
  box-shadow: 0 2px 4px 0 rgb(34 36 38 / 12%),
}

.first-card {
  border-radius: 10px 10px 10px 10px;
  position: relative;
  padding-bottom: 10px;
  /*text-align: center;*/
  font: 300 1em/1.8 PingFang SC, Lantinghei SC, Microsoft Yahei, Hiragino Sans GB, Microsoft Sans Serif, WenQuanYi Micro Hei, sans-serif;

}

hr.style-one {
  width: 100%;
  background-image: linear-gradient(to right, rgba(64, 158, 255, 0), rgba(64, 158, 255, 0.75), rgba(64, 158, 255, 0));
}

.appreciate {
  text-align: center;
}

.tags {
  display: flex;
  align-items: center;
  margin-left: 50px;
  margin-top: 20px;
}

.tag-item {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-left: 10px;
  margin-bottom: 20px;
}

.tag {
  padding-left: 10px;
  padding-right: 10px;
  border-radius: 5px;
  background-color: #ecf5ff;
  border: 1px solid #409eff;
  color: #409eff;
  display: flex;
}

.sjx-outer {
  width: 0;
  height: 0;
  border-top: 7px solid transparent;
  border-bottom: 7px solid transparent;
  border-right: 7px solid #409eff;
  position: relative;
}

.sjx-inner {
  border-top: 7px solid transparent;
  border-bottom: 7px solid transparent;
  border-right: 7px solid #ecf5ff;
  top: -7px;
  left: 1px;
  position: absolute;
}

.author {
  text-align: left;
  background-color: #fcfff5;
  box-shadow: 0 0 0 1px #a3c293 inset;
  color: #2c662d;
  width: 100%;
  position: absolute;
  left: 0;
  margin: 20px 0;
  padding: 20px 0;
  font-size: small;
  font-family: PingFang SC, Lantinghei SC, Microsoft Yahei, Hiragino Sans GB, Microsoft Sans Serif, WenQuanYi Micro Hei, sans-serif;
}

.comments {
  margin-top: 150px;
  box-shadow: 0 1px 2px 0 rgb(34 36 38 / 15%);
  border: 1px solid rgba(34, 36, 38, .15);
  border-top: 2px solid #409EFF;
  text-align: left;
}
.Instrument-title {
  text-align: center;
}

.Instrument-info {
  display: flex;
  align-items: center;
  color: rgba(0, 0, 0, .4);
  font-size: 13px;
}
.Instrument-date {
  margin-right: 5px;
  float: right;
}

.Instrument-views {
  margin-right: 5px;
  float: right;
}

.user-info {
  justify-content: space-around;
  align-items: center;
  margin-right: 15px;
  float: left;
}

.header {
  text-decoration: none;
  color: #3a8ee6;
  font-weight: bold;
}

@media screen and (max-width: 768px) {
  .tags {
    margin-left: 0;
    margin-top: 20px;
  }

  hr {
    display: none;
  }

  .comment-content {
    font-size: 12px !important;
  }
}

@media only screen and (max-width: 480px) {
  h2 {
    font-weight: normal;
  }

  code, pre {
    font-size: 13px !important;
  }
}
.InstrumentFilesInfoName {
  text-align: center;
  padding-top: 5px;
}
</style>
