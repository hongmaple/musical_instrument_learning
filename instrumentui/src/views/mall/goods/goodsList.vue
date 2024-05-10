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
      <el-form-item label="价格" prop="price">
        <el-input
          v-model="queryParams.price"
          placeholder="请输入价格"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-form>

    <el-row :gutter="20">
      <el-col :sm="2" class="hidden-xs-only" style="opacity:0;">左侧占位</el-col>
      <el-col :xs="24" :sm="15">
        <el-card style="background-color: rgba(255,255,255,0.9)" class="left-item">
          <el-row type="flex" align="middle" style="flex-wrap: wrap" :gutter="20" v-for="goods in goodsList"
                  :key="goods.id"
                  shadow="never" class="blog-content">
            <div @click="getGoodsInfo(goods.id)">
              <el-col class="img" :xs="24" :sm="6">
                <ImagePreview :width="120" :height="120" :src="goods.images"/>
              </el-col>
              <el-col :xs="24" :sm="15"
                      style="padding-left: 30px;padding-right: 10px;margin-bottom: 5px;margin-top: -5px;">
                <div>
                  <h3>
                    {{ goods.title }}
                  </h3>
<!--                  <div style="margin-bottom: 10px;">-->
<!--                    <span style="color: rgba(0, 0, 0, .4);"> {{ goods.content }}</span>-->
<!--                  </div>-->
                  <div class="blog-info">
                    <div class="user-info">
                      价格：
                      <span class="header" style="color: red"> {{ goods.price }}</span>
                    </div>
                    <div class="user-info">
                      <i class="el-icon-user"></i>
                      <span class="header"> {{ goods.createBy }}</span>
                    </div>
                    <div class="blog-date">
                      <i class="el-icon-date"></i>
                      <span> {{ goods.createTime }}</span>
                    </div>
                  </div>
                </div>
              </el-col>
              <el-col :xs="24" :sm="3" style="padding-left: 30px;padding-right: 10px;margin-bottom: -5px;margin-top: 50px;">
                <el-button type="primary" size="mini" @click="handleBuyNow(goods)">立即购买</el-button>
              </el-col>
            </div>
          </el-row>
          <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                      :limit.sync="queryParams.pageSize" background layout="total, sizes, prev, pager, next, jumper"
                      @pagination="getList" style="margin-bottom: 30px;float: right;margin-right: 10px;"/>
        </el-card>

      </el-col>
    </el-row>

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="buyOpen" width="500px" append-to-body>
      <el-form ref="buyForm" :model="buyForm" :rules="buyRules" label-width="80px">
        <el-form-item label="买家昵称" prop="buyerNick">
          <el-input v-model="buyForm.buyerNick" placeholder="请输入买家昵称" />
        </el-form-item>
        <el-form-item label="买家全称" prop="receiver">
          <el-input v-model="buyForm.receiver" placeholder="请输入买家全称" />
        </el-form-item>
        <el-form-item label="收货人手机" prop="receiverMobile">
          <el-input v-model="buyForm.receiverMobile" placeholder="请输入收货人手机" />
        </el-form-item>
        <el-form-item label="省份" prop="receiverState">
          <el-input v-model="buyForm.receiverState" placeholder="请输入省份" />
        </el-form-item>
        <el-form-item label="城市" prop="receiverCity">
          <el-input v-model="buyForm.receiverCity" placeholder="请输入城市" />
        </el-form-item>
        <el-form-item label="区/县 " prop="receiverDistrict">
          <el-input v-model="buyForm.receiverDistrict" placeholder="请输入区/县 " />
        </el-form-item>
        <el-form-item label="收货地址" prop="receiverAddress">
          <el-input v-model="buyForm.receiverAddress" placeholder="请输入收货地址" />
        </el-form-item>
        <el-form-item label="留言" prop="remark">
          <el-input v-model="buyForm.remark" placeholder="请输入留言" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="goodSubmitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<script>
import { customerListGoods} from "@/api/mall/goods";
import {addOrder} from "@/api/mall/order";

export default {
  name: "goodsList",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 商品表格数据
      goodsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示购买弹出层
      buyOpen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        price: null,
      },
      buyForm: {
        mallGoodsList: []
      }, // 表单校验
      buyRules: {
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
    /** 查询商品列表 */
    getList() {
      this.loading = true;
      customerListGoods(this.queryParams).then(response => {
        this.goodsList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    //跳转详情页
    getGoodsInfo(id) {
      // let routeUrl = this.$router.resolve({
      //   path: '/curriculum/main/info',
      //   query: {
      //     cId: id
      //   }
      // });
      // window.open(routeUrl.href, '_blank');
    },
    // 表单重置
    buyReset() {
      this.buyForm = {
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
        createBy: null,
        mallGoodsList: []
      };
      this.resetForm("buyForm");
    },
    handleBuyNow(goods) {
      this.buyReset();
      this.buyForm.mallGoodsList.push(goods);
      this.buyOpen = true;
      this.title = "立即购买";
    },    /** 提交按钮 */
    goodSubmitForm() {
      this.$refs["buyForm"].validate(valid => {
        if (valid) {
          addOrder(this.buyForm).then(response => {
            this.$modal.msgSuccess("购买成功,请到个人中心查询");
            this.buyOpen = false;
          });
        }
      });
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

.left-item .pagination-container {
  background: rgb(255, 255, 255, 0)
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
  font-size: 18px;
  width: 500px;
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
