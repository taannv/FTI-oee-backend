package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Table_2")
public class ProductionOrder{

    @Id
    private Long id;
    private String lenh_sx;
    private LocalDateTime date1;
    private String gio;
    private String ngay;
    private Float do_day;
    private Float chieu_rong;
    private Float khoi_luong;
    private Float crong1;
    private Float sluong1;
    private Float crong2;
    private Float sluong2;
    private Float crong3;
    private Float sluong3;
    private Float crong4;
    private Float sluong4;
    private Float crong5;
    private Float sluong5;
    private Float bient;
    private Float bienn;
}
