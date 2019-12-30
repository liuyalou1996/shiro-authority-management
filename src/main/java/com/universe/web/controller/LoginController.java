package com.universe.web.controller;

import com.alibaba.druid.util.StringUtils;
import com.universe.pojo.dto.response.GenericResponseDto;
import com.wf.captcha.SpecCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.UnsupportedTokenException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponseDto<?> login(String username, String password, String verifyCode, boolean rememberMe) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		String realVerifyCode = (String) subject.getSession().getAttribute("verifyCode");
		if (!StringUtils.equalsIgnoreCase(verifyCode, realVerifyCode)) {
			throw new UnsupportedTokenException("验证码错误!");
		}

		subject.login(token);
		return GenericResponseDto.builder().resultCode(1).build();
	}

	@ResponseBody
	@GetMapping(value = "/auth/verify-code", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponseDto<String> generateVerifyCode() {
		Session session = SecurityUtils.getSubject().getSession();
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 50, 5);
		session.setAttribute("verifyCode", specCaptcha.text().toLowerCase());

		String verifyCode = specCaptcha.toBase64();
		return GenericResponseDto.<String>builder().resultCode(1).content(verifyCode).build();
	}

}
