layui.use(['element', 'layer', 'layuimini'], function () {
    const element = layui.element,
        layer = layui.layer;
    addListnerOnLogout();
    initIndexPage();
});

function initIndexPage() {
    const configInfo = {
        "clearInfo": {
            "clearUrl": "api/clear.json"
        },
        "homeInfo": {
            "title": "首页",
            "icon": "fa fa-home",
            "href": "page/welcome-1.html?mpi=m-p-i-0"
        },
        "logoInfo": {
            "title": "Shiro权限管理",
            "image": "/static-resources/images/logo.png",
            "href": ""
        },
        "menuInfo": {
            "common": {
                "title": "常规管理",
                "icon": "fa fa-address-book",
                "child": null
            }
        }
    };

    $.getJSON('/api/resource/menu', function (json) {
        configInfo.menuInfo.common.child = json;
        layuimini.initByJson(configInfo);
    })
}

function addListnerOnLogout() {
    $('.login-out').click(function () {
        layer.msg('退出登录成功', function () {
            window.location = '/page/anon/login';
        });
    });
}