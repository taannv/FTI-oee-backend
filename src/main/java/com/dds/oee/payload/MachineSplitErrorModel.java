package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MachineSplitErrorModel {
    private String hourStart,dayStart,hourEnd,dayEnd,note;
}
