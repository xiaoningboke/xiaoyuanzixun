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
          >返回
        </yd-navbar-back-icon>
      </yd-navbar>
      <div style="height: 1rem;"></div>
      <!--顶部-->
      <yd-preview :buttons="btns">
        <yd-preview-header>
          <div slot="left">发布者</div>
          <div slot="right">{{ message.lost_user }}</div>
        </yd-preview-header>

        <yd-preview-item>
          <div slot="left">发布时间</div>
          <div slot="right">{{ message.lost_time }}</div>
        </yd-preview-item>
        <yd-preview-item>
          <div slot="left">描述</div>
          <div slot="right">{{ message.lost_con }}</div>
        </yd-preview-item>
        <div v-if="message.lost_state == 1">
          <yd-preview-item>
            <div slot="left">领取人</div>
            <div slot="right">{{ message.lost_owner_id }}</div>
          </yd-preview-item>
          <yd-preview-item>
            <div slot="left">领取时间</div>
            <div slot="right">
              {{ message.lost_receive_time }}
            </div>
          </yd-preview-item>
          <yd-preview-item>
            <div slot="left">工作描述</div>
            <div slot="right">
              {{ message.work_content }}
            </div>
          </yd-preview-item>
        </div>
      </yd-preview>
    </template>
  </yd-layout>
</template>

<script>
import { findshiwu, lingqu } from '../../components/rest-api/APIKeys';

export default {
  name: 'Recruit',
  components: {},
  props: [],
  data: function() {
    return {
      token: window.localStorage.getItem('token'),
      lost_id: window.localStorage.getItem('lost_id'),
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
          text: '立即领取',
          click: () => {
            this.lingqu();
          }
        }
      ]
    };
  },
  computed: {},
  created: function() {
    let jsonData = {
      lost_id: this.lost_id
    };
    this.$post(findshiwu, jsonData).then(res => {
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
    lingqu(token = (this.token = this.work_id)) {
      let jsonData = {
        lost_id: this.message.lost_id,
        token: token
      };
      this.$post(lingqu, jsonData).then(res => {
        this.$showDialog(true);
        if (res.errno == 0) {
          this.$showDialog(false);
          this.$dialog.toast({
            mes: '领取成功',
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
