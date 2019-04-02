<template>
  <yd-layout>
    <!--导航栏-->
    <yd-navbar title="发布商品" bgcolor="#3AB07E" color="#FFF">
      <yd-navbar-back-icon slot="left" @click.native="onPop" color="#FFF"
        >返回
      </yd-navbar-back-icon>
    </yd-navbar>
    <!--表单-->
    <yd-cell-group>
      <yd-cell-item>
        <span slot="left">商品名称：</span>
        <yd-input
          slot="right"
          required
          v-model="name"
          max="20"
          placeholder="请输入商品名称"
        ></yd-input>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">数量：</span>
        <yd-input
          slot="right"
          type="number"
          required
          v-model="number"
          placeholder="请输入商品数量"
        ></yd-input>
      </yd-cell-item>
      <yd-cell-item>
        <span slot="left">价格：</span>
        <yd-input
          slot="right"
          type="number"
          required
          v-model="price"
          placeholder="请输入商品价格"
        ></yd-input>
      </yd-cell-item>
      <yd-cell-group>
        <yd-cell-item arrow>
          <span slot="left">所在地区：</span>
          <input
            slot="right"
            type="text"
            @click.stop="show1 = true"
            v-model="address"
            readonly
            placeholder="请选择发货地址"
          />
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">详细地址：</span>
          <yd-input
            slot="right"
            type="text"
            required
            v-model="street"
            placeholder="请输入详细地址"
          ></yd-input>
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">状态：</span>
          <yd-radio-group v-model="state" slot="right">
            <yd-radio val="0">在售</yd-radio>
            <yd-radio val="1">代售</yd-radio>
            <yd-radio val="2">下架</yd-radio>
          </yd-radio-group>
        </yd-cell-item>
        <yd-cell-item arrow type="label">
          <span slot="left">分类：</span>
          <select slot="right" v-model="classify">
            <option value="">请选择性别</option>
            <option value="1">生活用品</option>
            <option value="2">文具</option>
            <option value="3">食品</option>
          </select>
        </yd-cell-item>
        <yd-cell-group title="商品描述">
          <yd-cell-item>
            <yd-textarea
              slot="right"
              placeholder="请输入您的商品的描述"
              maxlength="300"
              v-model="content"
            ></yd-textarea>
          </yd-cell-item>
        </yd-cell-group>
      </yd-cell-group>
      <yd-cityselect
        v-model="show1"
        :callback="result1"
        :items="district"
      ></yd-cityselect>
      <div class="box_cont mtop">
        <p class="mright">商品大图：</p>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
      </div>
      <!--      <div class="box_cont mtop">
        <p class="mright">商品图片：</p>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        <unphoto v-on:imagesUrl="saveUrl"></unphoto>
      </div>-->
      <yd-button size="large" type="primary" @click.native="onSubmit"
        >发布产品</yd-button
      >
    </yd-cell-group>
  </yd-layout>
</template>

<script>
import District from 'ydui-district/dist/jd_province_city_area_id';
import unphoto from '../../components/unphoto.vue';
import { api_addgoods } from '../../components/rest-api/APIKeys';

export default {
  name: 'addGoods',
  components: { unphoto },
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      name: '', //商品名称
      number: '', //商品数量
      price: '', //商品价格
      street: '', //详细地址
      state: '', //状态
      classify: '', //商品分类
      content: '', //商品描述
      photoUrl: '', //图片
      show1: false,
      address: '',
      district: District
    };
  },
  computed: {},
  created: function() {
    if (this.token == '' || this.token == null) {
      this.$showError('请先登录');
      this.$pushRoute('login');
    }
  },
  mounted: function() {},

  activated: function() {},
  methods: {
    onPop() {
      this.$pop();
    },
    onPressPush() {
      this.$pushRoute('details');
    },
    result1(ret) {
      this.address = ret.itemName1 + ' ' + ret.itemName2 + ' ' + ret.itemName3;
    },
    //保存图片的base64
    saveUrl: function(data) {
      this.photoUrl = data;
    },
    onSubmit() {
      let jsonData = {
        token: this.token,
        name: this.name,
        number: this.number,
        price: this.price,
        address: this.address + this.street,
        state: this.state,
        classify: this.classify,
        content: this.content,
        pic: this.photoUrl
      };
      this.$post(api_addgoods, jsonData).then(res => {
        this.$showDialog(false);
        if (res.errno == 0) {
          this.$showSuccess('发布成功');
          this.$pushRoute('ershou');
        } else {
          this.$showError(res.errmsg);
        }
      });
    }
  }
};
</script>

<style scoped>
.box_cont {
  display: flex;
  flex-wrap: wrap;
}

.box_img {
  width: 7.5rem !important;
}
</style>
