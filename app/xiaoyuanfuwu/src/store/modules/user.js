import { USER_LOGIN, USER_LOGOUT } from '../types';

const kLocalUser = process.env.VUE_APP_LOCAL_STORAGE_USER; // 获取配置的URL地址

const parseLocalUser = () => {
  'use strict';
  const localUser = window.localStorage.getItem(kLocalUser);
  try {
    if (typeof localUser === 'string') {
      console.log('字符串序列化[string] :=>', localUser);
      return JSON.parse(localUser) || {};
    }

    if (typeof localUser === 'object') {
      console.log('字符串序列化[object] :=>', localUser);
      return localUser || {};
    }

    console.log('字符串序列化 :=> 没有数据');
    return {};
  } catch (e) {
    console.log(e);
    return {};
  }
};

let state = {
  // user: {
  //     openid: '111222333',
  // },
  user: parseLocalUser() //基本数据
};

// const fetchUser = () => {
//     "use strict";
//     if (!state.user || !state.user.token)
//     window.localStorage.setItem('LOCAL_USER_STORAGE',user);
// }

const getters = {
  didLogin: state => {
    return !!state.user;
  },

  username: state => {
    return state.user ? state.user.username : '';
  }
};

const mutations = {
  [USER_LOGIN](state, user) {
    if (user && user != null && user != undefined) {
      state.user = user;
      window.localStorage.setItem(kLocalUser, JSON.stringify(user));
    }
    state.didLogin = !!user;
  },
  ['USER_INFO'](state, user) {
    if (user) {
      state.user = user;
    }
  }
};

const actions = {
  userLogin({ commit }, payload) {
    return new Promise(resolve => {
      commit(USER_LOGIN, payload);
      resolve();
    });
  },
  userDataSave({ commit }, { userData, userHealthData, Personid }) {
    return new Promise(resolve => {
      userData && commit('USER_INFO', userData);
      userHealthData && commit('USER_HEALTH', userHealthData);
      !!Personid && Personid.length && commit('USER_PERSONID', Personid);

      resolve();
    });
  },
  userLogout({ commit }, payload) {
    return new Promise(resolve => {
      commit(USER_LOGOUT, payload);
      resolve();
    });
  }
};

export default {
  state,
  mutations,
  getters,
  actions
};
