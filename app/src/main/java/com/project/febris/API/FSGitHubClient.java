package com.project.febris.API;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FSGitHubClient {

    @GET("/users/{user}}/repos")
    Call<List<FSGitHubRepo>> reposForUser(@Path("user") String user);


}
