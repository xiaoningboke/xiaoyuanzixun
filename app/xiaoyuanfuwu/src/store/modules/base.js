import { TAB } from '../types';

let state = {
  tab: 0,
  firstLogin: false //是否需要先登录
};

const mutations = {
  [TAB](state, tab) {
    state.tab = tab;
  }
};

export default {
  state,
  mutations
};
