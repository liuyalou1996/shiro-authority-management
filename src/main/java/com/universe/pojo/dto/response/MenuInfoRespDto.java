package com.universe.pojo.dto.response;

import com.universe.pojo.domain.ResourceDo;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public class MenuInfoRespDto {

	private String title;
	private String href;
	private String icon;
	private String target = "_self";

	private List<MenuInfoRespDto> child;

	public MenuInfoRespDto() {
	}

	public MenuInfoRespDto(ResourceDo resourceDo) {
		this.title = resourceDo.getResourceName();
		this.href = resourceDo.getResourceUrl();
		this.icon = resourceDo.getResourceIcon();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public List<MenuInfoRespDto> getChild() {
		return child;
	}

	public void setChild(List<MenuInfoRespDto> child) {
		this.child = child;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
