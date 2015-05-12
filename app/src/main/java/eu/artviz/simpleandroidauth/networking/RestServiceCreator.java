package eu.artviz.simpleandroidauth.networking;

import eu.artviz.simpleandroidauth.Constants;
import retrofit.RestAdapter;

public class RestServiceCreator {
    private static RestAdapter adapter;

    private static RestAdapter getRestAdapter() {
        if (adapter == null) {
            adapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.API_URL_ENDPOINT)
                    .build();
        }

        return adapter;
    }

    public static AuthService createAuthService() {
        return getRestAdapter().create(AuthService.class);
    }

    public static UserService createUserService() {
        return getRestAdapter().create(UserService.class);
    }


}
