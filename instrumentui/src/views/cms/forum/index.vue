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
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.cms_blog_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="简介" prop="blogDesc">
        <el-input
          v-model="queryParams.blogDesc"
          placeholder="请输入简介"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['cms:forum:add']"
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
          v-hasPermi="['cms:forum:edit']"
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
          v-hasPermi="['cms:forum:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['cms:forum:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="forumList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
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
      <el-table-column label="阅读" align="center" prop="views" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.cms_blog_status" :value="scope.row.status"/>
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
      <el-table-column label="简介" align="center" prop="blogDesc" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['cms:forum:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['cms:forum:remove']"
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

    <!-- 添加或修改论坛对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="800px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入标题" />
        </el-form-item>
        <el-row>
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
          <el-col :span="16">
            <el-form-item label="简介">
              <el-input type="textarea" v-model="form.blogDesc" :autosize="{ minRows: 7, maxRows: 7}" maxlength="50" show-word-limit placeholder="请输入简介" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item label="内容">
          <!-- 图片用base64存储,url方式移动端会显示异常 -->
          <el-row style="margin-bottom: 20px;">
            <el-col align="right">
              <span v-show="form.contentType ==='2'" style="color: #E6A23C;margin-right: 20px;">Markdown编辑器保存后会覆盖其他富文本编辑器内容</span>
              编辑器：
              <el-select v-model="form.contentType" placeholder="请选择">
                <el-option key="1" label="Quill富文本编辑器" value="1" />
                <el-option key="2" label="CherryMarkdown (推荐)" value="2" />
                <el-option key="3" label="Tinymce富文本编辑器" value="3" />
              </el-select>
            </el-col>
          </el-row>
          <el-row>
            <el-col>
              <cmsEditor v-if="form.contentType ==='1'" v-model="form.content" @getFileId="getFileId" type="base64" :min-height="192" />
              <CherryMarkdown ref="CherryMarkdown" v-if="form.contentType ==='2'" :height='400' v-model='form.contentMarkdown' ></CherryMarkdown>
              <Tinymce v-if="form.contentType ==='3'" :height='400' v-model='form.content'></Tinymce>
            </el-col>
          </el-row>
        </el-form-item>
        <el-row>
          <el-col :span="17">
            <el-form-item label="置顶">
              <el-checkbox v-model="top"></el-checkbox>
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
import { listForum, getForum, delForum, addForum, updateForum } from "@/api/cms/forum";
import {addFileBlogInfo} from "@/api/cms/fileBlogInfo";
import CherryMarkdown from '@/components/CherryMarkdown'
import Tinymce from '@/components/Tinymce'

export default {
  name: "Forum",
  dicts: ['cms_blog_status'],
  components: {
    CherryMarkdown,
    Tinymce
  },
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
      // 论坛表格数据
      forumList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        top: null,
        status: null,
        blogPic: null,
        blogPicLink: null,
        blogDesc: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "标题不能为空", trigger: "blur" }
        ],
        contentType: [
          { required: true, message: "文本编辑器类型不能为空", trigger: "change" }
        ],
      },
      top: false,
      fileIds: [],

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询论坛列表 */
    getList() {
      this.loading = true;
      listForum(this.queryParams).then(response => {
        this.forumList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.$confirm('是否放弃此次编辑？', '系统提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.open = false;
        this.top = false;
        this.reset();
      }).catch(() => {});
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
        contentType: "1",
        content: null,
        contentMarkdown: null,
        top: null,
        views: null,
        status: "0",
        blogPicType: null,
        blogPic: null,
        blogPicLink: null,
        blogDesc: null
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
      this.title = "添加论坛";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getForum(id).then(response => {
        this.form = response.data;
        this.open = true;
        if (this.form.top == 1) {
          this.top = true;
        };
        this.title = "修改论坛";
      });
    },
    /** 发布按钮 */
    releaseForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$modal.confirm('是否确认发布？').then(()=>{
            this.form.type = 1;
            this.form.status = 1;
            if (this.top) {
              this.form.top = 1;
            } else {
              this.form.top = 0;
            }
            if (this.form.contentType === '2'){
              this.setFormContent()
            }
            if (this.form.id != null) {
              updateForum(this.form).then(response => {
                if (this.fileIds.length > 0) {
                  let fileBlogInfo = {
                    blogId: this.form.id,
                    fileIds: this.fileIds
                  };
                  addFileBlogInfo(fileBlogInfo).then(response => {});
                }
                this.$modal.msgSuccess("发布成功");
                this.fileIds.length = 0;
                this.open = false;
                this.getList();
              });
            } else {
              addForum(this.form).then(response => {
                if (this.fileIds.length > 0) {
                  let fileBlogInfo = {
                    blogId: response.data,
                    fileIds: this.fileIds
                  };
                  addFileBlogInfo(fileBlogInfo).then(response => {});
                }
                this.$modal.msgSuccess("发布成功");
                this.fileIds.length = 0;
                this.open = false;
                this.getList();
              });
            }
          }).catch(() => {})
        }
      });
    },
    /** 暂存按钮 */
    saveForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.$modal.confirm('是否确认暂存？').then(()=>{
            this.form.type = 1;
            this.form.status = 0;
            if (this.top) {
              this.form.top = 1;
            } else {
              this.form.top = 0;
            }
            if (this.form.contentType === '2'){
              this.setFormContent()
            }
            if (this.form.id != null) {
              updateForum(this.form).then(response => {
                if (this.fileIds.length > 0) {
                  let fileBlogInfo = {
                    blogId: this.form.id,
                    fileIds: this.fileIds
                  };
                  addFileBlogInfo(fileBlogInfo).then(response => {});
                }
                this.$modal.msgSuccess("暂存成功");
                this.fileIds.length = 0;
                this.open = false;
                this.getList();
              });
            } else {
              addForum(this.form).then(response => {
                if (this.fileIds.length > 0) {
                  let fileBlogInfo = {
                    blogId: response.data,
                    fileIds: this.fileIds
                  };
                  addFileBlogInfo(fileBlogInfo).then(response => {});
                }
                this.$modal.msgSuccess("暂存成功");
                this.fileIds.length = 0;
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
      this.$modal.confirm('是否确认删除论坛编号为"' + ids + '"的数据项？').then(function() {
        return delForum(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('cms/forum/export', {
        ...this.queryParams
      }, `forum_${new Date().getTime()}.xlsx`)
    },getFileId(data) {
      this.fileIds.push(data);
    },
  }
};
</script>
