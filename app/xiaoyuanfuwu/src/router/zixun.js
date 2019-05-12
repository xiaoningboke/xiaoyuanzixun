const routes = [
  {
    path: '/zixun',
    name: 'zixun',
    component: resolve => require(['@/modules/zixun/zixun.vue'], resolve),
    meta: { title: '资讯' }
  },
  {
    path: '/addzixun',
    name: 'addzixun',
    component: resolve => require(['@/modules/zixun/addzixun.vue'], resolve),
    meta: { title: '添加资讯' }
  },
  {
    path: '/news',
    name: 'news',
    component: resolve => require(['@/modules/zixun/news.vue'], resolve),
    meta: { title: '新闻' }
  },
  {
    path: '/biaobaiqiang',
    name: 'biaobaiqiang',
    component: resolve =>
      require(['@/modules/zixun/biaobaiqiang.vue'], resolve),
    meta: { title: '表白墙' }
  },
  {
    path: '/shetuan',
    name: 'shetuan',
    component: resolve => require(['@/modules/zixun/shetuan.vue'], resolve),
    meta: { title: '社团活动' }
  },
  {
    path: '/shiwu',
    name: 'shiwu',
    component: resolve => require(['@/modules/zixun/shiwu.vue'], resolve),
    meta: { title: '失物招领' }
  },
  {
    path: '/addshiwu',
    name: 'addshiwu',
    component: resolve => require(['@/modules/zixun/addshiwu.vue'], resolve),
    meta: { title: '发布失物招领' }
  },
  {
    path: '/shiwucon',
    name: 'shiwucon',
    component: resolve => require(['@/modules/zixun/shiwucon.vue'], resolve),
    meta: { title: '失物招领详情' }
  }
];
export default routes;
