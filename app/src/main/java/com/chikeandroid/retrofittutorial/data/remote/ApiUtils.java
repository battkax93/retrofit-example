package com.chikeandroid.retrofittutorial.data.remote;

/**
 * Created by Chike on 12/4/2016.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://api.stackexchange.com/2.2/";

    private ApiUtils() {}

    //set baseUrl & time out here
    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL,30).create(SOService.class);
    }
}
