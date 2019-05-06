<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="立即购买" bgcolor="#3AB07E" color="#FFF">
      <yd-navbar-back-icon slot="left" @click.native="onPop" color="#FFF"
        >返回
      </yd-navbar-back-icon>
    </yd-navbar>
    <!--表单-->
    <yd-cell-group>
      <yd-cell-item arrow type="label">
        <span slot="left">收货地址：</span>
        <select slot="right" v-model="address_id">
          <option value="">请选择收货地址</option>
          <option
            v-for="item in address"
            value="item.address_id"
            :key="item.address_id"
            >{{ item.address }}{{ item.street }}--{{ item.tell }}</option
          >
        </select>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">商品名称</span>
        <span slot="right">{{ this.goods.goods_name }}</span>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">商品单价</span>
        <span slot="right">{{ this.goods.goods_price }}元</span>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">商品数量</span>
        <span slot="right">
          <yd-spinner unit="1" v-model="goods_num"></yd-spinner
        ></span>
      </yd-cell-item>
      <yd-cell-group title="备注">
        <yd-cell-item>
          <yd-textarea
            slot="right"
            placeholder="请输入您的备注"
            maxlength="100"
            v-model="remark"
          ></yd-textarea>
        </yd-cell-item>
      </yd-cell-group>
    </yd-cell-group>
    <yd-button size="large" type="primary" @click.native="submit"
      >提交订单</yd-button
    >
  </yd-layout>
</template>

<script>
import { seladdress, addorder } from '../../components/rest-api/APIKeys';
export default {
  name: 'Goumai',
  components: {},
  props: [],
  data: function() {
    return {
      goods_id: window.localStorage.getItem('goods_id'),
      token: window.localStorage.getItem('token'),
      goods: JSON.parse(window.localStorage.getItem('goods')),
      address_id: '', //地址id
      address: '', //地址
      goods_num: 1, //商品数量
      remark: '' //备注
    };
  },
  computed: {},
  created: function() {
    console.log(this.goods);
    let req = {
      token: this.token
    };
    this.$post(seladdress, req).then(res => {
      this.$showDialog(false);
      if (res.errno == 0) {
        this.address = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {
    //            if (!!this.scrollOffset && this.scrollOffset > 0) {
    //                this.scrollView.scrollTop = this.scrollOffset;
    //            }
  },
  methods: {
    //立即购买
    submit() {
      let req = {
        token: this.token,
        order_status: 0,
        goods_id: this.goods_id,
        seller_id: this.goods.seller_id,
        delivery_status: 1,
        remark: this.remark,
        goods_num: this.goods_num,
        goods_price: this.goods.goods_price,
        goods_money: 100,
        address_id: this.address_id
      };
      //提交
      this.$post(addorder, req).then(res => {
        this.$showDialog(true);
        if (res.errno == 0) {
          this.$showDialog(false);

          this.$dialog.toast({
            mes: '提交成功',
            timeout: 1500,
            icon: 'success'
          });
          this.$pop();
        } else {
          this.$showError(res.errmsg);
        }
      });
    },
    onPop() {
      this.$pop();
    },
    onPressPush(rou) {
      this.$pushRoute(rou);
    }
  }
};
</script>

<style scoped></style>
