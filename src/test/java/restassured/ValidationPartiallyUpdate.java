package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponsePartiallyUpdate;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationPartiallyUpdate {

     ResponsePartiallyUpdate responsePartiallyUpdate;

     @Test
     public void updatePartiallyObject(){

          String json = "{\r\n" +
                         "   \"name\": \"Apple MacBook Pro 16 (Updated Name) Partially Updated\"\r\n" +
                         "}";                      

         RestAssured.baseURI = "https://api.restful-api.dev/";
         RequestSpecification requestSpecification = RestAssured.given();

         Response response = requestSpecification
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                                   .pathParam("idObject", "ff808181932badb6019566c4317f7b99")
                                   .body(json)
                                   .contentType("application/json")
                              .when()
                                   .patch("{path}/{idObject}");
          System.out.println("Hasil patch object" + response.asPrettyString());      

          JsonPath addJsonPath = response.jsonPath();

          responsePartiallyUpdate = response.as(ResponsePartiallyUpdate.class);

          Assert.assertNotNull(responsePartiallyUpdate.id, "");
          Assert.assertEquals(responsePartiallyUpdate.name, "Apple MacBook Pro 16 (Updated Name) Partially Updated");
          Assert.assertNotNull(responsePartiallyUpdate.updatedAt);
          Assert.assertEquals(responsePartiallyUpdate.dataItem.year, 2025);
          Assert.assertEquals(responsePartiallyUpdate.dataItem.price, 1849.99f);
          Assert.assertEquals(responsePartiallyUpdate.dataItem.cpuModel, "Intel Core i9");
          Assert.assertEquals(responsePartiallyUpdate.dataItem.hardDiskSize, "1 TB");
          
       
     }

}