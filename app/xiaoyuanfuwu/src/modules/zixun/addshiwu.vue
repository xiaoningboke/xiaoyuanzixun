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
      <yd-cell-group title="内容">
        <yd-cell-item>
          <yd-textarea
            slot="right"
            placeholder="请输入您的内容"
            v-model="lost_con"
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
import { addshiwu } from '../../components/rest-api/APIKeys';
export default {
  name: 'addRecruit',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      lost_con: '' //内容
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
        lost_con: this.lost_con
      };
      this.$post(addshiwu, jsonData).then(res => {
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
