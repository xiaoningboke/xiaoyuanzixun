
//
var native = (function(window, native) {
    native.push_setAlias = function(alias, callback) {
        window.WebViewJavascriptBridge.callHandler('navtive_push_setAlias', {
            'param': alias
        },
        function(responseData) {
            callback && callback(responseData);
        });
    };
    // ==========================  获取版本号  ==========================
    native.version_code = function version_code(callback) {
        window.WebViewJavascriptBridge.callHandler('navtive_version_code', "",
            function(response) {
                callback && callback(response);
            });
    }


    // ==========================  扫一扫  ==========================
    native.barcode_scan = function(callback) {
        window.WebViewJavascriptBridge.callHandler('navtive_barcode_scan', "",
        function(response) {
            callback && callback(response);
        });
    }

    native.connectWebViewJavascriptBridge = function(callback) {
        if (window.WebViewJavascriptBridge) {
            callback(WebViewJavascriptBridge)
        } else {
            document.addEventListener('WebViewJavascriptBridgeReady',
            function() {
                callback(WebViewJavascriptBridge)
            },
            false);
        }

        // =====以下是iOS必须的特殊处理========
        if (window.WVJBCallbacks) { return window.WVJBCallbacks.push(callback); }
        window.WVJBCallbacks = [callback];
        var WVJBIframe = document.createElement('iframe');
        WVJBIframe.style.display = 'none';
        WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
        document.documentElement.appendChild(WVJBIframe);
        setTimeout(function() { document.documentElement.removeChild(WVJBIframe) }, 0);
        // =====以上是iOS必须的特殊处理========
    }

    // ==========================  NFC读取  ==========================
    native.nfc_reader = function nfc_reader(callback) {
        window.WebViewJavascriptBridge.callHandler('navtive_nfc_reader', "",
        function(response) {
            callback && callback(response);
        });
    }

    return native;
})(window, window.native || {});

//
void function(global) {
    native.connectWebViewJavascriptBridge(function(bridge) {
        bridge.init(function(message, responseCallback) {
            console.log('JS got a message', message);

            if (responseCallback) {
                console.log('JS responding with', data);
                responseCallback(data);
            }
        });

        bridge.registerHandler("functionInJs",
        function(data, responseCallback) {
            console.log("data from Java: = " + data);
            if (responseCallback) {
                var responseData = "Javascript Says Right back aka!";
                responseCallback(responseData);
            }
        });
    });

} (this);
