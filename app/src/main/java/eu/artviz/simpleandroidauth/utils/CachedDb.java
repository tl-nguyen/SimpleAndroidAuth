package eu.artviz.simpleandroidauth.utils;

import android.content.Context;

import eu.artviz.simpleandroidauth.models.User;


public class CachedDb {
    private static CachedDb instance;

    private AuthToken authToken;

    private User currentUser;
    private String token;

    private CachedDb() {
    }

    public static CachedDb getInstance(Context context) {
        if (instance == null) {
            synchronized (CachedDb.class) {
                if (instance == null) {
                    instance = new CachedDb();
                }
            }
        }

        instance.setAuthToken(new AuthToken(context));

        return instance;
    }

    private void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getToken() {
        if (authToken == null) {
            throw new NullPointerException("You have to set authToken before getting the token");
        }

        if (token == null) {
            token = authToken.getToken();
        }

        return token;
    }

    public void setToken(String token) {
        if (authToken == null) {
            throw new NullPointerException("You have to set authToken before setting the token");
        }

        this.authToken.setToken(token);

        this.token = token;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
