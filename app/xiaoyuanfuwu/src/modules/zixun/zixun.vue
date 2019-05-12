<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="新闻资讯" bgcolor="#3AB07E" color="#FFF">
      <div to="#" slot="right">
        <yd-icon
          name="feedback"
          size="25px"
          color="#FFF"
          @click.native="addzixun"
        ></yd-icon>
      </div>
    </yd-navbar>
    <!--轮播图-->
    <yd-slider autoplay="3000">
      <yd-slider-item v-for="(item, key) in advert" :key="key">
        <a v-on:click.stop="onPressPush('qwqw', item.uid)">
          <img :src="item.img" />
        </a>
      </yd-slider-item>
    </yd-slider>

    <h3>校园资讯</h3>
    <yd-list theme="5">
      <div
        v-for="(item, key) in zixun"
        :key="key"
        v-on:click.stop="onPressPush('news', item.news_id)"
      >
        <yd-list-item>
          <img slot="img" :src="item.new_pic" />
          <span slot="title">{{ item.news_title }}</span>
          <yd-list-other slot="other">
            <div>
              <span class="demo-list-price"
                ><em>发布者：</em>{{ item.news_user }}</span
              >
              <span class="demo-list-del-price"
                >时间：{{ item.news_time }}</span
              >
            </div>
            <div>content</div>
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
import { selnews } from '../../components/rest-api/APIKeys';

export default {
  name: 'DemoNews',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      //广告轮播
      advert: [
        {
          img: 'http://static.ydcss.com/uploads/ydui/1.jpg',
          uid: '112'
        },
        {
          img: 'http://static.ydcss.com/uploads/ydui/1.jpg',
          uid: '112'
        },
        {
          img: 'http://static.ydcss.com/uploads/ydui/1.jpg',
          uid: '112'
        },
        {
          img: 'http://static.ydcss.com/uploads/ydui/1.jpg',
          uid: '112'
        }
      ],
      //资讯列表
      zixun: []
    };
  },
  computed: {},
  created: function() {
    let jsonData = {};
    this.$post(selnews, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.zixun = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {},
  methods: {
    addzixun() {
      if (this.token == '' || this.token == null) {
        this.$showError('请先登录');
        this.$pushRoute('login');
      } else {
        this.$pushRoute('addzixun');
      }
    },
    onPressPush(route, params) {
      window.localStorage.setItem('news_id', params);
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
