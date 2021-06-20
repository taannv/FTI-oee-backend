package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author: Nguyen Van Tan
 *
 * February 06, 2021
 *
 */
@Getter
@Setter
public class CanOng02ProductionModel {
    private Long id;
    private LocalDateTime date1;
    private String ngay, gio, lenh_sx;
    private Float day_in;
    private int rong_in, dai_out, rong_out, cao_out, slkh, sltt, nhom;
}
