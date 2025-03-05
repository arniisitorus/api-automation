package com.apiautomation_arnii.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponsePartiallyUpdate {

     @JsonProperty("id")
     public String id;   

     @JsonProperty("name")
     public String name;

     @JsonProperty("updatedAt")
     public String updatedAt;

     @JsonProperty("data")
     public DataItem dataItem;

     public static class DataItem{
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
