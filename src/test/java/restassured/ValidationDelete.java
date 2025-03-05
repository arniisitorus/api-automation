package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseDelete;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationDelete {

     ResponseDelete responseDelete;

     @Test
     public void deleteObject(){
          RestAssured.baseURI = "https://api.restful-api.dev/";
          RequestSpecification requestSpecification = RestAssured
                                                      .given();
  
          Response response = requestSpecification
                                  .log()
                                  .all()
                                  .pathParam("path", "objects")
                                  .pathParam("idObject", "ff808181932badb6019566c4317f7b99")
                                  .contentType("application/json")
                              .when()
                                  .delete("{path}/{idObject}");
          System.out.println("Hasil delete object" + response.asPrettyString());

          JsonPath addJsonPath = response.jsonPath();

          responseDelete = response.as(ResponseDelete.class);

          Assert.assertNotNull(responseDelete.message, "");
      }      
     
}