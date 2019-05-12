<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="兼职招聘" bgcolor="#3AB07E" color="#FFF">
      <div to="#" slot="right">
        <yd-icon
          name="feedback"
          size="25px"
          color="#FFF"
          @click.native="addrecruit"
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
    <h3>兼职招聘</h3>
    <yd-tab>
      <yd-tab-panel label="兼职信息">
        <yd-list theme="4">
          <yd-list-item
            v-for="(item, key) in jianzhi"
            :key="key"
            v-on:click.native="onPressPush(item.work_id, 'recruitdetails')"
          >
            <img slot="img" :src="item.work_pic" />
            <span slot="title">{{ item.work_name }}</span>
            <yd-list-other slot="other">
              <div>
                <span class="demo-list-price"
                  ><em>公司：</em>{{ item.work_unit }}</span
                ><br />
                <span class="demo-list-del-price"
                  >简介：{{ item.work_content }}</span
                ><br />
                <span class="demo-list-del-price"
                  >工资：{{ item.work_money }}</span
                >
              </div>
            </yd-list-other>
          </yd-list-item>
        </yd-list>
      </yd-tab-panel>
      <yd-tab-panel label="招聘信息">
        <yd-list theme="4">
          <yd-list-item
            v-for="(item, key) in zhaopin"
            :key="key"
            v-on:click.native="onPressPush(item.work_id, 'recruitdetails')"
          >
            <img slot="img" :src="item.work_pic" />
            <span slot="title">{{ item.work_name }}</span>
            <yd-list-other slot="other">
              <div>
                <span class="demo-list-price"
                  ><em>公司：</em>{{ item.work_unit }}</span
                ><br />
                <span class="demo-list-del-price"
                  >简介：{{ item.work_content }}</span
                ><br />
                <span class="demo-list-del-price"
                  >工资：{{ item.work_money }}</span
                >
              </div>
            </yd-list-other>
          </yd-list-item>
        </yd-list>
      </yd-tab-panel>
    </yd-tab>

    <div class="footer">
      到底了~~~
    </div>
    <!--底部导航-->
    <Bottom :recruit="true"></Bottom>
  </yd-layout>
</template>

<script>
import { selwork } from '../../components/rest-api/APIKeys';
export default {
  name: 'Recruit',
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
      //兼职列表
      jianzhi: [],
      //招聘列表
      zhaopin: []
    };
  },
  computed: {},
  created: function() {
    let jsonData = {};
    this.$post(selwork, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.jianzhi = res.data.jianzhi;
        this.zhaopin = res.data.zhaopin;
      } else {
        this.$showError(res.errmsg);
      }
    });
  },
  mounted: function() {},

  activated: function() {},
  methods: {
    addrecruit() {
      if (this.token == '' || this.token == null) {
        this.$showError('请先登录');
        this.$pushRoute('login');
      } else {
        this.$pushRoute('addrecruit');
      }
    },
    onPressPush(work_id, uri) {
      window.localStorage.setItem('work_id', work_id);
      this.$pushRoute(uri);
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
