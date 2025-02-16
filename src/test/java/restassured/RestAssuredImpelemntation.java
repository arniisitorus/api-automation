package restassured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredImpelemntation {
    public static void main(String[] args) {
        getListOfObjects();
        getListObjectsId();
        getSingleObject();
        addObject();
        updateObject();
        partiallyUpdateObject();
        deleteObject();
    }

    public static void getListOfObjects(){
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured.given();

        Response response = requestSpecification
                                .log()
                                .all()
                            .when()
                                .get("objects");
                                
        System.out.println("Hasil Get List of Objectnya sebagai berikut " + response.asPrettyString());                        
    }

    public static void getListObjectsId(){
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
    }    

    public static void getSingleObject(){
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
    }   
    
    public static void addObject(){
        String json = """
            {
            "name": "Apple MacBook Pro 16",
            "data": {
                "year": 2019,
                "price": 1849.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB"
            }
            }
            """;

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
    } 
    
    public static void updateObject(){
        String json = """
            {
            "name": "Apple MacBook Pro 16 - Update 2025",
            "data": {
                "year": 2025,
                "price": 1849.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB"
            }
            }
            """;

        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb601950f410bc938ea")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .put("{path}/{idObject}");
        System.out.println("Hasil update object" + response.asPrettyString());
    }   
    

    public static void partiallyUpdateObject(){
        String json = """
            {
            "name": "Apple MacBook Pro 16 - Update 2025 - using partially update",
            "data": {
                "year": 2025,
                "price": 1849.99,
                "CPU model": "Intel Core i9",
                "Hard disk size": "1 TB"
            }
            }
            """;

        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb601950f410bc938ea")
                            .body(json)
                            .contentType("application/json")
                            .when()
                                .patch("{path}/{idObject}");
        System.out.println("Hasil patch object" + response.asPrettyString());
    }
    
    
    public static void deleteObject(){
        RestAssured.baseURI = "https://api.restful-api.dev/";
        RequestSpecification requestSpecification = RestAssured
                                                    .given();

        Response response = requestSpecification
                            .log()
                            .all()
                            .pathParam("path", "objects")
                            .pathParam("idObject", "ff808181932badb601950f410bc938ea")
                            .contentType("application/json")
                            .when()
                                .delete("{path}/{idObject}");
        System.out.println("Hasil delete object" + response.asPrettyString());
    }
}
