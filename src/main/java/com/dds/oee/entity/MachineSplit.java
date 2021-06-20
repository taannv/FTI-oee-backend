package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Table_1")
public class MachineSplit{


    @Id
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

    @Column(name = "KL_KH")
    private float klkh;

    @Column(name = "KL_SX")
    private float klsx;

    @Column(name = "OEE")
    private Float oee;
    @Column(name = "OEE_A")
    private Float oeeA;
    @Column(name = "OEE_Q")
    private Float oeeQ;
    @Column(name = "OEE_P")
    private Float oeeP;

    @Column(name = "TG_on")
    private int tgon;

    @Column(name = "TG_off")
    private int tgoff;

}
