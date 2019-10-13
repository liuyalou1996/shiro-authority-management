package com.universe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.universe.common.entity.domain.Resource;
import com.universe.mapper.ResourceMapper;
import com.universe.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {

  @Autowired
  private ResourceMapper resourceMapper;

  @Override
  public List<Resource> listResources() {
    return resourceMapper.listResources();
  }

  @Override
  public List<Resource> getResourcesByUsername(String username) {
    return resourceMapper.getResourcesByUsername(username);
  }

  @Override
  public Integer saveResource(Resource resource) {
    return resourceMapper.saveResource(resource);
  }

  @Override
  public Integer updateResource(Resource resource) {
    return resourceMapper.updateResource(resource);
  }

  @Override
  public Integer deleteResourceByResourceId(Integer resourceId) {
    return resourceMapper.deleteResourceByResourceId(resourceId);
  }

}
