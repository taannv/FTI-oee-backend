package com.dds.oee.payload;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Getter @Setter @Builder
public class HistoryErrorModel {

	private Long id;
	private String machineName, cause, createdBy;
	private Long startedAt, endedAt;
}
