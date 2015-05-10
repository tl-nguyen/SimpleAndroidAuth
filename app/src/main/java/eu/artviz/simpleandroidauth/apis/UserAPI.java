package eu.artviz.simpleandroidauth.apis;


import java.util.List;

import eu.artviz.simpleandroidauth.models.User;
import retrofit.Callback;
import retrofit.http.POST;

public interface UserAPI {
    @POST("/auth/register")
    void getUsers(Callback<List<User>> response);
}
