package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class UpdateBookingTests extends BaseTest{

    @Test
    public void updateBookingTests(){
        //Create Booking
        Response responseCreate = createBooking();
        responseCreate.print();

        //Get bookingID of New Booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Update Booking
        //Create JSON Body

        JSONObject body = new JSONObject();
        body.put("firstname", "Servet");
        body.put("lastname", "Yurt");
        body.put("totalprice", 112);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2022-01-01");
        bookingdates.put("checkout", "2022-03-01");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Lunch");

        //Update Response
        Response responseUpdate = RestAssured.given().auth().preemptive().basic("admin", "password123").
                contentType(ContentType.JSON).
                body(body.toString()).put("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseUpdate.print();

//        "username" : "admin",
//        "password" : "password123"

        //Verifications
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Servet", "First name in response is not expected!");

        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Yurt", "Last name in response is not expected!");

        int price = responseUpdate.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 112, "Total price in response is not expected!");

        boolean depositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid, "Deposit paid should be false, but it's not!");

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn, "2022-01-01", "CheckIn in response is not expected!");

        String actualCheckOut = responseUpdate.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckOut, "2022-03-01", "CheckOut in response is not expected!");

        String actualAdditionalNeeds = responseUpdate.jsonPath().getString("additionalneeds");
        softAssert.assertEquals(actualAdditionalNeeds, "Lunch", "additionalneeds is not expected!");

        softAssert.assertAll();
    }
}
