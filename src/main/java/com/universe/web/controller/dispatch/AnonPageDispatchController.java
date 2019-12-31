package com.universe.web.controller.dispatch;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 匿名访问页面分发控制器
 * @author 刘亚楼
 * @date 2019/12/31
 */
@Controller
@RequestMapping("/page/anon")
public class AnonPageDispatchController {

	@RequestMapping("/login")
	public String dispatchToLoginPage() {
		return "page/login";
	}
}
