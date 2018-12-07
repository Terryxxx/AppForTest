package com.allentang.appfortest.remote;

import com.allentang.appfortest.model.ResObj;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("login/{email}/{password}")
    Call<ResObj> login(@Path("email") String email, @Path("password") String password);

}
