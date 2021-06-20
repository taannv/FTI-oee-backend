package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

/**
 * Author : duybv
 * Sep 16, 2019
 */

@Getter @Setter
public class MachineModel {

	private Long id;
	private Short status;
	private String name, statusText, statusColor, description;
	private Long statusedAt;
}
