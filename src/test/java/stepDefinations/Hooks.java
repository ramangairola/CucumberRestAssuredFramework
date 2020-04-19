package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

    @Before("@DeletePlaceAPI")
    public void beforeScenario() throws IOException
    {		//execute this code only when place id is null
        //write a code that will give you place id

        MyStepdefs m =new MyStepdefs();
        if(MyStepdefs.place_id==null)
        {

            m.add_Place_Payload("Shetty", "French", "Asia");
            m.user_calls_with_Post_http_request("AddPlaceAPI", "POST");
            m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
        }



    }
}
