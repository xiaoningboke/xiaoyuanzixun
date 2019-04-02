const routes = [
  {
    path: '/user/login',
    name: 'login',
    component: resolve => require(['@/modules/user/login.vue'], resolve),
    meta: { title: '登录' }
  }
];
export default routes;
