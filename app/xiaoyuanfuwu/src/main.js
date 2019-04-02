import Vue from 'vue';

Vue.config.productionTip = false;

// ================================= UI框架 ================================= //
import YDUI from 'vue-ydui';
import 'vue-ydui/dist/ydui.rem.css';
import 'vue-ydui/dist/ydui.flexible';
import './static/css/app.css';
import './static/css/icons.css';
Vue.use(YDUI);
Vue.prototype.$showDialog = function(show = true, text = '加载中...') {
  if (show) {
    this.$dialog.loading.open(text);
  } else {
    this.$dialog.loading.close();
  }
};
Vue.prototype.$showError = function(text = '') {
  this.$dialog.toast({
    mes: text,
    timeout: 1500,
    icon: 'error'
  });
};

Vue.prototype.$showSuccess = function(text = '', timeout = 1500) {
  this.$dialog.toast({
    mes: text,
    timeout,
    icon: 'success'
  });
};

// ================================= vue-router ================================= //
import router from './router/';
// push操作
Vue.prototype.$push = function(route, params = {}) {
  // console.log(params);
  router.push({ path: route, params });
};
Vue.prototype.$pushRoute = function(route, params = {}) {
  // console.log(params);
  router.push({ name: route, params });
};
// pop操作
Vue.prototype.$pop = function() {
  router.back();
};
router.beforeEach((to, from, next) => {
  // 如果当前显示图片预览,则取消预览
  let { lightbox } = store.state;
  if (lightbox.show) {
    store.dispatch('hideLightbox');
    next(false);
    return;
  }

  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});
// =================================== 使用自定义组件 ================================== //
import Bottom from './modules/public/bottom';
Vue.use(Bottom);
// =================================== 使用vuex ================================== //
import store from './store/';
// 用户登录
Vue.prototype.$login = function(user) {
  // let isPro = process.env.NODE_ENV === 'production';
  // if (isPro && (isIOS || isAndroid)) {
  //   // 设置alias
  //   native.push_setAlias(user.USER_ID, function(res) {});
  // }
  return this.$store.dispatch('userLogin', user);
};
//退出登录
Vue.prototype.$logout = function() {
  return this.$store.dispatch('userLogin', {});
};
// 显示大图预览
Vue.prototype.$showLightbox = function(images) {
  return this.$store.dispatch('showLightbox', images);
};

// 取消大图预览
Vue.prototype.$hideLightbox = function() {
  return this.$store.dispatch('hideLightbox');
};

// =================================== 使用Navigation ================================== //
import Navigation from './components/navigation/';
Vue.use(Navigation, { router, store });

// push操作
Vue.prototype.$push = function(route, params = {}) {
  // console.log(params);
  router.push({ path: route, params });
};
Vue.prototype.$pushRoute = function(route, params = {}) {
  // console.log(params);
  router.push({ name: route, params });
};
// pop操作
Vue.prototype.$pop = function() {
  router.back();
};
router.beforeEach((to, from, next) => {
  // 如果当前显示图片预览,则取消预览
  let { lightbox } = store.state;
  if (lightbox.show) {
    store.dispatch('hideLightbox');
    next(false);
    return;
  }

  /* 路由发生变化修改页面title */
  if (to.meta.title) {
    document.title = to.meta.title;
  }
  next();
});

// ================================= API请求 ================================= //
import api from './components/rest-api/api';
Vue.prototype.$post = function(url, param = {}) {
  let headers = {
    // token: store.state.user.user.token || '',
  };

  delete param.token;
  if (store.state.user.user.token) {
    param.token = store.state.user.user.token;
  }

  if (process.env.NODE_ENV === 'production') {
    return api.POST(url, headers, param);
  }

  console.log(`=========== 数据请求【${url}】 ============`);
  console.log('headers：' + JSON.stringify(headers));
  console.log('参数：' + JSON.stringify(param));
  return api.POST(url, headers, param).then(res => {
    console.log('返回值：', res);
    return res;
  });
};

Vue.prototype.$get = function(url, param = {}) {
  url +=
    '?' +
    Object.keys(param)
      .map(k => `${k}=${param[k]}`)
      .join('&');
  param = {};

  let headers = {
    // token: store.state.user.user.token || '',
  };

  if (process.env.NODE_ENV === 'production') {
    return api.GET(url, headers, param);
  }

  console.log(`=========== 数据请求【${url}】 ============`);
  console.log('headers：' + JSON.stringify(headers));
  console.log('参数：' + JSON.stringify(param));

  return api.GET(url, headers, param).then(res => {
    console.log('返回值：', res);
    return res;
  });
};

import App from './App.vue';
new Vue({
  render: h => h(App),
  store,
  router
}).$mount('#app');
