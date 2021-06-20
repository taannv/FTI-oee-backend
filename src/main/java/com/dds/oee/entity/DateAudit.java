package com.dds.oee.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
		value = {"createdAt", "updatedAt"},
		allowGetters = true
		)
public abstract class DateAudit implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7807776981295512449L;


	@CreatedDate
	@Column(nullable = false, name = "created_at")
	private Instant createdAt;

	@LastModifiedDate
	@Column(nullable = false, name = "updated_at")
	private Instant updatedAt;

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}
}
