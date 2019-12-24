package com.universe.mapper;

import java.util.List;

import com.universe.pojo.domain.ResourceDo;

public interface ResourceDoMapper {

  List<ResourceDo> listResources();

  List<ResourceDo> getResourcesByUsername(String username);
  
  List<String> getPermissionsByUsername(String username);

  Integer saveResource(ResourceDo resource);

  Integer updateResource(ResourceDo resource);

  Integer removeResourceByResourceId(Integer resourceId);
}
