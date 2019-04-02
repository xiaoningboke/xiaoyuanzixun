<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        :fixed="true"
        style="border-bottom: 0"
        bgcolor="#3AB07E"
        color="#FFF"
        title="职位详情"
      >
        <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
          >返回</yd-navbar-back-icon
        >
      </yd-navbar>
      <div style="height: 1rem;"></div>
      <!--顶部-->
      <div style="font-weight: 600;padding: .2rem .3rem">
        <div style="height: .6rem;line-height: .6rem;">
          <span style="font-size: .4rem;width: 100%; ">
            <span style="float:left;width: 70%">咨询热线/呼叫客服中心</span>
            <span style="color: #028be6;float: right; width: 30%">3K-5K</span>
          </span>
          <span style="color: #999;font-size: .2rem">招聘信息</span>
          <hr />
        </div>
      </div>
      <!--联系人-->
      <div>
        <div style="float: left">
          <img

            src="http://img0.imgtn.bdimg.com/it/u=1449289102,354173276&fm=26&gp=0.jpg"
            alt=""
          />
        </div>
        <div></div>
      </div>
      <!--底部-->
      <yd-tabbar slot="tabbar">
        <yd-tabbar-item title="拨打电话">
          <yd-icon name="phone2" slot="icon" size=".3rem"></yd-icon>
        </yd-tabbar-item>
      </yd-tabbar>
    </template>
  </yd-layout>
</template>

<script>
export default {
  name: 'Recruit',
  components: {},
  props: [],
  data: function() {
    return {
      radio: '1'
    };
  },
  computed: {},
  created: function() {},
  mounted: function() {},

  activated: function() {
    //            if (!!this.scrollOffset && this.scrollOffset > 0) {
    //                this.scrollView.scrollTop = this.scrollOffset;
    //            }
  },
  methods: {
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
