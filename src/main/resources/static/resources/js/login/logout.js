layui.use(['element', 'layer', 'layuimini'], function () {
    var $ = layui.jquery,
        element = layui.element,
        layer = layui.layer;

    layuimini.init('api/resource/menu');
    $('.login-out').click(function () {
        layer.msg('退出登录成功', function () {
            window.location = '/page/anon/login';
        });
    });
});