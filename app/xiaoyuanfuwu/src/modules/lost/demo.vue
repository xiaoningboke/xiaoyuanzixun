<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        style="border-bottom: 0"
        bgcolor="#3AB07E"
        color="#FFF"
        title="发布失物招领"
      >
        <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
          >返回
        </yd-navbar-back-icon>
      </yd-navbar>
      <yd-cell-group>
        <yd-cell-item>
          <span slot="left">丢失物品：</span>
          <yd-input
            slot="right"
            required
            v-model="work_name"
            max="20"
            placeholder="请输入丢失物品"
          ></yd-input>
        </yd-cell-item>
        <yd-cell-item>
          <span slot="left">描述：</span>
          <yd-input
            slot="right"
            required
            v-model="work_money"
            placeholder="请输入描述"
          ></yd-input>
        </yd-cell-item>

        <yd-cell-item>
          <span slot="left">捡到时间：</span>
          <yd-datetime slot="right" v-model="work_start_time"></yd-datetime>
        </yd-cell-item>

        <yd-cityselect
          v-model="show1"
          :callback="result1"
          :items="district"
        ></yd-cityselect>
        <div class="box_cont mtop">
          <p class="mright">物品图片：</p>
          <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        </div>
      </yd-cell-group>

      <yd-button size="large" type="primary" @click.native="submit"
        >提交</yd-button
      >
    </template>
  </yd-layout>
</template>

<script>
import District from 'ydui-district/dist/jd_province_city_area_id';
import unphoto from '../../components/unphoto.vue';
import { addwork } from '../../components/rest-api/APIKeys';
export default {
  name: 'addRecruit',
  components: { unphoto },
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      work_name: '', //兼职名称
      work_money: '', //兼职工资
      work_number: 1, //兼职人数
      work_unit: '', //用人单位
      work_user: '', //联系人
      work_phone: '', //联系电话
      work_start_time: '', //开始时间
      work_end_time: '', //结束时间
      photoUrl: '', //兼职图片
      work_content: '', //兼职描述
      work_type: '', //工作类型
      radio: '1',
      show1: false,
      district: District
    };
  },
  computed: {},
  created: function() {},
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
    },
    result1(ret) {
      this.address = ret.itemName1 + ' ' + ret.itemName2 + ' ' + ret.itemName3;
    },
    //保存图片的base64
    saveUrl: function(data) {
      this.photoUrl = data;
    },
    submit() {
      let jsonData = {
        token: this.token,
        work_name: this.work_name,
        work_number: this.work_number,
        work_money: this.work_money,
        work_unit: this.work_unit,
        work_start_time: this.work_start_time,
        work_end_time: this.work_end_time,
        work_content: this.work_content,
        work_type: this.work_type,
        work_user: this.work_user,
        work_phone: this.work_phone,
        work_pic: this.photoUrl
      };
      this.$post(addwork, jsonData).then(res => {
        this.$showDialog(true);
        if (res.errno == 0) {
          this.$showDialog(false);
          this.$showSuccess('发布成功');
          this.$pop();
        } else {
          this.$showError(res.errmsg);
        }
      });
    }
  }
};
</script>

<style scoped></style>
