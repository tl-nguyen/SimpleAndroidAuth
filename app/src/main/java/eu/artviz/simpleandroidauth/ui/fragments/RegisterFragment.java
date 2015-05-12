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


public class RegisterFragment extends Fragment {

    private OnCancelSelectedListener mCallback;

    CachedDb mCachedDb;

    @InjectView(R.id.etEmail)
    EditText mEtEmail;

    @InjectView(R.id.etPassword)
    EditText mEtPassword;

    @InjectView(R.id.btnRegister)
    Button mBtnRegister;

    @InjectView(R.id.btnCancel)
    Button mBtnCancel;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        ButterKnife.inject(this, rootView);

        mCachedDb = CachedDb.getInstance(getActivity());

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User newUser = new User();
                newUser.setEmail(mEtEmail.getText().toString());
                newUser.setPassword(mEtPassword.getText().toString());

                AuthService authService = RestServiceCreator.createAuthService();

                authService.register(newUser, new Callback<Auth>() {
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

        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onCancelSelected();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnCancelSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnCancelSelectedListener");
        }
    }

    public interface OnCancelSelectedListener {
        void onCancelSelected();
    }
}
