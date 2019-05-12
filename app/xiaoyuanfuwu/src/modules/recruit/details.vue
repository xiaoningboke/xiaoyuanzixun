<template>
  <yd-layout>
    <template>
      <!--导航栏-->
      <yd-navbar
        :fixed="true"
        style="border-bottom: 0"
        bgcolor="#3AB07E"
        color="#FFF"
        title="职位详情"
      >
        <yd-navbar-back-icon slot="left" color="#FFF" @click.native="onPop"
          >返回</yd-navbar-back-icon
        >
      </yd-navbar>
      <div style="height: 1rem;"></div>
      <!--顶部-->
      <yd-preview :buttons="btns">
        <yd-preview-header>
          <div slot="left">兼职名称</div>
          <div slot="right">{{ message.work_name }}</div>
        </yd-preview-header>

        <yd-preview-item>
          <div slot="left">人数</div>
          <div slot="right">{{ message.work_number }}人</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">工资</div>
          <div slot="right">{{ message.work_money }}</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">开始时间</div>
          <div slot="right">{{ message.work_start_time }}</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">结束时间</div>
          <div slot="right">
            {{ message.work_end_time }}
          </div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">工作描述</div>
          <div slot="right">
            {{ message.work_content }}
          </div>
        </yd-preview-item>
      </yd-preview>
    </template>
  </yd-layout>
</template>

<script>
import { findwork, baoming } from '../../components/rest-api/APIKeys';

export default {
  name: 'Recruit',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      work_id: window.localStorage.getItem('work_id'),
      message: null,
      radio: '1',
      btns: [
        {
          text: '返回',
          click: () => {
            this.onPop();
          }
        },
        {
          color: '#F00',
          text: '我要报名',
          click: () => {
            this.baoming();
          }
        }
      ]
    };
  },
  computed: {},
  created: function() {
    let jsonData = {
      work_id: this.work_id
    };
    this.$post(findwork, jsonData).then(res => {
      this.$showDialog(true);
      if (res.errno == 0) {
        this.$showDialog(false);
        this.message = res.data;
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
    baoming(token = (this.token = this.work_id)) {
      let jsonData = {
        work_id: this.message.work_id,
        token: token
      };
      this.$post(baoming, jsonData).then(res => {
        this.$showDialog(true);
        if (res.errno == 0) {
          this.$showDialog(false);
          this.$dialog.toast({
            mes: '报名成功',
            timeout: 1500,
            icon: 'success'
          });
          this.onPop();
        } else {
          this.$showError(res.errmsg);
        }
      });
    },
    onPop() {
      this.$pop();
    },
    onPressPush(rou) {
      this.$pushRoute(rou);
    }
  }
};
</script>

<style scoped></style>
