package com.universe.service;

import java.util.List;

import com.universe.common.entity.domain.ResourceDo;

public interface ResourceService {

  List<ResourceDo> listResources();

  List<ResourceDo> getResourcesByUsername(String username);

  List<String> getPermissionsByUsername(String username);

  Integer saveResource(ResourceDo resource);

  Integer updateResource(ResourceDo resource);

  Integer removeResourceByResourceId(Integer resourceId);
}
