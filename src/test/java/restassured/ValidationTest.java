package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationTest {

     ResponseObject responseObject;

     @Test
     public void createObject(){
          String json = "{\r\n" + //
                              "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                              "   \"data\": {\r\n" + //
                              "      \"year\": 2019,\r\n" + //
                              "      \"price\": 1849.99,\r\n" + //
                              "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                              "      \"Hard disk size\": \"1 TB\"\r\n" + //
                              "   }\r\n" + //
                              "}";
                        

         RestAssured.baseURI = "https://api.restful-api.dev/";
         RequestSpecification requestSpecification = RestAssured.given();

         Response response = requestSpecification
                             .log()
                             .all()
                             .pathParam("path", "objects")
                             .body(json)
                             .contentType("application/json")
                             .when()
                                 .post("{path}");
         System.out.println("Hasil add object " + response.asPrettyString()); 

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

          // Cara Pertama

          JsonPath addJsonPath = response.jsonPath();
          
          String id = addJsonPath.get("id");
          String name = addJsonPath.get("name");
          String createdAt = addJsonPath.get("createdAt");
          int year = addJsonPath.get("data.year");
          float price = addJsonPath.getFloat("data.price");
          String cpuModel = addJsonPath.get("data.'CPU model'");
          String hardDiskSize = addJsonPath.get("data.'Hard disk size'");
          
          Assert.assertEquals(response.statusCode(), 200);
          Assert.assertNotNull(id);
          Assert.assertEquals(name, "Apple MacBook Pro 16");
          Assert.assertNotNull(createdAt);
          Assert.assertEquals(year, 2019);
          Assert.assertEquals(price, 1849.99f);
          Assert.assertEquals(cpuModel, "Intel Core i9");
          Assert.assertEquals(hardDiskSize, "1 TB");

          // Cara Kedua Menggunakan POJO

          responseObject = response.as(ResponseObject.class);

          Assert.assertNotNull(responseObject.id);
          Assert.assertEquals(responseObject.name, "Apple MacBook Pro 16");
          Assert.assertNotNull(responseObject.createdAt);
          Assert.assertEquals(responseObject.dataItem.year, 2019);
          Assert.assertEquals(responseObject.dataItem.price, 1849.99f);
          Assert.assertEquals(responseObject.dataItem.cpuModel, "Intel Core i9");
          Assert.assertEquals(responseObject.dataItem.hardDiskSize, "1 TB");
       
     }

}
