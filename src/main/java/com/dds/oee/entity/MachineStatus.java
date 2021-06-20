package com.dds.oee.entity;

import lombok.Getter;

import java.util.List;

/**
 * Author : duybv
 * Sep 16, 2019
 */
public enum MachineStatus {

	ACTIVE("Hoạt động", (short) 1, "green"),
	INACTIVE("Tạm khóa", (short) 2, "orange"),
	DEACTIVATED("Khóa", (short) 3, "red"),
	OTHER("Không xác định", (short) 99, "gray");


	private @Getter short value;
	private @Getter String text;
	private @Getter String color;


	MachineStatus(String text, short value, String color) {
		this.value = value;
		this.text = text;
		this.color = color;
	}

	public String getText(Short value) {
		return getStatus(value).getText();
	}

	public String getColor(Short value) {
		return getStatus(value).getColor();
	}

	public static MachineStatus getStatus(Short value) {
		if (value == null) {
			return OTHER;
		}

		for (MachineStatus status : MachineStatus.values()) {
			if (status.getValue() == value) {
				return status;
			}
		}

		return OTHER;
	}

	public static boolean isValid(Short status) {
		if (status == null) {
			return false;
		}
		for (MachineStatus stt: MachineStatus.values()) {
			if (stt.getValue() == status) {
				return true;
			}
		}
		return false;
	}

	public static boolean isValid(List<Short> statusList) {
		if (statusList == null || statusList.isEmpty()) {
			return true;
		}
		for (Short status : statusList) {
			if (!isValid(status)) {
				return false;
			}
		}
		return true;
	}

	public static boolean notValid(Short status) {
		return !isValid(status);
	}

	public static boolean notValid(List<Short> statusList) {
		return !isValid(statusList);
	}

	public static boolean test(Short persistedStatus, MachineStatus...status) {
		if (persistedStatus == null) {
			return false;
		}

		for (MachineStatus stt : status) {
			if (persistedStatus == stt.value) {
				return true;
			}
		}

		return false;
	}

	public static boolean negate(Short persistedStatus, MachineStatus...status) {
		return !test(persistedStatus, status);
	}

}
