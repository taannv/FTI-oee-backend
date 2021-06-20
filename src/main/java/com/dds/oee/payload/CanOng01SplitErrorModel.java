package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 *
 * @author: Nguyen Van Tan
 *
 * January 26, 2021
 *
 */
@Getter @Setter
public class CanOng01SplitErrorModel {
    private String hourStart,dayStart,hourEnd,dayEnd,note;
    private int tgdung;
    private LocalDateTime date1;

}
