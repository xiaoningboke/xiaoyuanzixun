webpackJsonp([16],{"7igj":function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var s=o("05op"),n={name:"CameraCorrect",components:{unphoto:o("Qnb9").a},data:function(){return{money:null,remarks:"",photoUrl:"",ssp_id:window.localStorage.getItem("ssp_id")}},mounted:function(){},methods:{saveUrl:function(t){this.photoUrl=t},saveData:function(){var t=this;if(""==this.money||null==this.money)return this.$showError("请填写整改金额"),!1;if(""==this.remarks)return this.$showError("请填写备注"),!1;if(0==this.photoUrl.length)return this.$showError("请上传整改图片"),!1;this.$showDialog();var e={ssp_id:this.ssp_id,zg_money:this.money,zg_remarks:this.remarks,attachment:this.photoUrl};this.$post(s.e,e).then(function(e){t.$showDialog(!1),0==e.errno?(t.$showSuccess("保存成功"),window.history.go(-2)):t.$showError(e.errmsg)})},heighOn:function(){/Android/gi.test(navigator.userAgent)&&window.addEventListener("resize",function(){"INPUT"!=document.activeElement.tagName&&"TEXTAREA"!=document.activeElement.tagName||window.setTimeout(function(){document.activeElement.scrollIntoViewIfNeeded()},0)})}}},r={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("yd-layout",{staticClass:"box_correct"},[o("navBar",{attrs:{slot:"navbar",title:"随手拍隐患整改"},slot:"navbar"}),t._v(" "),o("div",{staticClass:"box_form"},[o("div",{staticClass:"box_cont",staticStyle:{"align-items":"center"}},[o("p",{staticClass:"mright"},[t._v("金额：")]),t._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:t.money,expression:"money"}],staticClass:"box_input",attrs:{type:"number",placeholder:"请输入整改金额(元)"},domProps:{value:t.money},on:{focus:t.heighOn,input:function(e){e.target.composing||(t.money=e.target.value)}}})]),t._v(" "),o("div",{staticClass:"box_cont mtop"},[o("p",{staticClass:"mright"},[t._v("备注：")]),t._v(" "),o("textarea",{directives:[{name:"model",rawName:"v-model",value:t.remarks,expression:"remarks"}],staticClass:"box_txt",attrs:{rows:"5",placeholder:"请输入备注"},domProps:{value:t.remarks},on:{focus:t.heighOn,input:function(e){e.target.composing||(t.remarks=e.target.value)}}})]),t._v(" "),o("div",{staticClass:"box_cont mtop"},[o("p",{staticClass:"mright"},[t._v("附件：")]),t._v(" "),o("unphoto",{on:{imagesUrl:t.saveUrl}})],1),t._v(" "),o("yd-button",{attrs:{size:"large",color:"#fff",bgcolor:"#1D91E6"},nativeOn:{click:function(e){return t.saveData(e)}}},[t._v("保存")])],1)],1)},staticRenderFns:[]};var a=o("VU/8")(n,r,!1,function(t){o("Yg1D")},"data-v-51c9a7d6",null);e.default=a.exports},Yg1D:function(t,e){}});
//# sourceMappingURL=16.3b8b79cfb79a21a50fdd.js.map