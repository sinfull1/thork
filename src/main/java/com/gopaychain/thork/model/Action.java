package com.gopaychain.thork.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ApiAction.class, name = "api"),
        @JsonSubTypes.Type(value = CommandAction.class, name = "command"),
        @JsonSubTypes.Type(value = CallBackAction.class, name = "callback")
})
public abstract class Action {

    private String id;

    public abstract boolean execute(HashMap<String,Object> results) throws ExecutionException, InterruptedException;

}
