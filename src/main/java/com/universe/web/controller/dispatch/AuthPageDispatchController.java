package com.universe.web.controller.dispatch;

import com.universe.common.constant.Permissions;
import com.universe.common.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 需授权页面分发控制器
 * @author 刘亚楼
 * @date 2019/12/31
 */
@Controller
@RequestMapping("/page/auth")
public class AuthPageDispatchController {

	@RequiresPermissions(Permissions.Page.INDEX_PAGE_VIEW)
	@RequestMapping("/index")
	public ModelAndView dispatchToIndexPage(ModelAndView modelAndView) {
		modelAndView.setViewName("page/index");
		modelAndView.addObject("username", ShiroUtils.getSubject().getPrincipal());
		return modelAndView;
	}

}
