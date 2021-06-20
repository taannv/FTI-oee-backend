package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Table_6")
public class MachineSpeed {
    @Id
    private Long id;
    private LocalDateTime date1;
    private String gio;
    private String ngay;
    private boolean trang_thai_ON;
    @Column(name = "Toc_do")
    private int toc_do;
}
