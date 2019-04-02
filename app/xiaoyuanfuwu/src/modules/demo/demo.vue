<template>
  <yd-layout>
    <div style="display: flex; align-items: center; justify-content: center">
      <img
        src="../../assets/logo.png"
        style="width: 1.6rem; height: 1.6rem; border-radius: .8rem; background-color: #2cae72"
      />
    </div>

    <div style="padding: 0 .3rem 0 .3rem; margin: 0 auto">
      <yd-button size="large">
        显示灯箱
      </yd-button>
    </div>

    <div style="padding:0 .3rem">
      <yd-button size="large" @click.native="onPressLogin">
        测试接口
      </yd-button>
    </div>

    <div style="padding:0 .3rem">
      <yd-button size="large" @click.native="onPressPush">
        测试路由
      </yd-button>
    </div>

    <!--<div v-for="img,index in images" :data-index="index">-->
    <!--<img :src="img.url" @click="onPressLightbox($event)">-->
    <!--</div>-->
  </yd-layout>
</template>

<script>
import { api_login } from '../../components/rest-api/APIKeys';
export default {
  name: 'Demo',
  components: {},
  props: [],
  data: function() {
    return { user: '', age: '' };
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
    //            onPressLightbox(e) {
    //                let images = [
    //                    {url: 'http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1309/15/c7/25716216_1379237027049.jpg'},
    //                    {url: 'http://static.ydcss.com/uploads/lightbox/meizu_s2.jpg'},
    //                    {url: 'http://static.ydcss.com/uploads/lightbox/meizu_s3.jpg'},
    //                    {url: 'http://static.ydcss.com/uploads/lightbox/meizu_s4.jpg'},
    //                    {url: 'http://static.ydcss.com/uploads/lightbox/meizu_s5.jpg'},
    //                    {url: 'http://static.ydcss.com/uploads/lightbox/meizu_s6.jpg'}
    //                ];
    //
    //                this.$showLightbox(images);
    //                // fancyBox(e.target, images);
    //            },

    onPressLogin() {
      this.$showDialog(true);
      let body = {
        username: 'test',
        password: '123456'
      };
      this.$post(api_login, body)
        .then(res => {
          if (res.errno == 0) {
            this.$showSuccess('登录成功');
            window.localStorage.setItem('setTitle', res.data.ORG_NAME);
            this.$login(res.data);
          } else {
            this.$showError(res.errmsg);
          }
        })
        .finally(() => {
          this.$showDialog(false);
        });
    },

    onPressPush() {
      this.$pushRoute('demo-news');
    }
  }
};
</script>

<style scoped></style>
