<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        style="border-bottom: 0"
        bgcolor="#3AB07E"
        color="#FFF"
        title="发布资讯"
      >
        <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
          >返回
        </yd-navbar-back-icon>
      </yd-navbar>
      <yd-cell-group>
        <yd-cell-item>
          <span slot="left">标题：</span>
          <yd-input
            slot="right"
            required
            v-model="news_title"
            max="20"
            placeholder="请输入标题"
          ></yd-input>
        </yd-cell-item>

        <yd-cell-item>
          <span slot="left">分类：</span>
          <yd-radio-group v-model="news_classify" slot="right">
            <yd-radio val="0">表白墙</yd-radio>
            <yd-radio val="1">社团活动</yd-radio>
            <yd-radio val="2">校园资讯</yd-radio>
          </yd-radio-group>
        </yd-cell-item>

        <div class="box_cont mtop">
          <p class="mright">图片：</p>
          <unphoto v-on:imagesUrl="saveUrl"></unphoto>
        </div>
      </yd-cell-group>
      <yd-cell-group title="内容">
        <yd-cell-item>
          <yd-textarea
            slot="right"
            placeholder="请输入您的内容"
            v-model="news_con"
            maxlength="100"
          ></yd-textarea>
        </yd-cell-item>
      </yd-cell-group>
      <yd-button size="large" type="primary" @click.native="submit"
        >提交</yd-button
      >
    </template>
  </yd-layout>
</template>

<script>
import unphoto from '../../components/unphoto.vue';
import { addnews } from '../../components/rest-api/APIKeys';
export default {
  name: 'addRecruit',
  components: { unphoto },
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      news_title: '', //标题
      news_con: '', //内容
      photoUrl: '', //图片
      news_classify: '' //分类
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
        news_title: this.news_title,
        news_con: this.news_con,
        new_pic: this.photoUrl,
        news_classify: this.news_classify
      };
      this.$post(addnews, jsonData).then(res => {
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
