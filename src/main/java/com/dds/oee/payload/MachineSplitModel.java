package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class MachineSplitModel {
    private Long id;
    private LocalDateTime date;
    private String productionOrder,hour,day;
    private Float plan,produce,oee, oeeA, oeeQ, oeeP;
    private Float timePlan, timeProduce;
    private Float width,height, mass;
    private int timeOn,timeOff;
    private Float width1,amount1,width2,amount2,width3,amount3,width4,amount4,width5,amount5,bienT,bienN;

}
