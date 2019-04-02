let state = {
  show: false, //是否展示灯箱
  items: [] //展示灯箱所需要的图片数组
};

const getters = {};

const mutations = {
  // 显示大图
  ['SHOW_LIGHTBOX'](state, items) {
    if (!!items && items.length > 0) {
      state.show = true;
      state.items = items;
    }
  },

  // 隐藏大图
  ['HIDE_LIGHTBOX'](state) {
    state.show = false;
    state.items = [];
  }
};

const actions = {
  showLightbox({ commit }, payload) {
    commit('SHOW_LIGHTBOX', payload);
  },
  hideLightbox({ commit }, payload) {
    commit('HIDE_LIGHTBOX', payload);
  }
};

export default {
  state,
  mutations,
  getters,
  actions
};
