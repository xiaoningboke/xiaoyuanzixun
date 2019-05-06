<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="二手交易" bgcolor="#3AB07E" color="#FFF">
      <router-link to="#" slot="right" @click.native="addGoods()">
        <yd-icon name="feedback" size="25px" color="#FFF"></yd-icon>
      </router-link>
    </yd-navbar>
    <!--轮播图-->
    <yd-slider autoplay="3000">
      <yd-slider-item v-for="(item, key) in advert" :key="key">
        <a v-on:click.stop="onPressPush('qwqw', item.uid)">
          <img :src="item.img" />
        </a>
      </yd-slider-item>
    </yd-slider>
    <h3>二手交易</h3>
    <yd-list theme="3">
      <yd-list-item
        v-for="(item, key) in list"
        :key="key"
        v-on:click.native="onPressPush('details', item.goods_id)"
      >
        <img slot="img" :src="item.pic" />
        <span slot="title">{{ item.title }}</span>
        <yd-list-other slot="other">
          <div>
            <span class="demo-list-price"
              ><em>发布者：</em>{{ item.username }}</span
            >
            <span class="demo-list-del-price">简介：{{ item.name }}</span>
          </div>
          <div>content</div>
        </yd-list-other>
      </yd-list-item>
    </yd-list>
    <div class="footer">
      到底了~~~
    </div>
    <!--底部导航-->
    <Bottom :ershou="true"></Bottom>
  </yd-layout>
</template>

<script>
import { api_goodslist } from '../../components/rest-api/APIKeys';

export default {
  name: 'Ershou',
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
      list: []
    };
  },
  computed: {},
  created: function() {
    let jsonData = {};
    this.$post(api_goodslist, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.list = res.data;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {},
  methods: {
    //发布商品
    addGoods() {
      if (this.token == '' || this.token == null) {
        this.$showError('请先登录');
        this.$pushRoute('login');
      } else {
        this.onPressPush('addgoods');
      }
    },
    onPressPush(rou, params) {
      window.localStorage.setItem('goods_id', params);
      this.$pushRoute(rou);
    }
  }
};
</script>

<style scoped>
.footer {
  height: 2rem;
  padding-bottom: 2rem;
  padding-top: 0.3rem;
  text-align: center;
}
</style>
