package resources;

import pojo.GoogleApiPojo;
import pojo.LocationPojo;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public GoogleApiPojo addPlacePayload(String name, String address, String language){
        GoogleApiPojo obj = new GoogleApiPojo();
        obj.setAccuracy(50);
        obj.setName(name);
        obj.setPhone_number("9718185836");
        obj.setAddress(address);
        obj.setWebsite("http://google.com");
        obj.setLanguage(language);
        List<String> typeList = new ArrayList<>();
        typeList.add("shoe park");
        typeList.add("shop");
        obj.setTypes(typeList);
        LocationPojo pojoObj = new LocationPojo();
        pojoObj.setLat(-38.383494);
        pojoObj.setLng(33.427362);
        obj.setLocation(pojoObj);
        return obj;
    }

    public String deletePlacePayload(String placeID){
        return "{\n" +
                "\t\"place_id\":\""+placeID+"\"\n" +
                "}";
    }
}
