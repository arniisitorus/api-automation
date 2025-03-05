package com.apiautomation_arnii.model;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseGetList {

     @JsonProperty("id")
     public String id;

     @JsonProperty("name")
     public String name;

    @JsonProperty("data")
    public Map<String, Object> data;

    @Override
    public String toString() {
        return "ResponseGetAll{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", data=" + data +
                '}';
    }

}
