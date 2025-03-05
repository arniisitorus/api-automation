package com.apiautomation_arnii.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseSingleObject {

     @JsonProperty("id")
     public String id;   

     @JsonProperty("name")
     public String name;

     @JsonProperty("createdAt")
     public String createdAt;

     @JsonProperty("data")
     public DataItem dataItem;

     public class DataItem{
          @JsonProperty("year")
          public int year;

          @JsonProperty("price")
          public float price;

          @JsonProperty("CPU model")
          public String cpuModel;

          @JsonProperty("Hard disk size")
          public String hardDiskSize;
          }
     
}
