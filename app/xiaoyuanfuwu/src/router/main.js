const routes = [
  {
    path: '/main',
    name: 'main',
    component: resolve => require(['@/modules/main/main.vue'], resolve),
    meta: { title: '首页' }
  }
];
export default routes;
