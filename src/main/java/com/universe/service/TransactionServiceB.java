package com.universe.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.universe.common.entity.domain.OperationLogDo;
import com.universe.mapper.OperationLogMapper;

@Service
public class TransactionServiceB {

  @Autowired
  private OperationLogMapper operationLogMapper;

  @Transactional(propagation = Propagation.NESTED, rollbackFor = Exception.class)
  public void methodB() {
    OperationLogDo operationLog = new OperationLogDo();
    operationLog.setOpType(1);
    operationLog.setOpContent("新增操作");
    operationLog.setUserId(1);
    operationLog.setCreateTime(new Date());
    operationLogMapper.saveOperationLog(operationLog);
    throw new RuntimeException("发生异常了!");
  }
}
