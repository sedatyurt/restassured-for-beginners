package com.herokuapp.restfulbooker;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GetBookingTests {

    @Test
    public void getBookingTest(){
        //Get Response with Booking
        Response response = RestAssured.get("https://restful-booker.herokuapp.com/booking/1");
        response.print();

        //Verify Response 200
        Assert.assertEquals(response.getStatusCode(), 200, "Status  code should be 200, but it's not!");

        //Verify All Fields
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("firstname");
        softAssert.assertEquals(actualFirstName, "Mary", "First name in response is not expected!");

        String actualLastName = response.jsonPath().getString("lastname");
        softAssert.assertEquals(actualLastName, "Smith", "Last name in response is not expected!");

        int price = response.jsonPath().getInt("totalprice");
        softAssert.assertEquals(price, 823, "Total price in response is not expected!");

        boolean depositPaid = response.jsonPath().getBoolean("depositpaid");
        softAssert.assertTrue(depositPaid, "Deposit paid should be true, but it's not!");

        String actualCheckIn = response.jsonPath().getString("bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn, "2018-02-16", "CheckIn in response is not expected!");

        String actualCheckOut = response.jsonPath().getString("bookingdates.checkout");
        softAssert.assertEquals(actualCheckOut, "2018-05-27", "CheckOut in response is not expected!");

        softAssert.assertAll();
    }
}
