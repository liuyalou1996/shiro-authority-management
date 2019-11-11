package com.universe.mapper;

import com.universe.common.entity.domain.OperationLogDo;

public interface OperationLogMapper {

  void saveOperationLog(OperationLogDo operationLog);
}
