<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar
      bgcolor="#3AB07E"
      style="border-bottom: 0"
      color="#FFF"
      title="购物车"
    >
      <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
        >返回</yd-navbar-back-icon
      >
    </yd-navbar>
    <!--购物车顶部-->
    <div>
      <div style="padding-left:12px;margin-bottom: 10px;">
        <yd-checkbox v-model="isCheckAll" shape="circle" :change="checkAll"
          >全选</yd-checkbox
        >
      </div>

      <yd-checklist v-model="checklist3" ref="checklistDemo" :callback="change">
        <yd-checklist-item
          v-for="item in shoppingcart"
          :val="item.sc_id"
          :key="item.sc_id"
          :vale="item.price"
        >
          <div style="height: 2rem;line-height: 50px;">
            <yd-flexbox>
              <img :src="item.pic" class="demo-checklist-img" />
              <yd-flexbox-item align="top" style="padding-left: .2rem">
                <span style="font-weight: 700;">{{ item.name }}</span
                ><br />
                <span style="font-size: .05rem">{{ item.content }}</span
                ><br />
              </yd-flexbox-item>
            </yd-flexbox>
          </div>
        </yd-checklist-item>
      </yd-checklist>
    </div>
    <div style="height: 1rem;line-height: 1rem">
      <div style="float: right">
        合计：￥<span style="color: red">{{ price }}</span>
        <span
          style="  padding: 0.2rem;
  background: red;
  color: #ffffff;"
          >结 算</span
        >
      </div>
    </div>
  </yd-layout>
</template>

<script>
import { shoppingcartlist } from '../../components/rest-api/APIKeys';
import 'vue-ydui/dist/ydui.base.css';
export default {
  name: 'Ershou',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      //总价
      price: 0,
      //处理选择
      checklist3: [],
      isCheckAll: false,
      shoppingcart: []
    };
  },
  computed: {},
  created: function() {
    let req = {
      token: this.token
    };
    //购物车列表
    this.$post(shoppingcartlist, req).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.shoppingcart = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {},
  updated: function() {},
  methods: {
    onPressPush() {
      this.$pushRoute('details');
    },
    onPop() {
      this.$pop();
    },
    //选择
    change(value, isCheckAll) {
      this.isCheckAll = isCheckAll;
      this.totalPrice();
    },
    checkAll() {
      this.isCheckAll = !this.isCheckAll;
      this.$refs.checklistDemo.$emit(
        'ydui.checklist.checkall',
        this.isCheckAll
      );
      this.totalPrice();
    },
    //计算总价
    totalPrice() {
      this.price = 0;
      for (var i = 0; i < this.checklist3.length; i++) {
        console.log(this.checklist3[i]);
        this.price += Number(this.checklist3[i]);
      }
    },
    /**
     * 加入购物车成功
     */
    toastSuccess() {
      this.$dialog.toast({
        mes: '加入成功',
        timeout: 1500,
        icon: 'success'
      });
    }
  }
};
</script>

<style scoped>
.footer {
  height: 2rem;
  padding-bottom: 2rem;
  padding-top: 0.3rem;
  text-align: center;
}
.demo-checklist-img {
  width: 2rem;
}
</style>
