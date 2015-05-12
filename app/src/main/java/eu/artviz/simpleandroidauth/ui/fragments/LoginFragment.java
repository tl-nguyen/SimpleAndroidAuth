package eu.artviz.simpleandroidauth.ui.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.models.Auth;
import eu.artviz.simpleandroidauth.models.User;
import eu.artviz.simpleandroidauth.networking.AuthService;
import eu.artviz.simpleandroidauth.networking.RestServiceCreator;
import eu.artviz.simpleandroidauth.ui.activities.MainActivity;
import eu.artviz.simpleandroidauth.utils.CachedDb;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;


public class LoginFragment extends Fragment {

    private OnRegisterSelectedListener mCallback;
    private CachedDb mCachedDb;

    @InjectView(R.id.etEmail)
    EditText mEtEmail;

    @InjectView(R.id.etPassword)
    EditText mEtPassword;

    @InjectView(R.id.tvRegister)
    TextView mTvRegister;

    @InjectView(R.id.btnLogin)
    Button mBtnLogin;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        ButterKnife.inject(this, rootView);

        mCachedDb = CachedDb.getInstance(getActivity());

        mTvRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCallback.onRegisterSelected();
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                User user = new User();
                user.setEmail(mEtEmail.getText().toString());
                user.setPassword(mEtPassword.getText().toString());

                AuthService authService = RestServiceCreator.createAuthService();

                authService.login(user, new Callback<Auth>() {
                    @Override
                    public void success(Auth auth, Response response) {
                        mCachedDb.setToken(auth.getToken());
                        mCachedDb.setCurrentUser(auth.getUser());

                        Intent mainIntent = new Intent(getActivity(), MainActivity.class);
                        startActivity(mainIntent);

                        getActivity().finish();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        String errorResponseBody = new String(((TypedByteArray) error.getResponse().getBody()).getBytes());
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
