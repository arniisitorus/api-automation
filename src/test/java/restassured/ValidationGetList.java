package restassured;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.apiautomation_arnii.model.ResponseGetList;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ValidationGetList {

     @Test
     public void getListObjectsId(){
         RestAssured.baseURI = "https://api.restful-api.dev/";
         RequestSpecification requestSpecification = RestAssured.given();

         Response response = requestSpecification
                                 .log()
                                 .all()
                                 .queryParam("id", "3")
                                 .queryParam("id", "5")
                                 .queryParam("id", "10")
                             .when()
                                 .get("objects");
                                 
         System.out.println("Hasil Get List of Object berdasarkan Id " + response.asPrettyString());           
         
         JsonPath jsonPath = response.jsonPath();
         
         List<ResponseGetList> responseObjects = jsonPath.getList("", ResponseGetList.class);

          for (ResponseGetList obj : responseObjects) {

               Assert.assertNotNull(obj.id, "ID should not be null");
               Assert.assertNotNull(obj.name, "Name should not be null");
          }         
     }
}
