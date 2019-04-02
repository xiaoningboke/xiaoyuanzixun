// ================================= JS扩展 ================================= //
/// 日期格式化
Date.prototype.format = function(format) {
  var o = {
    'M+': this.getMonth() + 1, //month
    'd+': this.getDate(), //day
    'h+': this.getHours(), //hour
    'm+': this.getMinutes(), //minute
    's+': this.getSeconds(), //second
    'q+': Math.floor((this.getMonth() + 3) / 3), //quarter
    S: this.getMilliseconds() //millisecond
  };
  if (/(y+)/.test(format))
    format = format.replace(
      RegExp.$1,
      (this.getFullYear() + '').substr(4 - RegExp.$1.length)
    );

  for (var k in o)
    if (new RegExp('(' + k + ')').test(format))
      format = format.replace(
        RegExp.$1,
        RegExp.$1.length == 1 ? o[k] : ('00' + o[k]).substr(('' + o[k]).length)
      );

  return format;
};

/// 判断OS体统
const ua =
  navigator.userAgent.toLowerCase() || window.navigator.userAgent.toLowerCase();
global.isWX = () => /MicroMessenger/i.test(ua); //微信端
global.isIOS = () => /(iPhone|iPad|iPod|iOS)/i.test(ua); //苹果家族
global.isAndroid = () => /(android|nexus)/i.test(ua); //安卓家族
global.isWindows = () => /(Windows Phone|windows[\s+]phone)/i.test(ua); //微软家族
global.isBlackBerry = () => /BlackBerry/i.test(ua); //黑莓家族

const isWX = /MicroMessenger/i.test(ua); //微信端
const isIOS = /(iPhone|iPad|iPod|iOS)/i.test(ua); //苹果家族
const isAndroid = /(android|nexus)/i.test(ua); //安卓家族
const isWindows = /(Windows Phone|windows[\s+]phone)/i.test(ua); //微软家族
const isBlackBerry = /BlackBerry/i.test(ua); //黑莓家族

const parseDate = (date, fmt = 'yyyy-MM-dd') => {
  if (date && date.length > 0) {
    if (date !== '0001-01-01T00:00:00') {
      return new Date(date).format(fmt);
    }
    return '';
  }
  return '';
};

//压缩图片
/// 压缩图片
const compressImage = (file, option = { quality: 0.5, maxWidth: 320 }) => {
  if (!file) return '';

  const compressImageAtPath = (path, quality = 0.5, callback) => {
    var img = new Image();
    img.src = path;
    img.onload = function() {
      let that = this;
      // 默认按比例压缩
      let w = that.width,
        h = that.height,
        scale = w / h;
      w = option.maxWidth || w;
      h = option.maxHeight || w / scale;
      // var quality = 0.7;  // 默认图片质量为0.7

      //生成canvas
      var canvas = document.createElement('canvas');
      var ctx = canvas.getContext('2d');

      // 创建属性节点
      let anw = document.createAttribute('width');
      anw.nodeValue = w;
      let anh = document.createAttribute('height');
      anh.nodeValue = h;
      canvas.setAttributeNode(anw);
      canvas.setAttributeNode(anh);

      // 绘制图片
      ctx.drawImage(that, 0, 0, w, h);

      // 图像质量
      // if(obj.quality && obj.quality <= 1 && obj.quality > 0){
      //     quality = obj.quality;
      // }
      // quality值越小，所绘制出的图像越模糊
      const base64 = canvas.toDataURL('image/jpeg', quality);

      // console.log('current image => ', base64.length);

      callback(base64);
    };
  };

  return new Promise(resolve => {
    // 1. 通过file获取到imageData;
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    fileReader.onload = function() {
      compressImageAtPath(this.result, option.quality, function(imageData) {
        resolve(imageData);
      });
    };
  });
};

export {
  isWX,
  isIOS,
  isAndroid,
  isWindows,
  isBlackBerry,
  parseDate,
  compressImage
};
