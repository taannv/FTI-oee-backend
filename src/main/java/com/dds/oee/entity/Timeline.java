package com.dds.oee.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Getter @Setter
@Entity
@Table(name = "volumes")
public class Timeline extends DateAudit {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6485837394896968567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long machineId;

	private Date startedAt;

	private Date endedAt;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60)
	private TimelineStatus status;
}
