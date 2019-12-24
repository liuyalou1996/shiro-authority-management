package com.universe.mapper;

import com.universe.pojo.domain.ResourceDo;

public interface ResourceMapper {
    int deleteByPrimaryKey(Integer resourceId);

    int insert(ResourceDo record);

    int insertSelective(ResourceDo record);

    ResourceDo selectByPrimaryKey(Integer resourceId);

    int updateByPrimaryKeySelective(ResourceDo record);

    int updateByPrimaryKey(ResourceDo record);
}