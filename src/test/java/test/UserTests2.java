package test;


import com.github.javafaker.Faker;

import endpoints.UserEndPoints;
import endpoints.UserEndPoints2;
import io.restassured.response.Response;
import payloads.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {
	
	
	Faker faker;

	  User userPayload;
	  public Logger logger;

	    @BeforeClass
	    public  void SetupData(){

	        faker = new Faker();

	        userPayload =new User();
	        userPayload.setId(faker.idNumber().hashCode());

	        userPayload.setUsername(faker.name().username());
	        userPayload.setFirstName(faker.name().firstName());
	        userPayload.setLastName(faker.name().lastName());
	        userPayload.setEmail(faker.internet().safeEmailAddress());
	        userPayload.setPassword(faker.internet().password(5,10));
	        userPayload.setPhone(faker.phoneNumber().cellPhone());
	        
	        
	        // Logs
	        
	        logger=LogManager.getLogger(this.getClass());
	        
	        

	    }

	    @Test(priority = 1)

	    public void testPostUser()
	    {
	    	logger.info("******************* Creating User**************");
	    	
	    	
	        Response response = UserEndPoints2.createUser(userPayload);

	        response.then().log().all();

	        Assert.assertEquals(response.getStatusCode(),200);
	        
	        logger.info("*******************User is created  **************");
	    }

	    @Test(priority = 2)
	    public void testGetUserByName(){
	    	
	    	logger.info("******************* Reading User info **************");

	        Response response =  UserEndPoints2.readUser(this.userPayload.getUsername());

	        response.then().log().all();

	        //Assert.assertEquals(response.getStatusCode(),200);

	        Assert.assertEquals(response.getStatusCode(),200);
	        
	        logger.info("******************* User information is displayed **************");

	    }

	    @Test(priority = 3)
	    public void testUpdateUserByName(){

	    	
	    	logger.info("******************* Updating User**************");

	        userPayload.setFirstName(faker.name().firstName());
	        userPayload.setLastName(faker.name().lastName());
	        userPayload.setEmail(faker.internet().safeEmailAddress());

	        Response response = UserEndPoints2.updateUser(this.userPayload.getUsername(),userPayload);

	       // response.then().log().all();

	       // response.then().log().body().statusCode(200);// this is also kind of assertion
	        response.then().log().body();

	        Assert.assertEquals(response.getStatusCode(),200);

	        ///// details after updating user details

	        Response responseAfterUpdate =  UserEndPoints2.readUser(this.userPayload.getUsername());

	        responseAfterUpdate.then().log().all();

	        //Assert.assertEquals(response.getStatusCode(),200);

	        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
	        
	        logger.info("******************* User has updated **************");

	    }

	    @Test(priority = 4)
	    public void testDeleteUserByName(){
	    	
	    	logger.info("******************* Deleting User**************");

	   Response response = UserEndPoints2.deletUser(this.userPayload.getUsername());

	   Assert.assertEquals(response.getStatusCode(),200);
	   
	   logger.info("******************* User has deleted **************");


	    }

}
