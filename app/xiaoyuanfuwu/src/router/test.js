const routes = [
  {
    path: '/demo',
    name: 'demo',
    component: resolve => require(['@/modules/demo/demo.vue'], resolve),
    meta: { title: '测试' }
  },
  {
    path: '/demo/news',
    name: 'demo-news',
    component: resolve => require(['@/modules/demo/news.vue'], resolve),
    meta: { title: '新闻' }
  }
];
export default routes;
