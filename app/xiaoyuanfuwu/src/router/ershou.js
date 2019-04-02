const routes = [
  {
    path: '/ershou',
    name: 'ershou',
    component: resolve => require(['@/modules/ershou/ershou.vue'], resolve),
    meta: { title: '二手交易' }
  },
  {
    path: '/ershou/details',
    name: 'details',
    component: resolve => require(['@/modules/ershou/details.vue'], resolve),
    meta: { title: '商品详情' }
  },
  {
    path: '/ershou/gouwuche',
    name: 'gouwuche',
    component: resolve => require(['@/modules/ershou/gouwuche.vue'], resolve),
    meta: { title: '购物车' }
  },
  {
    path: '/ershou/addgoods',
    name: 'addgoods',
    component: resolve => require(['@/modules/ershou/addgoods.vue'], resolve),
    meta: { title: '发布产品' }
  }
];
export default routes;
