package com.universe.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转控制器
 * @author: liuyalou
 * @date: 2019年8月16日
 */
@Controller
@RequestMapping("/page")
public class PageDispatchingController {

	@Controller
	@RequestMapping("/anon")
	static class AnonymousController {

		@RequestMapping("/login")
		public String dispatchToLoginPage() {
			return "login";
		}

		@RequestMapping("/unauthorized")
		public String dispatchToUnauthrorizedPage() {
			return "unauthorized";
		}
	}

	@Controller
	@RequestMapping("/auth")
	static class AuthPassController {

		@RequestMapping("/index")
		public String dispatchToUnauthrorizedPage() {
			return "index";
		}
	}

}
