package com.E2EIntegration.endpoints;

import com.E2EIntegration.utils.FillowUtils;
import com.E2EIntegration.utils.PropertyReader;
import com.E2EIntegration.utils.YamlReader;

import java.io.FileNotFoundException;
import java.io.IOException;

public class APIConstants {

//    public static final String BASE_URL = FillowUtils.fetchDataFromXlsx("Sheet1","baseUrl","value");
    public static final String BASE_URL = "https://restful-booker.herokuapp.com";

    // Reading through PropertyReader File.......
    /*public static String BASE_URL;
    static {
        try {
            BASE_URL = PropertyReader.readyKey("baseUrl","config.properties");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    // Reading through Yaml File.......
   /* public static String BASE_URL;
    static {
        try {
            BASE_URL = YamlReader.readKey("Url","Data.yaml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/

    public static final String PING_HEALTH = "/ping";
    public static final String GET_ALL_BOOKINGS = "/booking";
    public static final String GET_SINGLE_BOOKING = "/booking/";
    public static final String CREATE_BOOKING = "/booking";
    public static final String CREATE_TOKEN = "/auth";
    public static final String UPDATE_BOOKING = "/booking/";
    public static final String PARTIAL_BOOKING = "/booking/";
    public static final String DELETE_BOOKING = "/booking/";


}