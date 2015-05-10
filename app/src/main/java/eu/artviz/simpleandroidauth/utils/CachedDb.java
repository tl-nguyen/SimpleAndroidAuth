package eu.artviz.simpleandroidauth.utils;

import eu.artviz.simpleandroidauth.models.User;


public class CachedDb {
    private static CachedDb instance;

    private AuthToken authToken;

    private User currentUser;
    private String token;


    private CachedDb() {
    }

    public static CachedDb getInstance() {
        if (instance == null) {
            synchronized (CachedDb.class) {
                if (instance == null) {
                    instance = new CachedDb();
                }
            }
        }

        return instance;
    }

    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }

    public String getToken() {
        if (token == null) {
            token = authToken.getToken();
        }

        return token;
    }

    public void setToken(String token) {
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
