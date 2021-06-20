package com.dds.oee.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Getter @Setter
@Entity
@Table(name = "history_errors")
public class HistoryError extends DateAudit {
	/**
	 *
	 */
	private static final long serialVersionUID = 6485837394896968567L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long machineId;
	private String cause, createdBy;
	private LocalDateTime startedAt, endedAt;
}
