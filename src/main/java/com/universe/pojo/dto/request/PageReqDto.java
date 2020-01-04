package com.universe.pojo.dto.request;

import com.universe.common.emuneration.OrderEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class PageReqDto<T> {

	private int pageNum;

	private int pageSize;

	private List<Sort> sort;

	private T params;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<Sort> getSort() {
		return sort;
	}

	public void setSort(List<Sort> sort) {
		this.sort = sort;
	}

	public T getParams() {
		return params;
	}

	public void setParams(T params) {
		this.params = params;
	}

	public static class Sort {

		private String field;
		private String order;

		public String getField() {
			return field;
		}

		public void setField(String field) {
			this.field = field;
		}

		public String getOrder() {
			return order;
		}

		public void setOrder(String order) {
			this.order = order;
		}

		public boolean isAscending() {
			return OrderEnum.ASC == getOrderEnum();
		}

		public boolean isDescending() {
			return OrderEnum.DESC == getOrderEnum();
		}

		public OrderEnum getOrderEnum() {
			return StringUtils.isBlank(order) ? null : OrderEnum.valueOf(order.toUpperCase());
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
