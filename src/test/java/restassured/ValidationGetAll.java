package restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseGetAll;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationGetAll {

     @Test
     public void getAllObject(){

          RestAssured.baseURI = "https://api.restful-api.dev/";
          RequestSpecification requestSpecification = RestAssured.given();

          Response response = requestSpecification
                                   .log()
                                   .all()
                                   .pathParam("path", "objects")
                              .when()
                                  .get("{path}");
          System.out.println("Hasil Get All Object " + response.asPrettyString());

          JsonPath jsonPath = response.jsonPath();
          List<ResponseGetAll> responseObjects = jsonPath.getList("", ResponseGetAll.class);

          for (ResponseGetAll obj : responseObjects) {

               Assert.assertNotNull(obj.id, "ID should not be null");
               Assert.assertNotNull(obj.name, "Name should not be null");
          }
     }

}
