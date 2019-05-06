const routes = [
  {
    path: '/lost',
    name: 'lost',
    component: resolve => require(['@/modules/lost/demo.vue'], resolve),
    meta: { title: '失物招领' }
  }
];
export default routes;
