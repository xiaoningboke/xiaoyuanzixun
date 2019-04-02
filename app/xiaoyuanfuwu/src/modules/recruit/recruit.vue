<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="兼职招聘" bgcolor="#3AB07E" color="#FFF">
      <router-link to="#" slot="right">
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
    <h3>兼职招聘</h3>
    <yd-tab>
      <yd-tab-panel label="兼职信息">
        <yd-list theme="4">
          <yd-list-item
            v-for="(item, key) in list"
            :key="key"
            v-on:click.native="onPressPush('recruitdetails')"
          >
            <img slot="img" :src="item.img" />
            <span slot="title">{{ item.title }}</span>
            <yd-list-other slot="other">
              <div>
                <span class="demo-list-price"
                  ><em>职位：</em>{{ item.price }}</span
                ><br />
                <span class="demo-list-del-price">简介：{{ item.w_price }}</span
                ><br />
                <span class="demo-list-del-price"
                  >工资：{{ item.w_price }}</span
                >
              </div>
            </yd-list-other>
          </yd-list-item>
        </yd-list>
      </yd-tab-panel>
      <yd-tab-panel label="招聘信息">无</yd-tab-panel>
    </yd-tab>

    <div class="footer">
      到底了~~~
    </div>
    <!--底部导航-->
    <Bottom :recruit="true"></Bottom>
  </yd-layout>
</template>

<script>
export default {
  name: 'Recruit',
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
    onPressPush() {
      this.$pushRoute('recruitdetails');
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
