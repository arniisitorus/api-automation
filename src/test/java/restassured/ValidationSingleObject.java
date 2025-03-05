package restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseObject;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationSingleObject {

     ResponseObject responseObject;

     @Test
     public void getSingleObject(){
         RestAssured.baseURI = "https://api.restful-api.dev/";
         RequestSpecification requestSpecification = RestAssured.given();

         Response response = requestSpecification
                                   .log()
                                   .all()
                                   .pathParam("idObject", 7)
                                   .pathParam("path", "objects")
                              .when()
                                   .get("{path}/{idObject}");
         
         System.out.println("Hasil Get Single Object " + response.asPrettyString());            
         
         JsonPath jsonPath = response.jsonPath();
         
         responseObject = response.as(ResponseObject.class);
         
         Assert.assertNotNull(responseObject.id, "ID should not be null");
         Assert.assertNotNull(responseObject.name, "Name should not be null");
          
     }
}
 