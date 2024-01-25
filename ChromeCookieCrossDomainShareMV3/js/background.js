function configAble(urlStr) {
    return urlStr.search("crm_portal_war_exploded") !== -1
        || urlStr.search("dev-crm-mng.bilibili.co") !== -1
        || urlStr.search("localhost") !== -1;
}

function extracted(request) {
    if (configAble(request.url)) {

        chrome.cookies.getAll(
            {url: "http://cm-mng.bilibili.co"},
            // {url: request.url},
            (cks) => {
                let cookie = cks.map((item) => {
                    setCookies("http://localhost", item.name, item.value, 3600 * 365 * 24);
                    return item.name + "=" + item.value
                }).join(";") + ";";
                console.log(cookie);
            });
    }
}

function extractedWithUrl(urlStr) {

    if (configAble(urlStr)) {
        chrome.cookies.getAll(
            {url: "http://cm-mng.bilibili.co"},
            // {url: request.url},
            function (cks) {
                // 注意 var 和let的区别
                let cookie = cks.map((item) => {
                    setCookies("http://localhost", item.name, item.value, 3600 * 365 * 24);
                    return item.name + "=" + item.value
                }).join(";") + ";";
                console.log(cookie);
            });
    }
}

chrome.runtime.onMessage.addListener(
    (request, sender, sendResponse) => {
        // console.log(request.url);
        extracted(request);
        sendResponse(request.url + "cookie update are trigger");
        return true;
    });


function setCookies(url, name, value, expireSecond) {
    //var exdate = new Date();
    var param = {
        url: url,
        name: name,
        value: value,
        path: '/'
    };
    console.log("name :" + name + "value :" + value);
    if (!!expireSecond) {
        param.expirationDate = new Date().getTime() / 1000 + expireSecond;
    }
    chrome.cookies.set(param, function (cookie) {
        console.log(cookie);
    });
}


chrome.webRequest.onBeforeSendHeaders.addListener(
    function (details) {

        if (configAble(details.url)) {
            // let cookies = extractedWithUrl(details.url);
            let cookieExist = false;
            for (var i = 0; i < details.requestHeaders.length; ++i) {
                if (details.requestHeaders[i].name === 'User-Agent') {
                    details.requestHeaders.splice(i, 1);
                }
                if (details.requestHeaders[i].name === 'Cookie') {
                    cookieExist = true;
                    chrome.cookies.getAll(
                        {url: "http://cm-mng.bilibili.co"},
                        // {url: request.url},
                        function (cks) {
                            // 注意 var 和let的区别
                            let cookie = cks.map((item) => {
                                setCookies("http://localhost", item.name, item.value, 3600 * 365 * 24);
                                return item.name + "=" + item.value
                            }).join(";") + ";";
                            details.requestHeaders[i].value = cookie
                            console.log(cookie);
                        });

                    // details.requestHeaders[i].value = cookies
                    // console.log("details.requestHeaders[i].value", details.requestHeaders[i].value);
                    break;
                }
            }

            if (!cookieExist) {
                chrome.cookies.getAll(
                    {url: "http://cm-mng.bilibili.co"},
                    // {url: request.url},
                    function (cks) {
                        // 注意 var 和let的区别
                        let cookie = cks.map((item) => {
                            setCookies("http://localhost", item.name, item.value, 3600 * 365 * 24);
                            return item.name + "=" + item.value
                        }).join(";") + ";";
                        //todo error
                        // details.requestHeaders[details.requestHeaders.length + 1].name = 'Cookie'
                        // details.requestHeaders[details.requestHeaders.length + 1].value = cookie
                        console.log(cookie);
                    });
            }


        }
        return {requestHeaders: details.requestHeaders};
    },
    {urls: ["<all_urls>"]},
    ["blocking", "requestHeaders", "extraHeaders"]);


// chrome.webRequest.onSendHeaders.addListener(
//     function (details) {
//
//         if (details.url.search("crm_portal_war_exploded") !== -1) {
//             let cookies = extractedWithUrl(details.url);
//             for (var i = 0; i < details.requestHeaders.length; ++i) {
//                 if (details.requestHeaders[i].name === 'User-Agent') {
//                     details.requestHeaders.splice(i, 1);
//                 }
//                 if (details.requestHeaders[i].name === 'Cookie') {
//                     details.requestHeaders[i].value = cookies
//                     console.log("details.requestHeaders[i].value", details.requestHeaders[i].value);
//                     console.log(details.requestHeaders);
//                     break;
//                 }
//
//             }
//         }
//         return {requestHeaders: details.requestHeaders};
//     },
//     {urls: ["<all_urls>"]},
//     ["requestHeaders","extraHeaders"]);