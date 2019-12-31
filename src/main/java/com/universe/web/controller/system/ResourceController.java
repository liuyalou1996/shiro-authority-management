package com.universe.web.controller.system;

import com.universe.common.util.ShiroUtils;
import com.universe.pojo.dto.response.GenericRespDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

	@GetMapping(value = "/menu")
	public GenericRespDto<?> getMenuByUsername() {
		String username = (String) ShiroUtils.getSubject().getPrincipal();

		return null;
	}
}
