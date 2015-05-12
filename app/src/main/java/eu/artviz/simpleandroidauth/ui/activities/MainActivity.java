package eu.artviz.simpleandroidauth.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import eu.artviz.simpleandroidauth.R;
import eu.artviz.simpleandroidauth.models.User;
import eu.artviz.simpleandroidauth.networking.RestServiceCreator;
import eu.artviz.simpleandroidauth.networking.UserService;
import eu.artviz.simpleandroidauth.utils.CachedDb;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends ActionBarActivity {

    private CachedDb mCachedDb;

    @InjectView(R.id.tvWelcome)
    TextView mTvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        mCachedDb = CachedDb.getInstance(this);

        String token = mCachedDb.getToken();

        if (token == null || token.isEmpty()) {
            Intent loginIntent = new Intent(this, AuthActivity.class);
            startActivity(loginIntent);
            finish();
        }

        if (mCachedDb.getCurrentUser() == null) {

            UserService userService = RestServiceCreator.createUserService();

            userService.getCurrentUser(new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    mTvWelcome.setText(user.getEmail());
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.d("Test", "Error: " + error.getMessage());
                }
            });
        } else {
            mTvWelcome.setText(mCachedDb.getCurrentUser().getEmail());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            mCachedDb.setToken(null);

            Intent authIntent = new Intent(this, AuthActivity.class);
            startActivity(authIntent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
