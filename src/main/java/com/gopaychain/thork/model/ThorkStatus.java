package com.gopaychain.thork.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Data
public class ThorkStatus {

    private String runStepId;
    @Id
    private String runBatchId;
    private ZonedDateTime startTime;
    private ZonedDateTime lastTime;

    private String status;
    private String thorkId;
    private String decisionName;
    private String actionName;

}
