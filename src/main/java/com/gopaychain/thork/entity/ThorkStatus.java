package com.gopaychain.thork.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
public class ThorkStatus {

    private String runStepId;
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "RUN_STEP_ID")
    private String runBatchId;
    private ZonedDateTime startTime;
    private ZonedDateTime lastTime;

    private String status;
    private String thorkId;
    private String decisionName;
    private String actionName;

}
