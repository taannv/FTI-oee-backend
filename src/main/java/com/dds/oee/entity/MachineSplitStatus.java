package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Setter @Getter
@Entity
@Table(name = "Table_5")
public class MachineSplitStatus {
    @Id
    private Long id;
    private LocalDateTime date1;
    private String gio;
    private String ngay;
    private String trang_thai_ON;
}
