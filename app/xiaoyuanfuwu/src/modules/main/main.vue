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
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">二手交易</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">短期兼职</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">实习招聘</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">失物招领</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">创业服务</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">表白墙</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
        <span slot="text">社团活动</span>
      </yd-grids-item>
      <yd-grids-item>
        <img slot="icon" src="http://static.ydcss.com/ydui/img/logo.png" />
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
        v-for="(item, key) in list"
        :key="key"
        v-on:click.stop="onPressPush('ershou', item.uid)"
      >
        <yd-list-item>
          <img slot="img" :src="item.img" />
          <span slot="title">{{ item.title }}</span>
          <yd-list-other slot="other">
            <div>
              <span class="demo-list-price"
                ><em>发布者：</em>{{ item.price }}</span
              >
              <span class="demo-list-del-price">简介：{{ item.w_price }}</span>
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
      list: [
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/23/14381920926024616259.jpg',
          title: '标题111标题标题标题标题',
          price: 156.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/21/10172020923917672923.jpg',
          title: '标题222标题标题标题标题',
          price: 256.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/23/15395220917905380014.jpg',
          title: '标题333标题标题标题标题',
          price: 356.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/25/14244120933639105658.jpg',
          title: '标题444标题标题标题标题',
          price: 456.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/26/12365720933909085511.jpg',
          title: '标题555标题标题标题标题',
          price: 556.23,
          w_price: 89.36
        },
        {
          uid: 1212,
          img: '//img1.shikee.com/try/2016/06/19/09430120929215230041.jpg',
          title: '标题666标题标题标题标题',
          price: 656.23,
          w_price: 89.36
        }
      ]
    };
  },
  computed: {},
  created: function() {},
  mounted: function() {},

  activated: function() {},
  methods: {
    onPressPush(route, params = {}) {
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
