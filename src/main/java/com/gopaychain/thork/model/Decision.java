package com.gopaychain.thork.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.LinkedHashMap;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SelectDecision.class, name = "SELECT"),
        @JsonSubTypes.Type(value = AllDecision.class, name = "ALL")
})
public abstract class Decision {
    @Id
    private String id;
    private int level;
    private int num;
    private List<Decision> decisions;
    private Action action;

    public  abstract Mono execute(Decision currentDecision, LinkedHashMap<String, Object> results);

    public String getId(){
        return id+level+num;
    }
}