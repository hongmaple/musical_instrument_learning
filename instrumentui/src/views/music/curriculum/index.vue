<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入标题"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="type">
        <el-select v-model="queryParams.type" placeholder="请选择类型" clearable size="small">
          <el-option
            v-for="dict in dict.type.curriculum_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option v-for="dict in dict.type.cms_blog_status" :key="dict.value" :label="dict.label"
                     :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['music:curriculum:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['music:curriculum:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['music:curriculum:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['music:curriculum:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="curriculumList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" >
        <template slot-scope="scope">
          <router-link :to="'/curriculum/curriculum-details/index/' + scope.row.id" class="link-type">
            <span>{{ scope.row.id }}</span>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center" prop="title" />
      <el-table-column label="类型" align="center" prop="type">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.curriculum_type" :value="scope.row.type"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.cms_blog_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="首图预览" align="center" prop="blogPic" >
        <template slot-scope="scope">
          <el-image
            v-if="scope.row.blogPicType == '0'"
            style="width: 120px;height: 60px;"
            :src="scope.row.blogPicLink"
            lazy
            :preview-src-list="[scope.row.blogPicLink]">
            <div slot="error" class="image-slot">
              <el-image src="/errorImg.jpg" fit="cover" class="blogPic"></el-image>
            </div>
          </el-image>
          <el-image
            v-if="scope.row.blogPicType == '1'"
            style="width: 120px;height: 60px;"
            :src="scope.row.blogPic"
            lazy
            :preview-src-list="[scope.row.blogPic]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="乐器id" align="center" prop="instrumentId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['music:curriculum:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['music:curriculum:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改课程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1200px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-row>
          <el-col :span="8">
            <el-form-item label="标题" prop="title">
              <el-input v-model="form.title" placeholder="请输入标题" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="类型" prop="type">
              <el-select v-model="form.type" placeholder="请选择类型">
                <el-option
                  v-for="dict in dict.type.curriculum_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="8">
            <el-form-item label="简介" prop="content">
              <el-input v-model="form.content" type="textarea" placeholder="请输入内容" />
            </el-form-item>
          </el-col>
          <el-col :span="8">
            <el-form-item label="首图">
              <el-radio-group v-model="form.blogPicType">
                <el-radio-button label="0">地址</el-radio-button>
                <el-radio-button label="1">上传</el-radio-button>
              </el-radio-group>
              <div v-show="form.blogPicType == '0'" class="tabBlock">
                <el-input v-model="form.blogPicLink" placeholder="请输入图片地址 https://" style="margin-bottom: 10px;" />
                <el-image :src="form.blogPicLink" :preview-src-list="[form.blogPicLink]" fit="cover" class="blogPic" >
                  <div slot="error" class="image-slot">
                    <el-image src="/errorImg.jpg" fit="cover" class="blogPic"></el-image>
                  </div>
                </el-image>
              </div>
              <div v-show="form.blogPicType == '1'" class="tabBlock">
                <imageUpload v-model="form.blogPic" :limit="1" />
              </div>
            </el-form-item>
          </el-col>
          <el-col :span="17">
            <el-form-item label="置顶">
              <el-checkbox v-model="top"></el-checkbox>
            </el-form-item>
          </el-col>
          <el-col :span="17">
            <el-form-item label="乐器">
              <el-select v-model="form.instrumentId" filterable placeholder="请选择">
                <el-option
                  v-for="item in instrumentList"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="releaseForm">发 布</el-button>
        <el-button type="info" @click="saveForm">暂 存</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCurriculum, getCurriculum, delCurriculum, addCurriculum, updateCurriculum } from "@/api/music/curriculum";
import { listInstrument} from "@/api/music/instrument";

export default {
  name: "Curriculum",
  dicts: ['cms_blog_status', 'curriculum_type'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 课程表格数据
      curriculumList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        type: null,
        content: null,
        top: null,
        status: null,
        blogPicType: null,
        blogPic: null,
        blogPicLink: null,
        instrumentId: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        type: [
          { required: true, message: "类型不能为空", trigger: "change" }
        ],
      },
      top: false,
      instrumentList: []
    };
  },
  created() {
    const instrumentParams = {
      pageNum: 1,
      pageSize: 99999,
    }
    this.loading = true;
    listInstrument(instrumentParams).then(response => {
      this.instrumentList = response.rows;
      this.loading = false;
    });
    this.getList();
  },
  methods: {
    /** 查询课程列表 */
    getList() {
      this.loading = true;
      listCurriculum(this.queryParams).then(response => {
        this.curriculumList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
        title: null,
        type: null,
        content: null,
        top: "0",
        status: "0",
        blogPicType: "0",
        blogPic: null,
        blogPicLink: null,
        instrumentId: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加课程";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCurriculum(id).then(response => {
        this.form = response.data;
        if (this.form.top === "1") {
          this.top = true;
        } else {
          this.top = false;
        }
        this.open = true;
        this.title = "修改课程";
      });
    },
    /** 提交按钮 */
    releaseForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.status = "1";
          if (this.form.id != null) {
            if (this.top) {
              this.form.top = "1";
            } else {
              this.form.top = "0";
            }
            updateCurriculum(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCurriculum(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 暂存按钮 */
    saveForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$modal.confirm('是否确认暂存？').then(()=>{
            this.form.status = "0";
            if (this.top) {
              this.form.top = "1";
            } else {
              this.form.top = "0";
            }
            if (this.form.id != null) {
              updateCurriculum(this.form).then(response => {
                this.$modal.msgSuccess("暂存成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCurriculum(this.form).then(response => {
                this.$modal.msgSuccess("暂存成功");
                this.open = false;
                this.getList();
              });
            }
          }).catch(() => {})
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除课程编号为"' + ids + '"的数据项？').then(function() {
        return delCurriculum(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('music/curriculum/export', {
        ...this.queryParams
      }, `curriculum_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
