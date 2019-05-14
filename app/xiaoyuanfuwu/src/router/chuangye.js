const routes = [
  {
    path: '/chuangye',
    name: 'chuangye',
    component: resolve => require(['@/modules/chuangye/chuangye.vue'], resolve),
    meta: { title: '创业列表' }
  },
  {
    path: '/addchuangye',
    name: 'addchuangye',
    component: resolve =>
      require(['@/modules/chuangye/addchuangye.vue'], resolve),
    meta: { title: '添加创业' }
  }
];
export default routes;
