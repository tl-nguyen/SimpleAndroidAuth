package eu.artviz.simpleandroidauth.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import eu.artviz.simpleandroidauth.Constants;
import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.apis.AuthAPI;
import eu.artviz.simpleandroidauth.models.Auth;
import eu.artviz.simpleandroidauth.models.User;
import eu.artviz.simpleandroidauth.utils.AuthToken;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class LoginFragment extends Fragment {


    private OnRegisterSelectedListener mCallback;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        final EditText etEmail = (EditText) rootView.findViewById(R.id.etEmail);
        final EditText etPassword = (EditText) rootView.findViewById(R.id.etPassword);

        TextView tvRegister = (TextView) rootView.findViewById(R.id.tvRegister);
        Button btnLogin = (Button) rootView.findViewById(R.id.btnLogin);

        tvRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCallback.onRegisterSelected();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                RestAdapter adapter = new RestAdapter.Builder()
                        .setEndpoint(Constants.API_URL_ENDPOINT)
                        .build();

                AuthAPI authAPI = adapter.create(AuthAPI.class);

                User user = new User();
                user.setEmail(etEmail.getText().toString());
                user.setPassword(etPassword.getText().toString());

                authAPI.login(user , new Callback<Auth>() {
                    @Override
                    public void success(Auth auth, Response response) {
                        Log.d("Test", auth.getUser().getEmail());
                        Log.d("Test", auth.getToken());

                        AuthToken.setToken(getActivity(), auth.getToken());
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String errorResponseBody =  new String(((TypedByteArray)error.getResponse().getBody()).getBytes());
                        Log.d("Test", "Error: " + error.getMessage());
                        Log.d("Test", errorResponseBody);
                    }
                });
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnRegisterSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnRegisterSelectedListener");
        }
    }

    public interface OnRegisterSelectedListener {
        void onRegisterSelected();
    }
}
