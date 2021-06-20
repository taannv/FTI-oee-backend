package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * pr
 * @author: Nguyen Van Tan
 *
 * May 11, 2020
 *
 */
@Getter
@Setter
@Entity
public class Production {
    @Id
    private String lenh_sx;
    private Float khoi_luong;
}
