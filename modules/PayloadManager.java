package com.E2EIntegration.modules;


import com.E2EIntegration.payloads.Booking;
import com.E2EIntegration.payloads.BookingDates;
import com.E2EIntegration.payloads.Auth;
import com.google.gson.Gson;


public class PayloadManager {
    Gson gson;
    public String createPayload() {

        Booking booking =new Booking();
        booking.setFirstname("Prasad");
        booking.setLastname("N");
        booking.setTotalprice(111);
        booking.setDepositpaid(true);
        BookingDates bookingdates = new BookingDates();
        bookingdates.setCheckin("2024-12-25");
        bookingdates.setCheckout("2024-12-30");
        booking.setBookingDates(bookingdates);
        booking.setAdditionalneeds("Breakfast and Lunch");


        gson = new Gson();
        String bookingString = gson.toJson(booking);
        return bookingString;
    }

    public String createToken()
    {

        Auth auth = new Auth();
        auth.setUsername("admin");
        auth.setPassword("password123");
        gson = new Gson();
        String authpayload = gson.toJson(auth);
        return authpayload;

    }

    public String updatePayload() {

        Booking updateBooking = new Booking();
        updateBooking.setFirstname("PrasadNew");
        updateBooking.setLastname("Narala");
        updateBooking.setTotalprice(112);
        updateBooking.setDepositpaid(true);

        BookingDates bookingDates2 = new BookingDates();
        bookingDates2.setCheckin("2024-12-20");
        bookingDates2.setCheckout("2024-12-24");

        updateBooking.setBookingDates(bookingDates2);
        updateBooking.setAdditionalneeds("Breakfast, Lunch and Dinner");

        Gson gson1 = new Gson();
        String updateBookingContent = gson1.toJson(updateBooking);
        return updateBookingContent;
}
}