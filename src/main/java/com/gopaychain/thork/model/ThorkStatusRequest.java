package com.gopaychain.thork.model;


import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.Entity;


@Data
public class ThorkStatusRequest {


    private String status;
    private String thorkId;
    private String decisionName;
    private String actionName;


}
