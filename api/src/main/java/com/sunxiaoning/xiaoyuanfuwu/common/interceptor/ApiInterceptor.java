package com.sunxiaoning.xiaoyuanfuwu.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiInterceptor implements HandlerInterceptor {

    //
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {

    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    // 拦截器
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 设置跨域访问
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");

        return true;

//        // 其他业务判断
//        String[] noAuthApis = {
//                "/api/account/login", // 登录
//        };
//
//        String requestURI = request.getRequestURI();
//        if (!Arrays.asList(noAuthApis).contains(requestURI)) {
//            System.out.println("需要验证");
//
//            boolean canRequest = true;
//            if (canRequest) {
//                return true;
//            } else {
//                APIResponse error = new APIResponse();
//                error.fail(APIResponse.ERROR_AUTH_FAIL, null);
//                HttpTools.sendJsonMessage(response, error);
//                return false;
//            }
//        }
//
//        System.out.println("拦截器执行完毕");
//        return true;
    }
}
