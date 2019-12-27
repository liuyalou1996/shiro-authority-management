package com.universe.web.controller;

import com.universe.pojo.dto.response.GenericResponseDto;
import com.wf.captcha.SpecCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@ResponseBody
	@PostMapping(value = "/auth/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponseDto<?> login(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password, true);
		try {
			Subject subject = SecurityUtils.getSubject();
			System.err.println(subject.getSession().getId());
			subject.login(token);
			return GenericResponseDto.builder().resultCode(1).build();
		} catch (AuthenticationException e) {
			return GenericResponseDto.builder().resultCode(0).resultDesc(e.getMessage()).build();
		}
	}

	@ResponseBody
	@GetMapping(value = "/auth/verify-code", produces = MediaType.APPLICATION_JSON_VALUE)
	public GenericResponseDto<String> generateVerifyCode() {
		Session session = SecurityUtils.getSubject().getSession();
		System.err.println(session.getId());
		SpecCaptcha specCaptcha = new SpecCaptcha(130, 50, 5);
		String veiryCode = specCaptcha.text().toLowerCase();
		return GenericResponseDto.<String>builder().resultCode(1).content(veiryCode).build();
	}

}
