// 粒子线条背景
$(function () {
        renderLoginPage();
        initVerifyCode();
        refreshVerifyCodeOnImageClick();
        addListenerOnLogin();
    }
);

function renderLoginPage() {
    $('.layui-container').particleground({
        dotColor: '#5cbdaa',
        lineColor: '#5cbdaa'
    });
}

function refreshVerifyCodeOnImageClick() {
    $('#captchaPic').click(function () {
        initVerifyCode();
    });
}

function initVerifyCode() {
    $.ajax({
        method: 'get',
        url: '/api/auth/captcha',
        dataType: 'json',
        success: function (resp) {
            if (resp.resultCode == 1) {
                $('#captchaPic').attr('src', resp.content);
            }
        }
    });
}

function addListenerOnLogin() {
    layui.use(['form'], function () {
        var form = layui.form,
            layer = layui.layer;

        // 登录过期的时候，跳出ifram框架
        if (top.location != self.location) {
            top.location = self.location;
        }

        // 进行登录操作
        form.on('submit(login)', function (data) {
            var fields = data.field;
            if (fields.username == '') {
                layer.msg('用户名不能为空');
                return false;
            }
            if (fields.password == '') {
                layer.msg('密码不能为空');
                return false;
            }
            if (fields.verifyCode == '') {
                layer.msg('验证码不能为空');
                return false;
            }

            login(fields, layer);
            return false;
        });
    });
}

function login(fields, layer) {
    $.ajax({
        method: 'post',
        url: '/api/auth/login',
        data: fields,
        dataType: 'json',
        success: function (resp) {
            if (resp.resultCode == 1) {
                window.location.href = '/page/auth/index';
            } else {
                layer.msg(resp.resultDesc);
            }
        }
    });
}

