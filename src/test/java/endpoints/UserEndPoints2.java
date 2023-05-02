package endpoints;



//UserEndPoints.java

//Create for perform create , Read , Update , Delete requests.

import com.sun.org.apache.bcel.internal.generic.RETURN;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;

import java.util.ResourceBundle;

public class UserEndPoints2 {
	
	
	// method created for getting URL from properties file
	static ResourceBundle getURL()
	{
		
		ResourceBundle routes=ResourceBundle.getBundle("routes"); //load properties file // roues under getBundle is the properties file name of the properties file
		
		
		return routes;
	}


    public static Response createUser( User payload) {
    	
    	String post_url=getURL().getString("PostURL");
    	

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(post_url);
        return  response;

    }


public static Response readUser( String username) {
	
	String get_url=getURL().getString("Get_URL");

    Response response = given().pathParam("username", username)
            .when()
            .get(get_url);

    return response;

}
    public static Response updateUser( String username, User payload) {
    	
    	String put_url=getURL().getString("Put_Url");

        Response response = given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(put_url);
        return  response;

    }



    public static Response deletUser( String username) {
    	
    	String delete_url=getURL().getString("Delete_Url");

        Response response = given().pathParam("username", username)
                .when()
                .delete(delete_url);

        return response;

    }


}
