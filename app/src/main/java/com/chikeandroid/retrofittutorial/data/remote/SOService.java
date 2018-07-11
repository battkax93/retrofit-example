package com.chikeandroid.retrofittutorial.data.remote;

import com.chikeandroid.retrofittutorial.data.model.Answer;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chike on 12/3/2016.
 */


public interface SOService {

    String url = "/answers?order=desc&sort=activity&site=stackoverflow";

    @GET(url)
    Call<Answer> getAnswers();

    @GET(url)
    Call<Answer> getDisplayImage();
    // RxJava
    // Observable<SOAnswersResponse> getAnswers();

    @GET(url)
    Call<List<Answer>> getAnswers(@Query("tagged") String tags);


}
