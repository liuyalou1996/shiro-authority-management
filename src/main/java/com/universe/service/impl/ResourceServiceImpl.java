package com.universe.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.universe.mapper.ResourceMapper;
import com.universe.pojo.domain.ResourceDo;
import com.universe.pojo.dto.response.MenuInfoRespDto;
import com.universe.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

	public void getMenuByUsername(String username) {
		List<ResourceDo> resourceList = resourceMapper.getMenuByUsername(username);
		Map<Integer, List<ResourceDo>> resourceMap = resourceList.stream().collect(Collectors.groupingBy(ResourceDo::getParentId));
		// 顶级资源列表，即目录
		List<ResourceDo> topLevelResourceList = resourceMap.get(0);
		List<MenuInfoRespDto> menuInfoList = new ArrayList<>();
		topLevelResourceList.forEach(resourceDo -> {
			MenuInfoRespDto menuInfo = new MenuInfoRespDto();
			menuInfo.setTitle(resourceDo.getResourceName());
			menuInfo.setHref(resourceDo.getResourceUrl());
			menuInfo.setIcon(resourceDo.getResourceIcon());
			menuInfo.setChild(null);
			List<ResourceDo> secondLevelResourceList = resourceMap.get(resourceDo.getResourceId());
			menuInfoList.add(menuInfo);
		});

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
