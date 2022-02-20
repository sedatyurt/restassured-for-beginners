package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeleteBookingTests extends BaseTest{

    @Test
    public void deleteBookingTests(){
        //Create Booking
        Response responseCreate = createBooking();
        responseCreate.print();

        //Get bookingID of New Booking
        int bookingid = responseCreate.jsonPath().getInt("bookingid");

        //Delete Booking
        Response responseDelete = RestAssured.given().auth().preemptive().basic("admin", "password123").
                delete("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseDelete.print();

        //Verification Response 201
        Assert.assertEquals(responseDelete.getStatusCode(), 201, "Status  code should be 201, but it's not!");

        //Get Response with Booking
        Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseGet.print();

        Assert.assertEquals(responseGet.getBody().asString(), "Not Found", "Body should be 'Not Found' but it's not!");
    }
}
