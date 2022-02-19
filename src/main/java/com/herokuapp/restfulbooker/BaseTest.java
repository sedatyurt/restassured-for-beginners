package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

public class BaseTest {

    protected Response createBooking() {
        //Create JSON Body

        JSONObject body = new JSONObject();
        body.put("firstname", "Sedat");
        body.put("lastname", "Yurt");
        body.put("totalprice", 155);
        body.put("depositpaid", false);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-01-01");
        bookingdates.put("checkout", "2022-03-01");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Lunch");

        //Get Response
        Response response = RestAssured.given().contentType(ContentType.JSON).
                body(body.toString()).post("https://restful-booker.herokuapp.com/booking");
        return response;
    }
}
