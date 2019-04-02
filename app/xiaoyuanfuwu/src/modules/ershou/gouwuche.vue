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
          >全选 {{ isCheckAll }}</yd-checkbox
        >
      </div>

      <yd-checklist v-model="checklist3" ref="checklistDemo" :callback="change">
        <yd-checklist-item val="1">
          <div style="height: 2rem;line-height: 50px;">
            <yd-flexbox>
              <img
                src="//img12.360buyimg.com/n1/jfs/t2122/170/1006550413/171711/de099a6f/56399d01N67907749.jpg"
                class="demo-checklist-img"
              />
              <yd-flexbox-item align="top">
                <span style="font-weight: 700;"
                  >[aaa] 类似购物车 / 类似购物车</span
                ><br />
                <span style="font-size: .05rem">点击这边的内容是禁止选中的</span
                ><br />
                <p style="color:#F00;">选中值：{{ checklist3 }}</p>
              </yd-flexbox-item>
            </yd-flexbox>
          </div>
        </yd-checklist-item>
        <yd-checklist-item val="2">
          <div style="height: 50px;line-height: 50px;">2</div>
        </yd-checklist-item>
        <yd-checklist-item val="3">
          <div style="height: 50px;line-height: 50px;">3</div>
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
import 'vue-ydui/dist/ydui.base.css';
export default {
  name: 'Ershou',
  components: {},
  props: [],
  data: function() {
    return {
      //总价
      price: 0,
      //处理选择
      checklist3: [],
      isCheckAll: false,
      //资讯列表
      list: [
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/23/14381920926024616259.jpg',
          title: '标题111标题标题标题标题',
          price: 156.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/21/10172020923917672923.jpg',
          title: '标题222标题标题标题标题',
          price: 256.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/23/15395220917905380014.jpg',
          title: '标题333标题标题标题标题',
          price: 356.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/25/14244120933639105658.jpg',
          title: '标题444标题标题标题标题',
          price: 456.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/26/12365720933909085511.jpg',
          title: '标题555标题标题标题标题',
          price: 556.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/19/09430120929215230041.jpg',
          title: '标题666标题标题标题标题',
          price: 656.23,
          w_price: 89.36
        }
      ]
    };
  },
  computed: {},
  created: function() {},
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
