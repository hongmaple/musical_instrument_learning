<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :sm="2" class="hidden-xs-only" style="opacity:0;">左侧占位</el-col>
      <el-col :xs="24" :sm="15">
        <el-card style="background-color: rgba(255,255,255,0.9)" class="left-item">

          <el-row>
            <video :src="baseUrl + curriculumDetails.url" controls  width="967" ref="videoPlayer"></video>
          </el-row>
          <el-row style="margin-top: 20px"></el-row>
          <el-col>
            <el-row style="text-align: left;padding-left: 20px;padding-bottom: 20px;">
              课程介绍：
              <span>{{curriculum.content}}</span>
            </el-row>
            <el-row style="text-align: left;padding-left: 20px;padding-bottom: 20px;">
              详细内容：
              <span>{{curriculumDetails.content}}</span>
            </el-row>
          </el-col>
          <el-col>
            <el-button
              size="mini"
              type="text"
              @click="addToMyCourse(curriculumDetails)"
              v-hasPermi="['music:myCurriculum:add']"
            >添加到我的课程</el-button>

            <el-button
              size="mini"
              type="text"
              @click="handelUploadExercise(curriculumDetails)"
              v-hasPermi="['music:myCurriculum:add']"
            >上传练习</el-button>
          </el-col>
        </el-card>
      </el-col>

      <el-col :xs="24" :sm="5">
        <el-card style="background-color: rgba(255,255,255,0.9)" class=" right-item">
          <div slot="header" class="attributes">
            <b>课程列表</b>
          </div>
          <ul class=" blog-type-ul" style="margin-top: 5px;">
            <li class=" blog-type-li" v-for="(item,index) in curriculumDetailsList" :key="index" @click="selectDetails(index)"
                :class="item.id === curriculumDetails.id? 'activeType':''">
              <div style="display: flex;align-items: center">
                {{item.title}}
              </div>
            </li>
          </ul>
          <div class="more" @click="dealCurriculum">
            <i v-if="moreCurriculum" class="el-icon-arrow-down"></i>
            <i v-else class="el-icon-arrow-up"></i>
          </div>
        </el-card>
      </el-col>
      <el-col :sm="2" class="hidden-xs-only" style="opacity:0;">右侧占位</el-col>

    </el-row>

    <!-- 添加或修改课程练习对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="practiceForm" :model="practiceForm" :rules="practiceFormRules" label-width="80px">
        <el-form-item label="练习文件">
          <fileUpload file-size="50" :file-type="fileTypes" limit="1" v-model="practiceForm.url"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitPracticeForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import {listCurriculumDetails} from "@/api/music/curriculumDetails";
import {getCurriculum} from "@/api/music/curriculum";
import { addMyCurriculum } from "@/api/music/myCurriculum";
import {addPractice} from "@/api/music/practice";

