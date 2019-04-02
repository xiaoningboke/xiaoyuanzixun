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
