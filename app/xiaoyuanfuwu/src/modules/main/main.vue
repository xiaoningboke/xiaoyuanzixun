<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="首   页" bgcolor="#3AB07E" color="#FFF">
      <router-link to="#" slot="right">
        <yd-icon name="ucenter-outline" size="25px" color="#FFF"></yd-icon>
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
    <!--首页导航-->
    <yd-grids-group :rows="4" title="">
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('ershou')"
        />
        <span slot="text">二手交易</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('recruit')"
        />
        <span slot="text">短期兼职</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('recruit')"
        />
        <span slot="text">实习招聘</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('shiwu')"
        />
        <span slot="text">失物招领</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">创业服务</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('biaobaiqiang')"
        />
        <span slot="text">表白墙</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('shetuan')"
        />
        <span slot="text">社团活动</span>
      </yd-grids-item>
      <yd-grids-item>
        <img
          slot="icon"
          src="http://static.ydcss.com/ydui/img/logo.png"
          v-on:click.stop="onPressPush('zixun')"
        />
        <span slot="text">校园资讯</span>
      </yd-grids-item>
    </yd-grids-group>
    <!--滚动公告-->
    <div class="demo-rollnotice">
      校园资讯：
      <yd-rollnotice autoplay="2000">
        <yd-rollnotice-item v-for="(item, key) in groom" :key="key">
          <span v-on:click="onPressPush('qwqw', item.uid)"
            ><span style="color:#F00;"> 荐 </span>{{ item.title }}</span
          ></yd-rollnotice-item
        >
      </yd-rollnotice>
    </div>
    <h3>校园资讯</h3>
    <yd-list theme="4">
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
    <Bottom v-bind:main="true"></Bottom>
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
      //推荐列表
      groom: [
        {
          title: '荣耀V93月超级钜惠！',
          uid: 1212
        },
        {
          title: '荣耀V93月超级钜惠！',
          uid: 1212
        },
        {
          title: '荣耀V93月超级钜惠！',
          uid: 1212
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
    onPressPush(route, params = {}) {
      window.localStorage.setItem('params', params);
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
