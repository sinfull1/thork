package com.gopaychain.thork.model;


import lombok.Data;


@Data
public class ThorkStatusRequest {


    private String status;
    private String thorkId;
    private String decisionName;
    private String actionName;


}
