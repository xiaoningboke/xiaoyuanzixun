import Vue from 'vue';
import Vuex from 'vuex';
import base from './modules/base'; //基础
import user from './modules/user'; //用户
import lightbox from './modules/lightbox'; //灯箱

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    user,
    base,
    lightbox
  }
});
