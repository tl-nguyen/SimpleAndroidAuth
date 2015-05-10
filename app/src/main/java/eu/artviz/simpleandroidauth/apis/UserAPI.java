package eu.artviz.simpleandroidauth.apis;


import eu.artviz.simpleandroidauth.models.User;
import retrofit.Callback;
import retrofit.http.POST;

public interface UserAPI {
    @POST("/auth/register")
    void getCurrentUser(Callback<User> response);
}
