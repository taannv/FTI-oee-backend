package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@Table(name = "Table_3")
public class MachineSplitError{

    @Id
    private Long id;
    private LocalDateTime date1;
    private String gio_start;
    private String ngay_start;
    private String gio_end;
    private String ngay_end;
    private String nguyen_nhan;
}
