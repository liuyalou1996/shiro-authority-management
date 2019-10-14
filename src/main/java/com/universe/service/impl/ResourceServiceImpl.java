package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.ResourceDo;
import com.universe.mapper.ResourceDoMapper;
import com.universe.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ResourceDoMapper resourceMapper;

  @Override
  public List<ResourceDo> listResources() {
    return resourceMapper.listResources();
  }

  @Override
  public List<ResourceDo> getResourcesByUsername(String username) {
    return resourceMapper.getResourcesByUsername(username);
  }

  @Override
  public Integer saveResource(ResourceDo resource) {
    return resourceMapper.saveResource(resource);
  }

  @Override
  public Integer updateResource(ResourceDo resource) {
    return resourceMapper.updateResource(resource);
  }

  @Override
  public Integer removeResourceByResourceId(Integer resourceId) {
    return resourceMapper.removeResourceByResourceId(resourceId);
  }

  @Override
  public List<String> getPermissionsByUsername(String username) {
    return resourceMapper.getPermissionsByUsername(username);
  }

}
