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
public class CanOng03Model {
    private Long id;
    private LocalDateTime date1;
    private String gio, ngay, lenh_sx;
    private Float tgkh, tgsx, slkh, slsx, oee, oeeA, oeeQ, oeeP, slkh8t, klkh8t, sltt8t, kltt8t, t, slkhtc, klkhtc, sltttc, kltttc, tc, chinhpham, thupham, phepham;
    private Float timeOn,timeOff;
    private Float day_in;
    private int rong_in, dai_out, rong_out, cao_out, sltt,slProduce;
}
