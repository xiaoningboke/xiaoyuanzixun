<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        :fixed="true"
        style="border-bottom: 0"
        bgcolor="#3AB07E"
        color="#FFF"
        title="资讯详情"
      >
        <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
          >返回</yd-navbar-back-icon
        >
      </yd-navbar>
      <div style="height: 1rem;"></div>
      <!--顶部-->
      <img :src="message.new_pic" />
      <yd-cell-group>
        <yd-cell-item>
          <span slot="left">发布者：</span>
          <span slot="right">{{ message.news_user }}</span>
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">发布时间：</span>
          <span slot="right">{{ message.news_time }}</span>
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">最后修改时间：</span>
          <span slot="right">{{ message.news_exit_time }}</span>
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">{{ message.news_con }}</span>
        </yd-cell-item>
      </yd-cell-group>
    </template>
  </yd-layout>
</template>

<script>
import { findnews } from '../../components/rest-api/APIKeys';

export default {
  message: null,
  name: 'Recruit',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      news_id: window.localStorage.getItem('news_id'),
      message: null,
      radio: '1',
      btns: [
        {
          text: '返回',
          click: () => {
            this.onPop();
          }
        },
        {
          color: '#F00',
          text: '我要报名',
          click: () => {
            this.baoming();
          }
        }
      ]
    };
  },
  computed: {},
  created: function() {
    let jsonData = {
      news_id: this.news_id
    };
    this.$post(findnews, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        console.log(res.data);
        this.message = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {
    //            if (!!this.scrollOffset && this.scrollOffset > 0) {
    //                this.scrollView.scrollTop = this.scrollOffset;
    //            }
  },
  methods: {
    onPop() {
      this.$pop();
    },
    onPressPush(rou) {
      this.$pushRoute(rou);
    }
  }
};
</script>

<style scoped></style>
