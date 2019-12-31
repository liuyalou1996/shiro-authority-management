package com.universe.web.controller.dispatch;

import com.universe.common.constant.Permissions;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String dispatchToIndexPage() {
		return "index";
	}

}
