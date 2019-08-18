package com.universe.common.entity.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GenericResponseDto {

  private Integer resultCode;
  private String resultDesc;
  private Object content;

  public Integer getResultCode() {
    return resultCode;
  }

  public String getResultDesc() {
    return resultDesc;
  }

  public Object getContent() {
    return content;
  }

  public void setResultCode(Integer resultCode) {
    this.resultCode = resultCode;
  }

  public void setResultDesc(String resultDesc) {
    this.resultDesc = resultDesc;
  }

  public void setContent(Object content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
