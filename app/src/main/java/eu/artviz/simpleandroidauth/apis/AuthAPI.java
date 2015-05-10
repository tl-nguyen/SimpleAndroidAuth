package eu.artviz.simpleandroidauth.apis;

import eu.artviz.simpleandroidauth.models.Auth;
import eu.artviz.simpleandroidauth.models.User;
import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by tl on 10.05.15.
 */
public interface AuthAPI {

    @POST("/auth/register")
    void register(@Body User user, Callback<Auth> response);

    @POST("/auth/login")
    void login(@Body User user, Callback<Auth> response);
}
