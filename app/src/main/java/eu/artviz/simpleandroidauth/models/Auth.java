package eu.artviz.simpleandroidauth.models;

/**
 * Created by tl on 10.05.15.
 */
public class Auth {
    private User user;
    private String token;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
