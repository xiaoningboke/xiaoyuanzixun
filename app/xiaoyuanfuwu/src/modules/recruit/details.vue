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
      <yd-preview :buttons="btns">
        <yd-preview-header>
          <div slot="left">兼职名称</div>
          <div slot="right">大棚搭建</div>
        </yd-preview-header>

        <yd-preview-item>
          <div slot="left">人数</div>
          <div slot="right">30人</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">工资</div>
          <div slot="right">100元/天</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">开始时间</div>
          <div slot="right">2019年11月</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">结束时间</div>
          <div slot="right">
            2019年12月
          </div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">工作描述</div>
          <div slot="right">
            工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述工作描述/天
          </div>
        </yd-preview-item>
      </yd-preview>
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
      radio: '1',
      btns: [
        {
          text: '辅助操作',
          click: () => {
            alert('点击咨询');
          }
        },
        {
          color: '#F00',
          text: '我要报名',
          link: { path: '/' }
        }
      ]
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
