<template>
  <div class="full-screen">
    <!-- 主业务 -->
    <div id="app" v-if="!firstLogin || didLogin">
      <navigation>
        <router-view class="child-view" />
      </navigation>
    </div>

    <!-- 大图浏览 -->
    <div v-if="showLightbox" class="full-screen lightbox-view">
      <swiper :options="swiperOption" style="height: 100%">
        <swiper-slide
          v-for="(img, index) in lightboxItems"
          :key="index"
          style="height: 100%"
        >
          <div class="image-container">
            <img :src="img.url" style="width: 100%;" @click="hideLightbox" />
          </div>
        </swiper-slide>
      </swiper>
    </div>

    <!-- 登录 -->
    <Login v-if="firstLogin && !didLogin" class="full-screen login-view" />
  </div>
</template>

<script>
import { mapState } from 'vuex';
import Login from './modules/Login'; //登录
// import VuePreview from 'vue-preview';
// import preview from 'vue-photo-preview';
// import 'vue-photo-preview/dist/skin.css';
//    import 'swiper/dist/css/swiper.css'
//    import {swiper, swiperSlide} from 'vue-awesome-swiper'

export default {
  name: 'App',
  components: {
    Login: Login
    // 'preview': preview,
  },
  data: function() {
    return {
      transitionName: 'none'
      //                swiperOption: {
      //                    slidesPerView: 1,
      //                    spaceBetween: 0,
      //                    mousewheel: true,
      //                }
    };
  },
  computed: mapState({
    didLogin: ({ user }) => {
      return user.user && user.user.token && user.user.token.length > 0;
    },
    showLightbox: ({ lightbox }) => lightbox.show,
    lightboxItems: ({ lightbox }) => lightbox.items,
    firstLogin: ({ base }) => base.firstLogin
  }),
  mounted() {
    // 奶奶滴，还要加延时
    setTimeout(() => {
      this._prepare();
    }, 500);
  },
  created: function() {
    // 显示欢迎页面2s
    // if (this.welcome) {
    //     setTimeout(()=> {
    //         this.welcome = false;
    //     }, 2000);
    // }
  },
  methods: {
    hideLightbox() {
      this.$hideLightbox();
    },

    _prepare() {
      // let {token = '', Personid = ''} = this.$route.query;
      //
      // if (/*token.length <= 0 || */Personid.length <= 0) {
      //     this.showLoading = false;
      //     this.showError = true;
      //     this.desc = '未获取到用户凭证';
      //     return;
      // }
      //
      // const callback = ()=> {
      //
      // }
      //
      // Promise.all([/*this._doLoadUserData(token), */this._doLoadUserHealthData(Personid)])
      //     .then(()=> {
      //         this.showLoading = false;
      //
      //         let {userData, userHealthData} = this;
      //         userData = {}; //临时关掉
      //         if (userData && userHealthData) {
      //             // 缓存用户数据、健康数据
      //             this.$store.dispatch('userDataSave', {userData, userHealthData})
      //                 .then(()=> {
      //                     console.log('处理了以后，才能进入主页面');
      //                     setTimeout(()=> {
      //                         this.showMain = true;
      //                         this.showError = false;
      //                     }, 500);
      //                 });
      //
      //         } else {
      //             this.showMain = false;
      //             this.showError = true;
      //             this.desc = '未获取到用户信息';
      //         }
      //     })
    }
  },
  watch: {}
};
</script>

<style>
#app {
  /*font-family: 'Avenir', Helvetica, Arial, sans-serif;*/
  font-family: 'lucida grande', 'lucida sans unicode', lucida, helvetica,
    'Hiragino Sans GB', 'Microsoft YaHei', 'WenQuanYi Micro Hei', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: left;
  color: #333333;
  font-size: 0.3rem;
  /*-webkit-overflow-scrolling:touch;*/
}

.child-view {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  -moz-box-sizing: border-box;
  box-sizing: border-box;
  transition: all 0.6s cubic-bezier(0.55, 0, 0.1, 1);
}

.full-screen {
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.lightbox-view {
  position: absolute;
  background-color: rgba(0, 0, 0, 0.8);
  z-index: 999;
}

.lightbox-view .image-container {
  height: 100%;
  background-color: transparent;
  display: flex;
  align-items: center;
}

.login-view {
  background-color: #f8f8f8;
}

/*.swiper-box {*/
/*width: 100%;*/
/*height: 100%;*/
/*margin: 0 auto;*/
/*}*/

/*.swiper-item {*/
/*height: 100%;*/
/*text-align: center;*/
/*font-size: 18px;*/
/*background: #fff;*/
/*!* Center slide text vertically *!*/
/*display: -webkit-box;*/
/*display: -ms-flexbox;*/
/*display: -webkit-flex;*/
/*display: flex;*/
/*-webkit-box-pack: center;*/
/*-ms-flex-pack: center;*/
/*-webkit-justify-content: center;*/
/*justify-content: center;*/
/*-webkit-box-align: center;*/
/*-ms-flex-align: center;*/
/*-webkit-align-items: center;*/
/*align-items: center;*/
/*}*/
</style>
