package com.E2EIntegration.tests.crud;


import com.E2EIntegration.modules.PayloadManager;
import com.E2EIntegration.actions.AssertActions;
import com.E2EIntegration.endpoints.APIConstants;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CRUDTypes {
    PayloadManager payloadManager;
    AssertActions assertActions;

    String token;
    int bookingId;
    public RequestSpecification requestSpecification;

    @Owner("Prasad Narala")
    @Description("Verify that the Status Code is 200 when we Create a Booking.")
    public void testCreateBooking() throws JsonProcessingException {

        requestSpecification.basePath(APIConstants.CREATE_BOOKING);
        payloadManager = new PayloadManager();
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .when().body(payloadManager.createPayload())
                .post().then().log().all();
        response.time(Matchers.lessThan(10000L));
        response.statusCode(200);
        JsonPath jsonPath = response.extract().jsonPath();
        bookingId = jsonPath.get("bookingid");
        String firstName = jsonPath.get("booking.firstname");
        System.out.println(firstName);
        System.out.println("Booking is created and the bookingId is "+bookingId);
        Assert.assertNotNull(bookingId);
        Assertions.assertThat(bookingId).isNotNull();
        Assertions.assertThat(firstName).isEqualToIgnoringCase("prasad").isNotEqualTo("jack");
    }

    @Owner("Ram kumar")
    @Description("Verify that the Status Code is 200 when we Create a Token.")
    public void testCreateToken() {

        requestSpecification.basePath(APIConstants.CREATE_TOKEN);
        payloadManager = new PayloadManager();
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .body(payloadManager.createToken())
                .post()
                .then().log().all()
                .statusCode(200);
        response.body("token", Matchers.notNullValue());
        response.body("token.length()",Matchers.equalTo(15));
        //Extract the token
        token = response.extract().path("token");
        System.out.println("The generated token is : "+token);
        Assertions.assertThat(token).isNotNull().hasSize(15);
    }

    @Owner("Ram kumar")
    @Description("Verify that the Status Code is 200 When we Update an existing Booking.")
    public void testUpdateBooking() throws JsonProcessingException {
//        testCreateToken();
//        testCreateBooking();
        payloadManager = new PayloadManager();
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+bookingId);

        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .body(payloadManager.updatePayload())
                .when().put()
                .then().log().all()
                .assertThat().statusCode(200)
                .body("firstname", Matchers.equalTo("PrasadNew"))
                .body("totalprice", Matchers.equalTo(112))
                .assertThat().body("firstname",Matchers.equalTo("PrasadNew"));

        JsonPath jsonPath = response.extract().jsonPath();
        String firstName = jsonPath.getString("firstname");
        Assertions.assertThat(firstName).isEqualTo("PrasadNew");
    }
    @Owner("Ram kumar")
    @Description("Verify that the Booking Id is retrieved from Get Booking Id.")
    public void testGetBookingById() {
        payloadManager = new PayloadManager();
        requestSpecification.basePath(APIConstants.UPDATE_BOOKING+bookingId);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .assertThat().statusCode(200);

        JsonPath jsonPath = response.extract().jsonPath();
        String firstName = jsonPath.getString("firstname");
        Assertions.assertThat(firstName).isEqualTo("PrasadNew");
    }
    @Owner("Ram kumar")
    @Description("Verify that the Booking Id not exists from Get Booking Id.")
    public void testGetBookingByIdNotExists() {
        payloadManager = new PayloadManager();
        requestSpecification.basePath(APIConstants.GET_SINGLE_BOOKING+bookingId);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .assertThat().statusCode(404);
    }

    @Owner("Ram kumar")
    @Description("Verify that the Created Booking is Deleted")
    public void testDeleteBooking(){
        requestSpecification.basePath(APIConstants.DELETE_BOOKING+bookingId);
        payloadManager = new PayloadManager();
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .cookie("token",token)
                .delete()
                .then().log().all()
                .assertThat().statusCode(201);
        response.extract().asString().equals("Created");

    }

    public void testGetExistingBooking(){

        payloadManager = new PayloadManager();
        requestSpecification.basePath(APIConstants.GET_ALL_BOOKINGS);
        ValidatableResponse response = RestAssured.given().spec(requestSpecification)
                .contentType(ContentType.JSON)
                .when().get()
                .then().log().all()
                .assertThat().statusCode(200);
        JsonPath jsonPath = response.extract().jsonPath();
        bookingId = jsonPath.getInt("[0].bookingid");
}

}