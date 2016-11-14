package com.example.demo.base.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class CommonBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;

	@Id
	@Column(name = "ID", length = 36, nullable = false)
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		// 如果主键ID为空字符串，则设置为null
		if (StringUtils.equals("", id)) {
			id = null;
		}
		this.id = id;
	}
}