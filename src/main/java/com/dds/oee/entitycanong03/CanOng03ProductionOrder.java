package com.dds.oee.entitycanong03;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
@Entity
@Table(name = "Table_2")
public class CanOng03ProductionOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "date1")
    private LocalDateTime date1;

    @Column(name = "Ngay")
    private String ngay;

    @Column(name = "Gio")
    private String gio;

    @Column(name = "Lenh_sx")
    private String lenh_sx;

    @Column(name = "Day_In")
    private float day_in;

    @Column(name = "Rong_In")
    private int rong_in;

    @Column(name = "Dai_Out")
    private int dai_out;

    @Column(name = "Rong_Out")
    private int rong_out;

    @Column(name = "Cao_Out")
    private int cao_out;

    @Column(name = "SL_KH")
    private int slkh;

    @Column(name = "SL_TT")
    private int sltt;

    @Column(name = "Nhom")
    private int nhom;
}
