package com.dds.oee.payload;
import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Aug 28, 2019
 */

@Builder @Getter @Setter
public class VolumeModel {

	private Long machineId;
	private BigDecimal input;
	private BigDecimal output;
	private String createdBy;
	private Long startedAt;
	private Long endedAt;
	private String command;
	private String note;
}
