package com.herokuapp.restfulbooker;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateBookingTests extends BaseTest{

    @Test
    public void createBookingTests(){
        Response response = createBooking();
        response.print();

        //Verifications
        SoftAssert softAssert = new SoftAssert();
        String actualFirstName = response.jsonPath().getString("booking.firstname");
        softAssert.assertEquals(actualFirstName, "Sedat", "First name in response is not expected!");

        String actualLastName = response.jsonPath().getString("booking.lastname");
        softAssert.assertEquals(actualLastName, "Yurt", "Last name in response is not expected!");

        int price = response.jsonPath().getInt("booking.totalprice");
        softAssert.assertEquals(price, 155, "Total price in response is not expected!");

        boolean depositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        softAssert.assertFalse(depositPaid, "Deposit paid should be false, but it's not!");

        String actualCheckIn = response.jsonPath().getString("booking.bookingdates.checkin");
        softAssert.assertEquals(actualCheckIn, "2022-01-01", "CheckIn in response is not expected!");

        String actualCheckOut = response.jsonPath().getString("booking.bookingdates.checkout");
        softAssert.assertEquals(actualCheckOut, "2022-03-01", "CheckOut in response is not expected!");

        String actualAdditionalNeeds = response.jsonPath().getString("booking.additionalneeds");
        softAssert.assertEquals(actualAdditionalNeeds, "Lunch", "additionalneeds is not expected!");

        softAssert.assertAll();
    }
}