export default {
  name: "CurriculumInfo",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 99999,
        cId: null,
        title: null,
        status: null
      },
      curriculumDetailsList: [],
      curriculumDetails: {},
      fullCurriculumDetailsList: [],
      moreCurriculum: true,
      baseUrl: process.env.VUE_APP_BASE_API,
      curriculum: {},
      // 表单参数
      practiceForm: {},
      // 表单校验
      practiceFormRules: {
        url: [
          { required: true, message: "文件", trigger: "change" }
        ],
      },
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      fileTypes: ['jpg', 'png','txt','doc','docx','xls','xlsx','ppt','zip','pdf','mp4'],
    };
  },
  created() {
    this.loading = true;
    this.queryParams.cId = this.$route.query.cId;
    getCurriculum(this.queryParams.cId).then(response => {
      this.curriculum = response.data;
    });
    this.getList();
  },
  methods: {
    /** 查询课程详情列表 */
    getList() {
      this.loading = true;
      listCurriculumDetails(this.queryParams).then(response => {
        this.fullCurriculumDetailsList = response.rows;
        if (response.rows.length>4) {
          this.curriculumDetailsList = this.fullCurriculumDetailsList.slice(0, 4);
        } else {
          this.curriculumDetailsList = this.fullCurriculumDetailsList;
        }
        this.total = response.total;
        this.curriculumDetails = response.rows[0];
        this.loading = false;
      });
    },
    async selectDetails(idIndex) {
      this.loading = true;
      this.$refs.videoPlayer.load(); // 重新加载视频
      this.curriculumDetails = this.curriculumDetailsList[idIndex];
      this.loading = false;
    },
    async dealCurriculum() {
      if (this.moreCurriculum) {
        this.curriculumDetailsList = this.fullCurriculumDetailsList;
      } else {
        this.curriculumDetailsList = this.fullCurriculumDetailsList.slice(0, 4);
      }
      this.moreCurriculum = !this.moreCurriculum
    },
    //添加到我的课程
    addToMyCourse(row) {
      const data = {
        curriculumId: this.curriculum.id
      }
      console.log(data);
      addMyCurriculum(data).then(response => {
        this.$modal.msgSuccess("添加成功");
      });
    },
    resetPracticeForm() {
      this.practiceForm = {};
    },
    //上传练习
    handelUploadExercise(row) {
      this.resetPracticeForm();
      this.practiceForm.type = this.curriculum.type;
      this.practiceForm.curriculumId = this.curriculum.id;
      this.practiceForm.curriculumDetailsId = row.id;
      this.open = true;
      this.title = "添加课程练习";
    },
    /** 提交按钮 */
    submitPracticeForm() {
      this.$refs["practiceForm"].validate(valid => {
        if (valid) {
          addPractice(this.practiceForm).then(response => {
            this.$modal.msgSuccess("新增成功");
            this.open = false;
            this.getList();
          });
        }
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
  }
};
</script>

<style scoped>

.welcome {
  background-color: rgba(0, 0, 0, 0.1);
  border: none;
  height: 90%;
  position: relative;
}

.border {
  width: 812px;
  height: 112px;
  position: absolute;
  top: -6px;
  left: -6px;
  border: 3px solid white;
  box-sizing: border-box;
  animation: clipMe 5s linear infinite;
}

.tit {
  box-sizing: border-box;
  position: relative;
  width: 800px;
  height: 100px;
  line-height: 100px;
  box-shadow: inset 0 0 0 1px white;
  margin: 40px auto;
  margin-top: 80px;
  color: white;
  text-align: center;
  font-size: 50px;
  font-weight: normal;
  letter-spacing: 10px;
}

.intro {
  letter-spacing: 5px;
  line-height: 50px;
  width: 80%;
  margin: 0 auto;
  text-align: center;
  font-weight: normal;
  color: white;
}

.down {
  animation: bounce 2s infinite;
  animation-duration: 3s;
  font-size: 25px;
  position: absolute;
  bottom: 5px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  justify-content: center;
  align-items: center;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  border: 2px solid #fff;
}

.down:hover {
  animation: none;
  cursor: pointer;
  box-shadow: 0 0 20px 0 white;
  transition: all .2s;
}

@keyframes clipMe {

  0%,
  100% {
    clip: rect(0px, 806px, 6px, 0px);
  }

  25% {
    clip: rect(0px, 6px, 112px, 0px);
  }

  50% {
    clip: rect(112px, 812px, 112px, 0px);
  }

  75% {
    clip: rect(0px, 812px, 112px, 806px);
  }
}

@keyframes bounce {

  0%,
  20%,
  50%,
  80%,
  100% {
    transform: translate(-50%, 0);
  }

  40% {
    transform: translate(-50%, -30px);
  }

  60% {
    transform: translate(-50%, -15px);
  }
}


.blog-type-ul {
  padding-left: 10px;
  padding-right: 10px;
  margin-bottom: 0;
  border-radius: 5px;
}

.el-pagination {
  padding-bottom: 20px;
}


.el-card /deep/ .el-card__body {
  padding: 0;
}


.right-item {
  margin-bottom: 20px;
}

.blog-type-li:first-child {
  border-top: 1px solid rgba(179, 216, 255, 0.5);
}

.blog-type-li {
  border-bottom: 1px solid rgba(179, 216, 255, 0.5);
}

.more {
  text-align: center;
  color: #3a8ee6;
  padding: 8px;
}

.more:hover {
  cursor: pointer;
  color: #3a8ee6;
}

.blog-type-li:hover {
  background-color: rgba(213, 255, 255, 0.3);
  cursor: pointer;
}

.activeType {
  background-color: rgba(58, 142, 230, 0.3);
  cursor: pointer;
}

.tags {
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  margin: 15px 13px 0;
  border-bottom: 1px solid rgba(179, 216, 255, 0.5);

}

.tag-item {
  display: flex;
  justify-content: space-around;
  align-items: center;
  margin-left: 5px;
  margin-right: 5px;
  margin-bottom: 10px;
  box-sizing: border-box;
}

.tag {
  background-color: #ecf5ff;
  box-sizing: border-box;
  display: inline-block;
  height: 22px;
  padding: 0 10px;
  line-height: 22px;
  font-size: 10px;
  color: #409eff;
  border-radius: 4px;
  white-space: nowrap;
  border: 1px solid #409eff;
  transition: .2s;
}

.sjx-outer {
  width: 0;
  height: 0;
  border-top: 6px solid transparent;
  border-bottom: 6px solid transparent;
  border-right: 6px solid #409eff;
  position: relative;
  transition: .2s;
}

.sjx-inner {
  border-top: 6px solid transparent;
  border-bottom: 6px solid transparent;
  border-right: 6px solid #ecf5ff;
  top: -6px;
  left: 1px;
  position: absolute;
  transition: .2s;
}

.tag-item:hover,
.activeTag {
  box-sizing: border-box;
}

.tag {
  color: white;
  background-color: #409eff;
  cursor: pointer;
}

.sjx-inner {
  border-right: 6px solid #409eff;
}

.blog-type-li {
  display: flex;
  justify-content: space-between;
  align-items: center;
  line-height: 40px;
}

.recommend-blog {
  overflow: hidden;
  text-overflow: ellipsis;
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  padding-left: 10px;
  padding-right: 10px;
  margin-bottom: 0;
  border-radius: 5px;
}

.recommend-a {
  border-bottom: 1px solid rgba(34, 36, 38, .15);
  line-height: 40px;
  display: block;
  text-decoration: none;
  color: black;
}

.recommend-a:hover {
  color: #3a8ee6;
}

.total {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: larger;
  font-weight: bold;
}

.titleIndex {
  display: flex;
  align-items: center;
}

.el-icon-back {
  font-weight: bolder;
  color: #3a8ee6;
  margin-right: 10px;
}

.el-icon-back:hover {
  cursor: pointer;
}

.blog-content:hover {
  border-left: 5px solid #3a8ee6;
  border-right: 5px solid #3a8ee6;
  background-color: rgba(58, 142, 230, 0.3);
  cursor: pointer;
}

.blog-content {
  padding: 10px;
  height: auto;
  border-bottom: 1px solid rgb(199, 163, 92);
  /*border-bottom: 1px solid rgba(34, 36, 38, .15);*/
  transition: .3s;
}

.el-image {
  border-radius: 5px;
  box-sizing: border-box;
  flex-shrink: 0;
}

.blog-info {
  display: flex;
  align-items: center;
  color: rgba(0, 0, 0, .4);
  font-size: 10px;
}

.user-info {
  display: flex;
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

.blog-date {
  float: right;
  margin-right: 15px;
}

.blog-type {
  float: right;
  margin-left: auto;
}

.blog-tag {
  float: right;
  margin-left: auto;
}

@media screen and (max-width: 768px) {
  .blog-date {
    display: none;
  }

  .welcome {
    width: 100%;
  }

  .border {
    display: none;
  }

  .tit {
    font-size: 2rem;
    width: 100%;
    line-height: 50px;
    letter-spacing: 2px;
    height: auto;
  }

  .intro {
    font-size: 1rem;
    line-height: 30px;
  }

  .el-pagination {
    width: 100%;
  }
}
</style>
