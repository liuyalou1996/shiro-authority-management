package com.universe.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.mapper.ResourceMapper;
import com.universe.pojo.domain.ResourceDo;
import com.universe.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends ServiceImpl<BaseMapper<ResourceDo>, ResourceDo> implements ResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	@Override
	public List<ResourceDo> listResources() {
		return null;
	}

	@Override
	public List<String> getResourceCodeByUsername(String username) {
		return resourceMapper.getResourceCodeByUsername(username);
	}

	@Override
	public boolean saveResource(ResourceDo resource) {
		return super.save(resource);
	}

	@Override
	public boolean updateResourceByResourceId(ResourceDo resource) {
		return super.updateById(resource);
	}

	@Override
	public boolean removeResourceByResourceId(Integer resourceId) {
		return super.removeById(resourceId);
	}
}
