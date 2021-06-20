package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author: Phan Thi Hong
 *
 * June 01, 2020
 *
 */
 @Getter @Setter
 @Entity
public class MachineQuantity {

     @Id
    private String ngay;
    private Float khoi_luong;
}
