package com.dds.oee.entitycanong02;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * @author: Nguyen Van Tan
 * *
 * March 04, 2021
 */
@Setter
@Getter
@Entity
@Table(name = "Table_6")
public class CanOng02SplitStatus {
    @Id
    private Long id;
    private LocalDateTime date1;
    private String gio;
    private String ngay;
    @Column(name = "Trang_thai_ON")
    private Boolean trang_thai_ON;
    @Column(name = "Toc_do")
    private int toc_do;

}
