webpackJsonp([4],{"2KhO":function(t,o){},IP7C:function(t,o,r){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var e=r("Qnb9"),n=r("05op"),i={name:"Camera",components:{unphoto:e.a},data:function(){return{orggroup:[],showorg:!1,orginfo:[],photoUrl:"",danger:""}},mounted:function(){this.doLoadOrglist()},methods:{doLoadOrglist:function(){var t=this;this.$post(n.k,{}).then(function(o){0==o.errno&&(t.orggroup=o.data||[])})},onClickOrgname:function(t){this.showorg=!1,this.orginfo=t||[]},saveUrl:function(t){this.photoUrl=t},saveData:function(){var t=this;if(""==this.orginfo.QYMC||void 0==this.orginfo.QYMC)return this.$showError("请选择企业"),!1;if(""==this.danger)return this.$showError("请填写隐患"),!1;if(""==this.photoUrl)return this.$showError("请上传隐患图片"),!1;this.$showDialog();var o={qiye_id:this.orginfo.QIYE_ID,qiye_name:this.orginfo.QYMC,remarks:this.danger,attachment:this.photoUrl};this.$post(n.d,o).then(function(o){t.$showDialog(!1),0==o.errno?(t.$showSuccess("保存成功"),t.$pop()):t.$showError("保存失败")})},heighOn:function(){/Android/gi.test(navigator.userAgent)&&window.addEventListener("resize",function(){"INPUT"!=document.activeElement.tagName&&"TEXTAREA"!=document.activeElement.tagName||window.setTimeout(function(){document.activeElement.scrollIntoViewIfNeeded()},0)})}}},s={render:function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("yd-layout",{staticClass:"box_camera"},[e("div",{staticClass:"box_form"},[e("div",{staticClass:"box_cont",staticStyle:{"align-items":"center"}},[e("p",{staticClass:"mright"},[t._v("企业：")]),t._v(" "),e("div",{staticClass:"box_org",on:{click:function(o){t.showorg=!0}}},[e("input",{staticClass:"box_input",attrs:{readonly:"readonly",type:"text",placeholder:"请选择企业"},domProps:{value:t.orginfo.QYMC},on:{focus:t.heighOn}}),t._v(" "),e("div",{staticStyle:{width:".5rem",height:".5rem"}},[e("img",{staticStyle:{width:"100%",height:"100%"},attrs:{src:r("lP78"),alt:""}})])]),t._v(" "),e("yd-popup",{attrs:{position:"bottom"},model:{value:t.showorg,callback:function(o){t.showorg=o},expression:"showorg"}},[e("div",{staticClass:"org_list"},t._l(t.orggroup,function(o,r){return e("div",{key:r,on:{click:function(r){t.onClickOrgname(o)}}},[t._v(t._s(o.QYMC))])}))])],1),t._v(" "),e("div",{staticClass:"box_cont mtop"},[e("p",{staticClass:"mright"},[t._v("隐患：")]),t._v(" "),e("textarea",{directives:[{name:"model",rawName:"v-model",value:t.danger,expression:"danger"}],staticClass:"box_txt",attrs:{rows:"5",placeholder:"请尽可能详细描述隐患情况"},domProps:{value:t.danger},on:{focus:t.heighOn,input:function(o){o.target.composing||(t.danger=o.target.value)}}})]),t._v(" "),e("div",{staticClass:"box_cont mtop"},[e("p",{staticClass:"mright"},[t._v("附件：")]),t._v(" "),e("unphoto",{on:{imagesUrl:t.saveUrl}})],1),t._v(" "),e("yd-button",{attrs:{size:"large",color:"#fff",bgcolor:"#1D91E6"},nativeOn:{click:function(o){return t.saveData(o)}}},[t._v("保存")])],1)])},staticRenderFns:[]};var a=r("VU/8")(i,s,!1,function(t){r("zypR"),r("2KhO")},"data-v-6b9d7f9f",null);o.default=a.exports},lP78:function(t,o){t.exports="data:image/png;base64,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"},zypR:function(t,o){}});
//# sourceMappingURL=4.7155a1600465921e0d17.js.map