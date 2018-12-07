package com.allentang.appfortest.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://172.18.28.95:8004/user/";

    public static UserService getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }

}
