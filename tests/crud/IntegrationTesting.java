package com.E2EIntegration.tests.crud;


import com.E2EIntegration.modules.PayloadManager;
import com.E2EIntegration.actions.AssertActions;
import com.E2EIntegration.endpoints.APIConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.assertj.core.api.Assertions;

public class IntegrationTesting extends CRUDTypes  {

    PayloadManager payloadManager;
    AssertActions assertActions;
    @BeforeMethod
    public void setUpConfig() {
        payloadManager = new PayloadManager();
        assertActions = new AssertActions();
        requestSpecification = (RequestSpecification) new RequestSpecBuilder()
                .setBaseUri(APIConstants.BASE_URL)
                .addHeader("Content-Type", "application/json")
                .build().log().all();
    }
    @AfterMethod
    public void tearDown(){
    }

    @Test(priority = 1)
    @Owner("Ram Kumar")
    @Description("#Integration TC01 - Create a Booking, Update the Booking and then validate the Booking by Id.")
    public void createBooking_UpdateBooking_GetBookingByIdAndVerify() throws JsonProcessingException {
        testCreateBooking();
        testCreateToken();
        testUpdateBooking();
        testGetBookingById();
    }
    @Test(priority = 2)
    @Owner("Ram Kumar")
    @Description("#Integration TC02 - Create a Booking, Delete the Booking and then validate the Booking not exists in Get Booking Id.")
    public void createBooking_DeleteBooking_GetBookingByIdNotExists() throws JsonProcessingException {
        testCreateBooking();
        testCreateToken();
        testDeleteBooking();
        testGetBookingByIdNotExists();
    }
    @Test(priority = 3)
    @Owner("Ram Kumar")
    @Description("#Integration TC03 - Get an Existing Booking, Update the Booking and then validate the Booking by Id.")
    public void getExistingBooking_UpdateBooking_GetBookingIdAndVerify() throws JsonProcessingException {
        testGetExistingBooking();
        testUpdateBooking();
        testGetBookingById();
}

}