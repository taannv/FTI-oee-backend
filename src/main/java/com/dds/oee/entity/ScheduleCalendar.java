package com.dds.oee.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "schedule")
public class ScheduleCalendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long user_id;
    private LocalDateTime start;

    @Column(name = "end_at")
    private LocalDateTime endAt;
    private int send_mail;
    private String send_type;
    private String user_maintenance;
    private int status;
}
