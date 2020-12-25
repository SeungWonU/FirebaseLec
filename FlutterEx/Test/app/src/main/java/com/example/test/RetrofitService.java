package com.example.test;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {


    @GET("v1/api/papago/translation/{text}")
    Call<PostResult> getPosts(@Path("text") String post);

}