<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        :fixed="true"
        style="backgroundColor:rgba(0,0,0,0);border-bottom: 0"
      >
        <yd-navbar-back-icon slot="left" @click.native="onPop"
          >返回</yd-navbar-back-icon
        >
        <yd-icon
          slot="right"
          name="shopcart-outline"
          size="20px"
          color="rgb(92, 92, 92)"
          @click.native="onPressPush('gouwuche')"
        ></yd-icon>
      </yd-navbar>
      <!--轮播图-->
      <yd-slider autoplay="3000">
        <yd-slider-item>
          <a href="http://www.ydcss.com">
            <img src="http://static.ydcss.com/uploads/ydui/1.jpg" />
          </a>
        </yd-slider-item>
        <yd-slider-item>
          <a href="http://www.ydcss.com">
            <img src="http://static.ydcss.com/uploads/ydui/2.jpg" />
          </a>
        </yd-slider-item>
        <yd-slider-item>
          <a href="http://www.ydcss.com">
            <img src="http://static.ydcss.com/uploads/ydui/3.jpg" />
          </a>
        </yd-slider-item>
      </yd-slider>
      <!--商品简介-->
      <div class="spjj">
        <span slot="title" class="title">{{ name }}</span>
        <yd-list-other slot="other">
          <div>
            <span class="demo-list-price"><em>¥</em>{{ price }}</span>
          </div>
          <div>{{ classify }}</div>
        </yd-list-other>
      </div>
      <!--发布者，以及服务-->
      <div style="height: 0.1rem"></div>
      <yd-cell-group>
        <yd-cell-item arrow type="a" href="tel:400-888-8888">
          <span slot="left">联系电话</span>
          <span slot="right">00-888-8888</span>
        </yd-cell-item>
        <yd-cell-item
          arrow
          tag="button"
          @click.stop.native="openAlert"
          size="large"
        >
          <span slot="left">发布者信息</span>
          <span slot="right">href会解析为路由</span>
        </yd-cell-item>
      </yd-cell-group>
      <!--产品型号-->
      <yd-accordion accordion>
        <!-- <yd-accordion-item title="产品型号" open>
          <div style="padding: .24rem;">
            &lt;!&ndash;选择项&ndash;&gt;
            <div class="reply">
              <span
                class="radio-box"
                :class="{ on: item.isChecked }"
                v-for="(item, index) in radios"
                :key="item.id"
              >
                <span class="radio" :class="{ on: item.isChecked }">
                  <span
                    :value="item.value"
                    class="input-radio"
                    :checked="item.isChecked"
                    @click="check(index)"
                    type="radio"
                    >{{ item.label }}</span
                  ></span
                >
              </span>
            </div>
          </div>
        </yd-accordion-item>-->
      </yd-accordion>
      <div style="height: .1rem"></div>
      <!--选项卡-->
      <yd-tab>
        <yd-tab-panel label="产品简介">{{ content }}</yd-tab-panel>
        <yd-tab-panel label="评价"
          >我需要三件东西：爱情友谊和图书。然而这三者之间何其相通！炽热的爱情可以充实图书的内容，图书又是人们最忠实的朋友。</yd-tab-panel
        >
      </yd-tab>
      <yd-tabbar slot="tabbar">
        <yd-tabbar-item title="电话">
          <yd-icon name="phone2" slot="icon" size=".3rem"></yd-icon>
        </yd-tabbar-item>
        <yd-tabbar-item title="收藏">
          <yd-icon name="star-outline" slot="icon" size=".3rem"></yd-icon>
        </yd-tabbar-item>
        <div>
          <yd-button class="trade" @click.native="toastSuccess"
            >加入购物车</yd-button
          >
          <yd-button class="shopping">立 即 购 买</yd-button>
        </div>
      </yd-tabbar>
    </template>
  </yd-layout>
</template>

<script>
import { api_goodsmessage } from '../../components/rest-api/APIKeys';

export default {
  name: 'DemoNews',
  components: {},
  props: [],
  data: function() {
    return {
      goods_id: window.localStorage.getItem('goods_id'),
      radio: '1',
      radios: [
        {
          label: '是',
          value: '1',
          isChecked: true
        },
        {
          label: '否',
          value: '2',
          isChecked: false
        },
        {
          label: '全部',
          value: '3',
          isChecked: false
        }
      ],
      name: '',
      price: '',
      w_price: 'ppp',
      number: '',
      classify: '',
      content: ''
    };
  },
  computed: {},
  created: function() {
    let jsonData = {
      goods_id: this.goods_id
    };
    this.$post(api_goodsmessage, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.name = res.data['name'];
        this.number = res.data['number'];
        this.address = res.data['address'];
        this.classify = res.data['classify'];
        this.pic = res.data['pic'];
        this.price = res.data['price'];
        this.content = res.data['content'];
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
    check(index) {
      // 先取消所有选中项
      this.radios.forEach(item => {
        item.isChecked = false;
      });
      //再设置当前点击项选中
      this.radio = this.radios[index].value;
      // 设置值，以供传递
      this.radios[index].isChecked = true;
      console.log(this.radio);
    },
    openAlert() {
      this.$dialog.alert({
        title: '选填标题',
        mes: '消息一出，休想滚动屏幕[移动终端移动终端移动终端移动终端]！'
      });
    },
    onPop() {
      this.$pop();
    },
    onPressPush(rou) {
      this.$pushRoute(rou);
    },
    toastSuccess() {
      this.$dialog.toast({
        mes: '保存成功',
        timeout: 1500,
        icon: 'success'
      });
    }
  }
};
</script>

<style scoped>
.spjj {
  background: #ffffff;
  padding: 0.2rem 0.3rem;
}
title {
  color: #000;
}
.radio-box {
  color: #eeeeee;
  background: orange;
  padding: 0.1rem 0.2rem;
  margin: 0.2rem;
  border: 1px solid #cccccc;
  border-radius: 0.2rem;
}
.on {
  background: red;
}

.icon {
  padding: 0.2rem;
}
.trade {
  padding: 0.2rem;
  background: orange;
  color: #ffffff;
}
.shopping {
  padding: 0.2rem;
  background: red;
  color: #ffffff;
}
</style>
