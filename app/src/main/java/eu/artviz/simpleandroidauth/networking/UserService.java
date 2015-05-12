package eu.artviz.simpleandroidauth.networking;


import eu.artviz.simpleandroidauth.models.User;
import retrofit.Callback;
import retrofit.http.POST;

public interface UserService {
    @POST("/user/current")
    void getCurrentUser(Callback<User> response);
}
