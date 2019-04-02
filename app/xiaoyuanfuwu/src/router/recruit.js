const routes = [
  {
    path: '/recruit',
    name: 'recruit',
    component: resolve => require(['@/modules/recruit/recruit.vue'], resolve),
    meta: { title: '兼职招聘' }
  },
  {
    path: '/recruit/details',
    name: 'recruitdetails',
    component: resolve => require(['@/modules/recruit/details.vue'], resolve),
    meta: { title: '职位信息' }
  }
];
export default routes;
