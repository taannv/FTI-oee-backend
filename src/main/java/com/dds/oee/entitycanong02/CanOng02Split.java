package com.dds.oee.entitycanong02;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
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
@Table(name = "Table_1")
public class CanOng02Split {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "date1")
    private LocalDateTime date1;

    @Column(name = "Gio")
    private String gio;

    @Column(name = "Ngay")
    private String ngay;

    @Column(name = "TG_KH")
    private float tgkh;

    @Column(name = "TG_SX")
    private float tgsx;

    @Column(name = "TG_ON")
    private float tgon;

    @Column(name = "TG_OFF")
    private float tgoff;

    @Column(name = "SL_KH")
    private float slkh;

    @Column(name = "SL_SX")
    private float slsx;

    @Column(name = "OEE")
    private Float oee;
    @Column(name = "OEE_A")
    private Float oeeA;
    @Column(name = "OEE_Q")
    private Float oeeQ;
    @Column(name = "OEE_P")
    private Float oeeP;

    @Column(name = "SL_KH_8t")
    private float slkh8t;

    @Column(name = "KL_KH_8t")
    private float klkh8t;

    @Column(name = "SL_TT_8t")
    private float sltt8t;

    @Column(name = "KL_TT_8t")
    private float kltt8t;

    @Column(name = "[%_8t]")
    private float t;

    @Column(name = "SL_KH_TC")
    private float slkhtc;

    @Column(name = "KL_KH_TC")
    private float klkhtc;

    @Column(name = "SL_TT_TC")
    private float sltttc;

    @Column(name = "KL_TT_TC")
    private float kltttc;

    @Column(name = "[%_TC]")
    private float tc;

    @Column(name = "Chinh_pham")
    private Float chinhpham;

    @Column(name = "Thu_pham")
    private Float thupham;

    @Column(name = "Phe_pham")
    private Float phepham;
}
