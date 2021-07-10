package com.gopaychain.thork.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ApiAction.class, name = "api"),
        @JsonSubTypes.Type(value = CommandAction.class, name = "command")
})
public abstract class Action {

    private String id;
    public abstract Mono<Boolean> execute(HashMap<String,Object> results);

}
