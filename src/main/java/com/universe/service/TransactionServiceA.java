package com.universe.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.universe.common.entity.domain.OperationLogDo;
import com.universe.mapper.OperationLogMapper;

@Service
public class TransactionServiceA {

  @Autowired
  private OperationLogMapper operationLogMapper;
  @Autowired
  private TransactionServiceB serviceB;

  @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
  public void methodA() {
    OperationLogDo operationLog = new OperationLogDo();
    operationLog.setOpType(1);
    operationLog.setOpContent("修改操作");
    operationLog.setUserId(1);
    operationLog.setCreateTime(new Date());
    operationLogMapper.saveOperationLog(operationLog);

    try {
      serviceB.methodB();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }
}
