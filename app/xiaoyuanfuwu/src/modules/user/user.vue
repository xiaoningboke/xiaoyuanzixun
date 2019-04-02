<template>
  <yd-layout>
    <yd-cell-group>
      <yd-cell-item>
        <span slot="left">用户名：</span>
        <yd-input
          slot="right"
          required
          v-model="username"
          max="20"
          placeholder="请输入用户名"
        ></yd-input>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">密码：</span>
        <yd-input
          slot="right"
          type="password"
          v-model="password"
          placeholder="请输入密码"
        ></yd-input>
      </yd-cell-item>
    </yd-cell-group>
  </yd-layout>
</template>

<script>
import { api_login } from '../../components/rest-api/APIKeys';
export default {
  name: 'Demo',
  components: {},
  props: [],
  data: function() {
    return { username: '', password: '' };
  },
  computed: {},
  created: function() {},
  mounted: function() {},

  activated: function() {},
  methods: {
    onPressLogin() {
      this.$showDialog(true);
      let body = {
        username: 'test',
        password: '123456'
      };
      this.$post(api_login, body)
        .then(res => {
          if (res.errno == 0) {
            this.$showSuccess('登录成功');
            window.localStorage.setItem('setTitle', res.data.ORG_NAME);
            this.$login(res.data);
          } else {
            this.$showError(res.errmsg);
          }
        })
        .finally(() => {
          this.$showDialog(false);
        });
    },

    onPressPush() {
      this.$pushRoute('demo-news');
    }
  }
};
</script>

<style scoped></style>
