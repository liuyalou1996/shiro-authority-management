package com.universe.service;

import com.universe.pojo.domain.ResourceDo;

import java.util.List;

public interface ResourceService {

	List<ResourceDo> listResources();

	List<String> getResourceCodeByUsername(String username);

	boolean saveResource(ResourceDo resource);

	boolean updateResourceByResourceId(ResourceDo resource);

	boolean removeResourceByResourceId(Integer resourceId);
}
