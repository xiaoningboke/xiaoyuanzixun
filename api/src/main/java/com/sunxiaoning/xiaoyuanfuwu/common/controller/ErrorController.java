package com.sunxiaoning.xiaoyuanfuwu.common.controller;

import com.sunxiaoning.xiaoyuanfuwu.common.model.APIResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController extends BaseController {

    @RequestMapping(value = "/common/error", method = RequestMethod.GET)
    public APIResponse handleAuthError() {
        return this.fail("验证未通过");
    }
}
