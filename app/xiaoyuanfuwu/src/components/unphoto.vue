<template>
  <div class="box_light">
    <div class="box_photo" v-if="isImage">
      <img
        src="../assets/delete.png"
        alt=""
        class="box_delete"
        @click="delImg"
      />
      <img
        :src="imagesUrl"
        alt=""
        class="img1"
        @click="onPressLightbox($event, imagesUrl)"
      />
    </div>
    <div class="box_img" @click="uploadPhoto" v-if="!isImage">
      <img src="../assets/add.png" alt="" />
      <div style="color: #d9d9d9;font-size: .26rem;">相机</div>
    </div>
    <!--允许本地相册图片上传,用于浏览器测试-->
    <input
      type="file"
      accept="image/*"
      @change="handleFile"
      class="inputFile"
      v-show="false"
    />
    <!--只允许手机拍照上传-->
    <!--<input
      type="file"
      accept="image/*"
      @change="handleFile"
      class="inputFile"
      v-show="false"
      capture="camera"
    />-->
  </div>
</template>

<script>
import { compressImage } from './tools';
export default {
  name: 'unphoto',
  data() {
    return {
      imagesUrl: '', //拍照上传当前图片的路径
      isImage: false //是否展示当拍照上传按钮
    };
  },
  mounted() {},
  methods: {
    //     //点击上传图片
    uploadPhoto() {
      this.$el.querySelector('.inputFile').click();
    },
    //展示图片
    handleFile(e) {
      let $target = e.target || e.srcElement;
      let file = $target.files[0];
      //压缩图片
      compressImage(file, { quality: 0.2 }).then(imageData => {
        this.imagesUrl = imageData;
        // console.log('当前图片的路径',this.imagesUrl);
        this.$emit('imagesUrl', this.imagesUrl);
        // 清空value (BUG:如果不清空,会导致同一张图片无法二次选择)
        this.$el.querySelector('.inputFile').value = '';
      });
      this.isImage = !this.isImage;
    },
    //     //大图预览
    onPressLightbox(e, img) {
      let photoes = [{ url: img }];
      this.$showLightbox(photoes);
      // fancyBox(e.target, images);
    },
    //删除当前图片
    delImg() {
      this.isImage = !this.isImage;
      this.imagesUrl = '';
      this.$emit('imagesUrl', this.imagesUrl);
    }
  }
};
</script>

<style scoped>
.box_light {
  flex: 1;
  display: flex;
  align-items: center;
  flex-wrap: wrap;
}
.box_img {
  width: calc(95% / 3);
  height: 1.8rem;
  margin-right: 0.1rem;
  flex-direction: column;
  align-items: center;
  text-align: center;
  border: 1px solid #d9d9d9;
  padding: 0.2rem;
  margin-bottom: 0.1rem;
}
.box_img img {
  width: 60%;
  height: 60%;
}
.box_photo {
  width: calc(93% / 3);
  height: 1.8rem;
  margin-right: 0.1rem;
  margin-bottom: 0.1rem;
  position: relative;
}
.box_photo .img1 {
  width: 100%;
  height: 100%;
}
.box_delete {
  position: absolute;
  right: 0;
  top: 0;
  width: 35%;
  height: 35%;
}
</style>
