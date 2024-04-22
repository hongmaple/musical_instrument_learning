<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="总金额 单位分" prop="totalPay">
        <el-input
          v-model="queryParams.totalPay"
          placeholder="请输入总金额 单位分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实付金额 单位分" prop="actualPay">
        <el-input
          v-model="queryParams.actualPay"
          placeholder="请输入实付金额 单位分"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家昵称" prop="buyerNick">
        <el-input
          v-model="queryParams.buyerNick"
          placeholder="请输入买家昵称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家全称" prop="receiver">
        <el-input
          v-model="queryParams.receiver"
          placeholder="请输入买家全称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货人手机" prop="receiverMobile">
        <el-input
          v-model="queryParams.receiverMobile"
          placeholder="请输入收货人手机"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="省份" prop="receiverState">
        <el-input
          v-model="queryParams.receiverState"
          placeholder="请输入省份"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="城市" prop="receiverCity">
        <el-input
          v-model="queryParams.receiverCity"
          placeholder="请输入城市"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区/县 " prop="receiverDistrict">
        <el-input
          v-model="queryParams.receiverDistrict"
          placeholder="请输入区/县 "
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="收货地址" prop="receiverAddress">
        <el-input
          v-model="queryParams.receiverAddress"
          placeholder="请输入收货地址"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable size="small">
          <el-option
            v-for="dict in dict.type.orderType"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="付款时间">
        <el-date-picker
          v-model="daterangePaymentTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="发货时间">
        <el-date-picker
          v-model="daterangeConsignTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="交易完成时间">
        <el-date-picker
          v-model="daterangeEndTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="交易关闭时间">
        <el-date-picker
          v-model="daterangeCloseTime"
          size="small"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['mall:order:add']"
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
          v-hasPermi="['mall:order:edit']"
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
          v-hasPermi="['mall:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['mall:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单id" align="center" prop="orderId" />
      <el-table-column label="总金额 单位分" align="center" prop="totalPay" />
      <el-table-column label="实付金额 单位分" align="center" prop="actualPay" />
      <el-table-column label="买家昵称" align="center" prop="buyerNick" />
      <el-table-column label="买家全称" align="center" prop="receiver" />
      <el-table-column label="收货人手机" align="center" prop="receiverMobile" />
      <el-table-column label="省份" align="center" prop="receiverState" />
      <el-table-column label="城市" align="center" prop="receiverCity" />
      <el-table-column label="区/县 " align="center" prop="receiverDistrict" />
      <el-table-column label="收货地址" align="center" prop="receiverAddress" />
      <el-table-column label="留言" align="center" prop="remark" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.orderType" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="付款时间" align="center" prop="paymentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.paymentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="发货时间" align="center" prop="consignTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.consignTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易完成时间" align="center" prop="endTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交易关闭时间" align="center" prop="closeTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.closeTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新者" align="center" prop="updateBy" />
      <el-table-column label="创建者" align="center" prop="createBy" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['mall:order:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['mall:order:remove']"
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

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="总金额 单位分" prop="totalPay">
          <el-input v-model="form.totalPay" placeholder="请输入总金额 单位分" />
        </el-form-item>
        <el-form-item label="实付金额 单位分" prop="actualPay">
          <el-input v-model="form.actualPay" placeholder="请输入实付金额 单位分" />
        </el-form-item>
        <el-form-item label="买家昵称" prop="buyerNick">
          <el-input v-model="form.buyerNick" placeholder="请输入买家昵称" />
        </el-form-item>
        <el-form-item label="买家全称" prop="receiver">
          <el-input v-model="form.receiver" placeholder="请输入买家全称" />
        </el-form-item>
        <el-form-item label="收货人手机" prop="receiverMobile">
          <el-input v-model="form.receiverMobile" placeholder="请输入收货人手机" />
        </el-form-item>
        <el-form-item label="省份" prop="receiverState">
          <el-input v-model="form.receiverState" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="receiverCity">
          <el-input v-model="form.receiverCity" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="区/县 " prop="receiverDistrict">
          <el-input v-model="form.receiverDistrict" placeholder="请输入区/县 " />
        </el-form-item>
        <el-form-item label="收货地址" prop="receiverAddress">
          <el-input v-model="form.receiverAddress" placeholder="请输入收货地址" />
        </el-form-item>
        <el-form-item label="留言" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入留言" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option
              v-for="dict in dict.type.orderType"
              :key="dict.value"
              :label="dict.label"
:value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="付款时间" prop="paymentTime">
          <el-date-picker clearable size="small"
            v-model="form.paymentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择付款时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="发货时间" prop="consignTime">
          <el-date-picker clearable size="small"
            v-model="form.consignTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择发货时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易完成时间" prop="endTime">
          <el-date-picker clearable size="small"
            v-model="form.endTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交易完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交易关闭时间" prop="closeTime">
          <el-date-picker clearable size="small"
            v-model="form.closeTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择交易关闭时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="${comment}" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入${comment}" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/mall/order";

export default {
  name: "Order",
  dicts: ['orderType'],
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
      // 订单表格数据
      orderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // $comment时间范围
      daterangePaymentTime: [],
      // $comment时间范围
      daterangeConsignTime: [],
      // $comment时间范围
      daterangeEndTime: [],
      // $comment时间范围
      daterangeCloseTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        totalPay: null,
        actualPay: null,
        buyerNick: null,
        receiver: null,
        receiverMobile: null,
        receiverState: null,
        receiverCity: null,
        receiverDistrict: null,
        receiverAddress: null,
        status: null,
        paymentTime: null,
        consignTime: null,
        endTime: null,
        closeTime: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        buyerNick: [
          { required: true, message: "买家昵称不能为空", trigger: "blur" }
        ],
        receiver: [
          { required: true, message: "买家全称不能为空", trigger: "blur" }
        ],
        receiverMobile: [
          { required: true, message: "收货人手机不能为空", trigger: "blur" }
        ],
        receiverState: [
          { required: true, message: "省份不能为空", trigger: "blur" }
        ],
        receiverCity: [
          { required: true, message: "城市不能为空", trigger: "blur" }
        ],
        receiverDistrict: [
          { required: true, message: "区/县 不能为空", trigger: "blur" }
        ],
        receiverAddress: [
          { required: true, message: "收货地址不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangePaymentTime && '' != this.daterangePaymentTime) {
        this.queryParams.params["beginPaymentTime"] = this.daterangePaymentTime[0];
        this.queryParams.params["endPaymentTime"] = this.daterangePaymentTime[1];
      }
      if (null != this.daterangeConsignTime && '' != this.daterangeConsignTime) {
        this.queryParams.params["beginConsignTime"] = this.daterangeConsignTime[0];
        this.queryParams.params["endConsignTime"] = this.daterangeConsignTime[1];
      }
      if (null != this.daterangeEndTime && '' != this.daterangeEndTime) {
        this.queryParams.params["beginEndTime"] = this.daterangeEndTime[0];
        this.queryParams.params["endEndTime"] = this.daterangeEndTime[1];
      }
      if (null != this.daterangeCloseTime && '' != this.daterangeCloseTime) {
        this.queryParams.params["beginCloseTime"] = this.daterangeCloseTime[0];
        this.queryParams.params["endCloseTime"] = this.daterangeCloseTime[1];
      }
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
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
        orderId: null,
        totalPay: null,
        actualPay: null,
        buyerNick: null,
        receiver: null,
        receiverMobile: null,
        receiverState: null,
        receiverCity: null,
        receiverDistrict: null,
        receiverAddress: null,
        remark: null,
        status: null,
        createTime: null,
        paymentTime: null,
        consignTime: null,
        endTime: null,
        closeTime: null,
        isDeleted: null,
        updateBy: null,
        createBy: null
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
      this.daterangePaymentTime = [];
      this.daterangeConsignTime = [];
      this.daterangeEndTime = [];
      this.daterangeCloseTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const orderIds = row.orderId || this.ids;
      this.$modal.confirm('是否确认删除订单编号为"' + orderIds + '"的数据项？').then(function() {
        return delOrder(orderIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('mall/order/export', {
        ...this.queryParams
      }, `order_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
