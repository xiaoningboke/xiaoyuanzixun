import Vue from 'vue';
import Router from 'vue-router';
import main from './main'; //首页
import ershou from './ershou'; //二手交易
import recruit from './recruit'; //兼职招聘
import user from './user'; //兼职招聘
import lost from './lost'; //失物招领

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/', //自动登录
      name: 'Root',
      component: resolve => require(['@/modules/main/main.vue'], resolve),
      meta: { title: '首页' }
    },
    ...main,
    ...ershou,
    ...recruit,
    ...user,
    ...lost
  ]
});
