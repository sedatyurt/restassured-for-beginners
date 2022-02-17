package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class GetBookingIdsTests {

    @Test
    public void getBookingIdsWithoutFilterTest(){
        //Get Response with Booking IDs
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking");
        response.print();

        //Verify Response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status  code should be 200, but it's not!");

        //Verify at least 1 Booking ID in Response
        List<Integer> bookingIDs = response.jsonPath().getList("bookingid");
        Assert.assertFalse(bookingIDs.isEmpty(), "List of bookingIDs is empty, but it shouldn't be!");

        //TODO HW: Create new class and new test method for that Get a booking endpoint

    }
}
