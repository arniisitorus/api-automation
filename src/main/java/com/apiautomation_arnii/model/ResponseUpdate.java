package com.apiautomation_arnii.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseUpdate {
     /*
     * Hasil add object {
          "id": "ff808181932badb60195651482ac75bd",
          "name": "Apple MacBook Pro 16",
          "createdAt": "2025-02-24T14:17:24.584+00:00",
          "data": {
               "year": 2019,
               "price": 1849.99,
               "CPU model": "Intel Core i9",
               "Hard disk size": "1 TB"
          }
     */

     @JsonProperty("id")
     public String id;   

     @JsonProperty("name")
     public String name;

     // @JsonProperty("createdAt")
     // public String createdAt;

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
