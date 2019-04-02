<template>
  <yd-layout class="box_login">
    <div class="box_top">
      <!--<img src="../assets/logo.png" alt="">-->
      <div class="logo_name">
        校园资讯服务平台
      </div>
    </div>
    <div class="box_content">
      <div class="login_title">
        <div class="login_txt">
          账号登陆
        </div>
        <div class="login_line" />
      </div>
      <div class="login_content">
        <div class="login_user">
          <div class="login_img">
            <!--<img src="../assets/username_03.png" alt="">-->
          </div>
          <input
            type="tel"
            placeholder="请输入您的账号"
            v-model="username"
            :max="11"
            @focus="heighOn"
          />
        </div>
        <div class="login_user">
          <div class="login_img">
            <!--<img src="../assets/password_03.png" alt="">-->
          </div>
          <input
            type="password"
            placeholder="请输入您的密码"
            v-model="password"
            @focus="heighOn"
          />
        </div>
      </div>
      <div style="padding: 0.5rem 1.5rem;">
        <yd-button
          class="box_button"
          color="#fff"
          bgcolor="#1D91E6"
          size="large"
          type="primary"
          @click.native="onPressLogin"
        >
          登 录
        </yd-button>
      </div>
      <!--<yd-button size="large" @click.native="onClickTest">测试</yd-button>-->
    </div>
    <!--<div>登录页面</div>-->
    <!--<yd-button size="large" type="primary" @click.native="onPressLogin">登录</yd-button>-->
    <div class="box_bottom">
      <div style="flex: 1;" />
      <div>孙肖宁</div>
      <div style="flex: 1;" />
    </div>
  </yd-layout>
</template>

<script>
import { api_login } from '../../components/rest-api/APIKeys';
import { mapState } from 'vuex';

export default {
  name: 'login',
  components: {},
  props: [],
  data: function() {
    return {
      username: null,
      password: ''
    };
  },
  computed: mapState({
    openid: ({ user }) => user.user.openid
  }),
  created: function() {},
  mounted: function() {
    setTimeout(() => {
      this.onSetTitle();
    }, 500);
  },

  activated: function() {},

  methods: {
    //进入登录页自动获取
    onSetTitle() {
      //console.log('我开是执行给app传空值');
    },
    //点击登录
    onPressLogin() {
      if (this.username == '') {
        this.$showError('请输入正确的账号');
        return false;
      }
      if (this.password == '') {
        this.$showError('请输入密码');
        return false;
      }

      this.$showDialog();
      let jsonData = {
        username: this.username,
        password: this.password
      };
      this.$post(api_login, jsonData).then(res => {
        this.$showDialog(false);
        if (res.errno == 0) {
          this.$showSuccess('登录成功');
          window.localStorage.setItem('setTitle', res.data.ORG_NAME);
          this.$login(res.data);

          console.log('这个账号的token', res.data.token);
          window.localStorage.setItem('token', res.data.token);
          this.$showSuccess('登录成功');
          this.$login(res.data);
          console.log('执行跳转');
          this.$pushRoute('Root');
        } else {
          this.$showError(res.errmsg);
        }
      });
    },

    //防止键盘遮挡
    heighOn: function() {
      if (/Android/gi.test(navigator.userAgent)) {
        window.addEventListener('resize', function() {
          if (
            document.activeElement.tagName == 'INPUT' ||
            document.activeElement.tagName == 'TEXTAREA'
          ) {
            window.setTimeout(function() {
              document.activeElement.scrollIntoViewIfNeeded();
            }, 0);
          }
        });
      }
    }
  }
};
</script>

<style scoped>
.box_login {
  /*background: url("../assets/loginbg.png") 0 0 no-repeat;*/
  background-size: 100%;
}

.box_top {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding-top: 1rem;
}

.box_top img {
  width: 1.5rem;
  height: 1.5rem;
  /*border-radius:50px;*/
}

.logo_name {
  color: #fff;
  font-size: 0.36rem;
  margin-top: 0.3rem;
  font-family: '微软雅黑';
}

.box_content {
  margin: 0.3rem;
  padding: 0.3rem 0.24rem;
  background: #fff;
  border-radius: 10px;
  border: 1px solid #d9d9d9;
}

.login_title {
  padding: 0.3rem;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

.login_txt {
  font-size: 0.34rem;
  color: #000;
  text-align: center;
}

.login_line {
  width: 1.7rem;
  height: 0.07rem;
  background: #3989e2;
  margin-top: 0.08rem;
}

.login_content {
  padding: 0.24rem 0.15rem;
  margin-top: 0.4rem;
}

.login_user {
  background: #f8f8f8;
  border-radius: 5px;
  display: flex;
  align-items: center;
  padding: 0.24rem 0.3rem;
  margin-bottom: 0.3rem;
}

.login_img {
  width: 0.4rem;
  height: 0.4rem;
  /*margin-top:0.1rem;*/
}

.login_img img {
  width: 100%;
  height: 100%;
}

.login_user input {
  flex: 1;
  border: 0;
  margin-left: 0.2rem;
  /*background: #f8f8f8;*/
}

.box_button {
  border-radius: 200px;
  font-size: 0.36rem;
  font-weight: 600;
  font-family: '微软雅黑';
}

.box_bottom {
  position: absolute;
  bottom: 0.1rem;
  color: gray;
  font-size: 0.28rem;
  display: flex;
  width: 100%;
}
</style>
