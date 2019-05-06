export const action = function(api) {
  let apiRoot = process.env.VUE_APP_API_ROOT;
  return `${apiRoot}${api}`;
};

// ============= 以下是demo样例，不要删 ============= //
// export const api_xj_submit = action(`zhaj_zptswc/appxj/addProblem`);//日常巡检提交
// export const api_savejw = action(`zhaj_zptswc/appxj/addCompanyWdAndJd`);//日常巡检保存经纬度
// export const api_recheck1 = action('zhaj_zptswc/appxj/getYhwtByProblem_id'); //复查详情
// export const api_recheck_submit = action('zhaj_zptswc/appxj/updateProblem'); //复查详情提交
//
// export const api_wx_login = action(`/jjzb/JingjiZhibiao/GetJingJiData.ashx?qzdate=QZDATE`); //微信登录
// export const api_jjzb = (month) => action(`/jjzb/JingjiZhibiao/GetJingJiData.ashx?zhibiaoname=&isBrother=ZB&jjdate=${month}`); //首页
//
// // 框架
// export const api_getWxConfig = action(``); //微信配置

export const api_login = action(`account/login`); //登录
export const api_addgoods = action(`goodsmessage/addgoods`); //添加商品
export const api_goodslist = action(`goodsmessage/goodslist`); //查询所有商品
export const api_goodsmessage = action(`goodsmessage/goodsmessage`); //根据id查询商品信息
export const addcollect = action(`goodsmessage/addcollect`); //添加到收藏接口
export const collectlist = action(`goodsmessage/collectlist`); //收藏列表接口
export const delcollect = action(`goodsmessage/delcollect`); //取消收藏的接口
export const addshoppingcart = action(`goodsmessage/addshoppingcart`); //取消收藏的接口
export const shoppingcartlist = action(`goodsmessage/shoppingcartlist`); //购物车列表
export const delshoppingcart = action(`goodsmessage/delshoppingcart`); //取消购物车
export const seladdress = action(`usermessage/seladdress`); //返回该用户的所有地址
export const addorder = action(`order/addorder`); //返回该用户的所有地址
export const addwork = action(`work/addwork`); //发布兼职招聘
