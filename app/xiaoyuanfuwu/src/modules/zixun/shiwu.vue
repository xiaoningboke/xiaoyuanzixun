<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar
      :fixed="true"
      style="border-bottom: 0"
      bgcolor="#3AB07E"
      color="#FFF"
      title="失物招领"
    >
      <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
        >返回</yd-navbar-back-icon
      >
      <div to="#" slot="right">
        <yd-icon
          name="feedback"
          size="25px"
          color="#FFF"
          @click.native="addshiwu"
        ></yd-icon>
      </div>
    </yd-navbar>
    <h3>失物招领</h3>
    <yd-list theme="4">
      <div
        v-for="(item, key) in shiwu"
        :key="key"
        v-on:click.stop="onPressPush('shiwucon', item.lost_id)"
      >
        <yd-list-item>
          <img
            slot="img"
            src="../../../public/static/img/wlq.png"
            v-if="item.lost_state == 0"
          />
          <img
            slot="img"
            src="../../../public/static/img/ylq.png"
            v-if="item.lost_state == 1"
          />
          <span slot="title">{{ item.news_title }}</span>
          <yd-list-other slot="other">
            <div>
              <span class="demo-list-price"
                ><em>发布者：</em>{{ item.lost_user }}</span
              ><br />
              <span class="demo-list-del-price"
                >发布时间：{{ item.lost_time }}</span
              >
            </div>
          </yd-list-other>
        </yd-list-item>
      </div>
    </yd-list>
    <div class="footer">
      到底了~~~
    </div>
    <!--底部导航-->
    <Bottom v-bind:zixun="true"></Bottom>
  </yd-layout>
</template>

<script>
import { selshiwu } from '../../components/rest-api/APIKeys';

export default {
  name: 'DemoNews',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      //资讯列表
      shiwu: []
    };
  },
  computed: {},
  created: function() {
    let jsonData = {};
    this.$post(selshiwu, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.shiwu = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {},
  methods: {
    onPop() {
      this.$pop();
    },
    addshiwu() {
      if (this.token == '' || this.token == null) {
        this.$showError('请先登录');
        this.$pushRoute('login');
      } else {
        this.$pushRoute('addshiwu');
      }
    },
    onPressPush(route, params) {
      window.localStorage.setItem('lost_id', params);
      this.$pushRoute(route, params);
    }
  }
};
</script>

<style scoped>
.demo-rollnotice {
  display: flex;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  background-color: #fff;
  padding: 0 26px;
  white-space: nowrap;
  margin: 5px 0;
}

.footer {
  height: 2rem;
  padding-bottom: 2rem;
  padding-top: 0.3rem;
  text-align: center;
}
</style>
