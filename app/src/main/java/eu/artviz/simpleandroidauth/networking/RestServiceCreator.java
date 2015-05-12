package eu.artviz.simpleandroidauth.networking;

import eu.artviz.simpleandroidauth.Constants;
import eu.artviz.simpleandroidauth.utils.CachedDb;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class RestServiceCreator {
    private static RestAdapter adapter;

    private static RestAdapter getRestAdapter() {

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if (CachedDb.getInstance(null).getToken() != null) {
                    request.addHeader("Authorization", "Bearer " + CachedDb.getInstance(null).getToken());
                }
            }
        };

        if (adapter == null) {
            adapter = new RestAdapter.Builder()
                    .setEndpoint(Constants.API_URL_ENDPOINT)
                    .setRequestInterceptor(requestInterceptor)
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
