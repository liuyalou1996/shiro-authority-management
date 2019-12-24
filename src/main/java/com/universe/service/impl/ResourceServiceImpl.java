package com.universe.service.impl;

import com.universe.mapper.ResourceMapper;
import com.universe.pojo.domain.ResourceDo;
import com.universe.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<ResourceDo> listResources() {
		return null;
	}

	@Override
	public List<ResourceDo> getResourcesByUsername(String username) {
		return null;
	}

	@Override
	public List<String> getPermissionsByUsername(String username) {
		return null;
	}

	@Override
	public Integer saveResource(ResourceDo resource) {
		return null;
	}

	@Override
	public Integer updateResource(ResourceDo resource) {
		return null;
	}

	@Override
	public Integer removeResourceByResourceId(Integer resourceId) {
		return null;
	}
}
