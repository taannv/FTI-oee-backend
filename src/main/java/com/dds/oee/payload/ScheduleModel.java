package com.dds.oee.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ScheduleModel {
    private Long id;
    private String title;
    private String content;
    private String start;
    private String end;
    private int send_mail;
    private String send_type;
    private String user_maintenance;
    private int status;
}
