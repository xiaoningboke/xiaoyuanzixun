webpackJsonp([10],{"5Zpw":function(t,e,o){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=o("05op"),a={name:"DangerCorrect",components:{unphoto:o("Qnb9").a},data:function(){return{token:window.localStorage.getItem("token"),danger:JSON.parse(window.localStorage.getItem("danger")),money:null,remark:"",photoUrl:"",recheck:0}},methods:{saveUrl:function(t){this.photoUrl=t},saveData:function(){var t=this;if(""==this.money||null==this.money||this.money.length<1)return this.$showError("请输入整改金额"),!1;if(""==this.remark)return this.$showError("请输入整改备注"),!1;if(this.photoUrl.length<1)this.$showError("请上传整改图片");else{this.$showDialog();var e={token:this.token,yhjl_id:this.danger.yh_id,yhzgje:this.money,yhzgbz:this.remark,yhzgzp:this.photoUrl,state:this.recheck};this.$post(r.m,e).then(function(e){t.$showDialog(!1),0==e.errno?(t.$showSuccess("保存成功"),window.history.go(-2)):t.$showError("保存失败")})}},heighOn:function(){/Android/gi.test(navigator.userAgent)&&window.addEventListener("resize",function(){"INPUT"!=document.activeElement.tagName&&"TEXTAREA"!=document.activeElement.tagName||window.setTimeout(function(){document.activeElement.scrollIntoViewIfNeeded()},0)})}}},n={render:function(){var t=this,e=t.$createElement,o=t._self._c||e;return o("yd-layout",{staticClass:"box_danger_correct"},[o("navBar",{attrs:{slot:"navbar",title:"隐患整改"},slot:"navbar"}),t._v(" "),o("div",{staticClass:"box_form"},[o("div",{staticClass:"box_cont",staticStyle:{"align-items":"center"}},[o("p",{staticClass:"mright"},[t._v("金额：")]),t._v(" "),o("input",{directives:[{name:"model",rawName:"v-model",value:t.money,expression:"money"}],staticClass:"box_input",attrs:{type:"number",placeholder:"请输入整改金额(元)"},domProps:{value:t.money},on:{focus:t.heighOn,input:function(e){e.target.composing||(t.money=e.target.value)}}})]),t._v(" "),o("div",{staticClass:"box_cont mtop"},[o("p",{staticClass:"mright"},[t._v("备注：")]),t._v(" "),o("textarea",{directives:[{name:"model",rawName:"v-model",value:t.remark,expression:"remark"}],staticClass:"box_txt",attrs:{rows:"5",placeholder:"请输入备注"},domProps:{value:t.remark},on:{focus:t.heighOn,input:function(e){e.target.composing||(t.remark=e.target.value)}}})]),t._v(" "),o("div",{staticClass:"box_cont mtop"},[o("p",{staticClass:"mright"},[t._v("附件：")]),t._v(" "),o("unphoto",{on:{imagesUrl:t.saveUrl}})],1),t._v(" "),o("div",{staticClass:"box_cont mtop"},[o("p",{staticClass:"mright"},[t._v("复查：")]),t._v(" "),o("yd-radio-group",{attrs:{color:"#1D91E6"},model:{value:t.recheck,callback:function(e){t.recheck=e},expression:"recheck"}},[o("yd-radio",{attrs:{val:"0"}},[t._v("需要复查")]),t._v(" "),o("yd-radio",{attrs:{val:"1"}},[t._v("不需要复查")])],1)],1),t._v(" "),o("yd-button",{attrs:{size:"large",color:"#fff",bgcolor:"#1D91E6"},nativeOn:{click:function(e){return t.saveData(e)}}},[t._v("保存")])],1)],1)},staticRenderFns:[]};var s=o("VU/8")(a,n,!1,function(t){o("Rreh"),o("pi+5")},"data-v-479dd2ec",null);e.default=s.exports},Rreh:function(t,e){},"pi+5":function(t,e){}});
//# sourceMappingURL=10.71c6159cac9109f59ce9.js.map