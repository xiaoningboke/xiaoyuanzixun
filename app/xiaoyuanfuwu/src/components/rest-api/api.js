import axios from 'axios';
import qs from 'qs';

// axios 配置
axios.defaults.timeout = 30000; //请求超时时间，默认30s
axios.defaults.headers.post['Content-Type'] =
  'application/x-www-form-urlencoded;charset=UTF-8';

//POST传参序列化
axios.interceptors.request.use(
  config => {
    if (config.method === 'post') {
      config.data = qs.stringify(config.data);
    }
    return config;
  },
  error => {
    // _.toast("错误的传参", 'fail');
    return Promise.reject(error);
  }
);

//返回状态判断
// axios.interceptors.response.use((res) => {
//   if (!res.data.success) {
//     // _.toast(res.data.msg);
//     return Promise.reject(res);
//   }
//   return res;
// }, (error) => {
//   // _.toast("网络异常", 'fail');
//   return Promise.reject(error);
// });

export function fetch(url, headers, params, method) {
  if (method.toLowerCase() === 'post') {
    return new Promise((resolve, reject) => {
      Object.assign(axios.defaults.headers.common, headers);
      // axios.defaults.headers.common['token'] = AUTH_TOKEN;
      axios
        .post(url, params)
        .then(
          response => {
            resolve(response.data);
          },
          err => {
            reject(err);
          }
        )
        .catch(error => {
          reject(error);
        });
    });
  } else if (method.toLowerCase() === 'get') {
    return new Promise((resolve, reject) => {
      Object.assign(axios.defaults.headers.common, headers);
      // axios.defaults.headers.common['token'] = AUTH_TOKEN;
      axios
        .get(url, params)
        .then(
          response => {
            resolve(response.data);
          },
          err => {
            reject(err);
          }
        )
        .catch(error => {
          reject(error);
        });
    });
  }
}

export default {
  POST(url, headers = {}, params = {}) {
    return fetch(url, headers, params, 'post');
  },

  GET(url, headers = {}, params = {}) {
    return fetch(url, headers, params, 'get');
  }
};
