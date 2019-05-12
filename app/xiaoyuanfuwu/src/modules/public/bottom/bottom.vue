<template>
  <!--底部导航-->
  <yd-tabbar :fixed="true">
    <yd-tabbar-item
      title="首页"
      link="#"
      v-on:click.native="onPressPush('main')"
      :active="main"
    >
      <yd-icon name="home" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item
      title="二手交易"
      link="#"
      v-on:click.native="onPressPush('ershou')"
      :active="ershou"
    >
      <yd-icon name="shopcart-outline" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item
      title="兼职招聘"
      link="#"
      v-on:click.native="onPressPush('recruit')"
      :active="recruit"
    >
      <yd-icon name="order" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item
      title="校园资讯"
      link="#"
      v-on:click.native="onPressPush('zixun')"
      :active="zixun"
    >
      <yd-icon name="discover" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
    <yd-tabbar-item title="个人中心" link="#">
      <yd-icon name="ucenter-outline" slot="icon" size="0.54rem"></yd-icon>
    </yd-tabbar-item>
  </yd-tabbar>
</template>

<script>
export default {
  name: 'bottom',
  props: {
    main: {
      type: Boolean
    },
    ershou: {
      type: Boolean
    },
    recruit: {
      type: Boolean
    },
    zixun: {
      type: Boolean
    }
  },
  data: function() {
    return {
      mainb: false
    };
  },
  methods: {
    onPressPush(route, params = {}) {
      this.$pushRoute(route, params);
    }
  }
};
</script>

<style scoped></style>
