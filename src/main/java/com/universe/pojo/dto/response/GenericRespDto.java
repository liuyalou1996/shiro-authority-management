package com.universe.pojo.dto.response;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GenericRespDto<T> {

	private int resultCode;
	private String resultDesc;
	private T content;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultDesc() {
		return resultDesc;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public static <T> Builder<T> builder() {
		return new Builder<>();
	}

	public static class Builder<T> {

		private GenericRespDto<T> response = new GenericRespDto<>();

		public Builder<T> resultCode(int resultCode) {
			response.setResultCode(resultCode);
			return this;
		}

		public Builder<T> resultDesc(String resultDesc) {
			response.setResultDesc(resultDesc);
			return this;
		}

		public Builder<T> content(T content) {
			response.setContent(content);
			return this;
		}

		public GenericRespDto<T> build() {
			return response;
		}

	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}
