package com.universe.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.universe.pojo.domain.ResourceDo;

import java.util.List;

public interface ResourceMapper extends BaseMapper<ResourceDo> {

	List<String> getResourceCodeByUsername(String username);

	List<ResourceDo> getMenuByUsername(String username);
}