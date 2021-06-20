package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author: Nguyen Van Tan
 *
 * June 01, 2020
 *
 */
@Getter
@Setter
@Entity
public class ProductionChart {
    @Id
    private String ngay;
    private Float khoi_luong;
}
