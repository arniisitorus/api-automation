package scenario;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseDelete;
import com.apiautomation_arnii.model.ResponseObject;
import com.apiautomation_arnii.model.ResponseUpdate;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;

public class RestE2ETest {

     ResponseObject responseObject;
     ResponseUpdate responseUpdate;
     ResponseDelete responseDelete;

     @Test
     public void scenarioRestE2ETest(){

          String json = "{\r\n" + //
                         "   \"name\": \"Apple MacBook Pro 16\",\r\n" + //
                         "   \"data\": {\r\n" + //
                         "      \"year\": 2019,\r\n" + //
                         "      \"price\": 1849.99,\r\n" + //
                         "      \"CPU model\": \"Intel Core i9\",\r\n" + //
                         "      \"Hard disk size\": \"1 TB\"\r\n" + //
                         "   }\r\n" + //
                         "}";
          
          // Add Object
          
          RestAssured.baseURI = "https://api.restful-api.dev/";
          Response responseAdd = given()
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                                   .body(json)
                                   .contentType("application/json")
                              .when()
                                   .post("{path}");
          System.out.println("Hasil add object " + responseAdd.asPrettyString()); 

          JsonPath addJsonPath = responseAdd.jsonPath();

          responseObject = responseAdd.as(ResponseObject.class);

          Assert.assertNotNull(responseObject.id);
          Assert.assertEquals(responseObject.name, "Apple MacBook Pro 16");
          Assert.assertNotNull(responseObject.createdAt);
          Assert.assertEquals(responseObject.dataItem.year, 2019);
          Assert.assertEquals(responseObject.dataItem.price, 1849.99f);
          Assert.assertEquals(responseObject.dataItem.cpuModel, "Intel Core i9");
          Assert.assertEquals(responseObject.dataItem.hardDiskSize, "1 TB");

          String idObject = "ff808181932badb6019566f1787f7c15";


          // Get Single Object
          Response responseGet = given()
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                                   .pathParam("idObject", "ff808181932badb6019566f1787f7c15")
                              .when()
                                   .get("{path}/{idObject}");

          System.out.println("Hasil Get Single Object " + responseGet.asPrettyString()); 

          JsonPath jsonPath = responseGet.jsonPath();
         
          responseObject = responseGet.as(ResponseObject.class);
          
          Assert.assertNotNull(responseObject.id, "");
          Assert.assertNotNull(responseObject.name, "");


          // Update Object
          
          Response responseUpdates = given()
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                                   .pathParam("idObject", "ff808181932badb6019566f1787f7c15")
                                   .body(json)
                                   .contentType("application/json")
                              .when()
                                   .put("{path}/{idObject}");
          System.out.println("Hasil update object" + responseUpdates.asPrettyString());

          JsonPath updateJsonPath = responseUpdates.jsonPath();

          responseUpdate = responseUpdates.as(ResponseUpdate.class);

          Assert.assertNotNull(responseUpdate.id, "");
          Assert.assertNotNull(responseUpdate.name, "");
          Assert.assertNotNull(responseUpdate.updatedAt);
          Assert.assertEquals(responseUpdate.dataItem.year, 2019);
          Assert.assertEquals(responseUpdate.dataItem.price, 1849.99f);
          Assert.assertEquals(responseUpdate.dataItem.cpuModel, "Intel Core i9");
          Assert.assertEquals(responseUpdate.dataItem.hardDiskSize, "1 TB");


          // Delete Object
          Response responseDeletes = given()
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                                   .pathParam("idObject", "ff808181932badb6019566f1787f7c15")
                              .when()
                                   .delete("{path}/{idObject}");

          System.out.println("Hasil delete object " + responseDeletes.asPrettyString());

          JsonPath deleteJsonPath = responseDeletes.jsonPath();

          responseDelete = responseDeletes.as(ResponseDelete.class);

          Assert.assertNotNull(responseDelete.message, "");

     }

}
