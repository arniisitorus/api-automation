package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseUpdate;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationUpdate {

     ResponseUpdate responseUpdate;

     @Test
     public void updateObject(){

          String json = "{\r\n" +
                         "   \"name\": \"Apple MacBook Pro 16 - Update 2025\",\r\n" +
                         "   \"data\": {\r\n" +
                         "      \"year\": 2025,\r\n" +
                         "      \"price\": 1849.99,\r\n" +
                         "      \"CPU model\": \"Intel Core i9\",\r\n" +
                         "      \"Hard disk size\": \"1 TB\"\r\n" +
                         "   }\r\n" +
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
                                   .put("{path}/{idObject}");
          System.out.println("Hasil update object" + response.asPrettyString());     

          JsonPath addJsonPath = response.jsonPath();

          responseUpdate = response.as(ResponseUpdate.class);

          Assert.assertNotNull(responseUpdate.id, "");
          Assert.assertEquals(responseUpdate.name, "Apple MacBook Pro 16 - Update 2025");
          Assert.assertNotNull(responseUpdate.updatedAt);
          Assert.assertEquals(responseUpdate.dataItem.year, 2025);
          Assert.assertEquals(responseUpdate.dataItem.price, 1849.99f);
          Assert.assertEquals(responseUpdate.dataItem.cpuModel, "Intel Core i9");
          Assert.assertEquals(responseUpdate.dataItem.hardDiskSize, "1 TB");
       
     }

}
