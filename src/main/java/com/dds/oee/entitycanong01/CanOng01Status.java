package com.dds.oee.entitycanong01;

import lombok.Getter;

import java.util.List;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 15, 2021
 *
 */
public enum CanOng01Status {

    ACTIVE("Hoạt động", (short) 1, "green"),
    INACTIVE("Tạm khóa", (short) 2, "orange"),
    DEACTIVATED("Khóa", (short) 3, "red"),
    OTHER("Không xác định", (short) 99, "gray");

    private @Getter
    short value;
    private @Getter String text;
    private @Getter String color;

    CanOng01Status(String text, short value, String color) {
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

    public static CanOng01Status getStatus(Short value) {
        if (value == null) {
            return OTHER;
        }

        for (CanOng01Status status : CanOng01Status.values()) {
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
        for (CanOng01Status stt: CanOng01Status.values()) {
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

    public static boolean test(Short persistedStatus, CanOng01Status...status) {
        if (persistedStatus == null) {
            return false;
        }

        for (CanOng01Status stt : status) {
            if (persistedStatus == stt.value) {
                return true;
            }
        }

        return false;
    }

    public static boolean negate(Short persistedStatus, CanOng01Status...status) {
        return !test(persistedStatus, status);
    }

}
