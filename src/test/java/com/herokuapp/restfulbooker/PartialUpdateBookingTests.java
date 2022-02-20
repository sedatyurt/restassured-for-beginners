package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class PartialUpdateBookingTests extends BaseTest{

    @Test
    public void partialUpdateBookingTests(){
        //Create Booking
        Response responseCreate = createBooking();
        responseCreate.print();

        //Get bookingID of New Booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Update Booking
        //Create JSON Body

        JSONObject body = new JSONObject();
        body.put("firstname", "Servet");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-02-01");

        body.put("bookingdates", bookingdates);

        //Update Response
        Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin", "password123").
                contentType(ContentType.JSON).
                body(body.toString()).patch("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseUpdate.print();

//        "username" : "admin",
//        "password" : "password123"

        //Verifications
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Servet", "First name in response is not expected!");

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn, "2022-02-01", "CheckIn in response is not expected!");

        softAssert.assertAll();
    }
}
