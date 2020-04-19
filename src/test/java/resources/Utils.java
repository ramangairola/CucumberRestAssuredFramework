package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;

    public RequestSpecification requestSpecification() throws IOException {
        if (req==null) {
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().addQueryParam("key", "quick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
                    .build();
        }
        return req;
    }

    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        FileInputStream file = new FileInputStream("F:\\Raman\\Work\\Framework\\CucumberRestFramework\\src\\test\\java\\resources\\global.properties");
        prop.load(file);
        return prop.getProperty(key);

    }

    public String getJsonValue(Response response, String key){
        String responseString = response.asString();
        JsonPath jsonPath = new JsonPath(responseString);
        return jsonPath.getString(key);
    }

}
