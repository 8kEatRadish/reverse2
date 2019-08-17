package com.example.reverse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&")
    Call<Translation> getCall(@Query("w") String text);
}
