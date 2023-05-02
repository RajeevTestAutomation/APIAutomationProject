package endpoints;

public class Routes {
	
	
	
	/*

	//Swager URI: https://petstore.swagger.io/

	Create User (post): https://petstore.swagger.io/v2/user

	Get user(Get):  https://petstore.swagger.io/v2/user/{username}

	Update User(Put): https://petstore.swagger.io/v2/user/{username}

	Delete User(Delete): https://petstore.swagger.io/v2/user/{username}

	 */


    public static String base_url ="https://petstore.swagger.io/v2";

    // User module

    public static String post_url =base_url+"/user";
    public static String get_url =base_url+"/user/{username}";
    public static String put_url =base_url+"/user/{username}";
    public static String delete_url =base_url+"/user/{username}";

}
