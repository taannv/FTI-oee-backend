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
@Entity
@Setter
@Getter
@Table(name = "Table_3")
public class CanOng03SplitError {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "date1")
    private LocalDateTime date1;

    @Column(name = "Gio_Start")
    private String gio_start;

    @Column(name = "Ngay_Start")
    private String ngay_start;

    @Column(name = "Gio_End")
    private String gio_end;

    @Column(name = "Ngay_End")
    private String ngay_end;

    @Column(name = "Nguyen_nhan")
    private String nguyen_nhan;

    @Column(name = "Tg_dung")
    private int tgdung;
}
