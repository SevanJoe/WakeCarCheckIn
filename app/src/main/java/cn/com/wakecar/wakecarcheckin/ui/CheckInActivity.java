package cn.com.wakecar.wakecarcheckin.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import cn.com.wakecar.wakecarcheckin.Constants;
import cn.com.wakecar.wakecarcheckin.R;
import cn.com.wakecar.wakecarcheckin.network.WakeCarApiClient;
import cn.com.wakecar.wakecarcheckin.utils.InputUtil;
import cn.com.wakecar.wakecarcheckin.utils.PreferencesHelper;

public class CheckInActivity extends ActionBarActivity {
    private View mLoginView, mSignInView;
    private AutoCompleteTextView mLoginEmailText;
    private EditText mLoginPasswordText;
    private Button mSignInButton, mRetryButton;
    private ProgressBar mLoginProgress;
    private TextView mLoginText;

    private String mEmailString, mPasswordString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_check_in);

        initViews();
    }

    private void initViews() {
        mLoginView = findViewById(R.id.login_form);
        mSignInView = findViewById(R.id.sign_in_form);
        mLoginEmailText = (AutoCompleteTextView) findViewById(R.id.login_email);
        mLoginPasswordText = (EditText) findViewById(R.id.login_password);
        mLoginProgress = (ProgressBar) findViewById(R.id.login_progress);
        mLoginText = (TextView) findViewById(R.id.login_text);
        mSignInButton = (Button) findViewById(R.id.sign_in_button);
        mRetryButton = (Button) findViewById(R.id.retry_button);

        mLoginEmailText.setText(PreferencesHelper.getStringForKey(Constants.PREFS_EMAIL, ""));
        mLoginPasswordText.setText(PreferencesHelper.getStringForKey(Constants.PREFS_PASSWORD, ""));

        String [] emails = new String[Constants.USER_NAMES.length];
        for (int i = 0; i < emails.length; ++i) {
            emails[i] = Constants.USER_NAMES[i] + Constants.EMAIL_SUFFIX;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, emails);
        mLoginEmailText.setAdapter(arrayAdapter);
        mLoginEmailText.setThreshold(1);
    }

    public void login(View view) {
        InputUtil.hideSoftKeyboard(this);

        mEmailString = mLoginEmailText.getText().toString();
        mPasswordString = mLoginPasswordText.getText().toString();

        PreferencesHelper.setStringForKey(Constants.PREFS_EMAIL, mEmailString);
        PreferencesHelper.setStringForKey(Constants.PREFS_PASSWORD, mPasswordString);

        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "guest");
        requestParams.put("a", "login");
        requestParams.put("email", mEmailString);
        requestParams.put("password", mPasswordString);
        WakeCarApiClient.post(requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                if (bytes == null)
                    loginResult(false);
                else
                    loginResult(true);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                loginResult(false);
            }
        });

        mLoginView.setVisibility(View.GONE);
        mSignInView.setVisibility(View.VISIBLE);
        mSignInButton.setVisibility(View.GONE);
    }

    private void loginResult(boolean isSuccess) {
        mLoginProgress.setVisibility(View.GONE);
        if (isSuccess) {
            mLoginText.setText(getString(R.string.login_success));
            mSignInButton.setVisibility(View.VISIBLE);
        } else {
            mLoginText.setText(getString(R.string.login_failed));
            mRetryButton.setVisibility(View.VISIBLE);
        }
    }

    public void signIn(View view) {
        RequestParams requestParams = new RequestParams();
        requestParams.put("c", "signin");
        requestParams.put("a", "signin");

        WakeCarApiClient.post(requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                signInResult(bytes);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                signInResult(bytes);
            }
        });

        mLoginProgress.setVisibility(View.VISIBLE);
        mLoginText.setText(getString(R.string.sign_in_progress));
    }

    private void signInResult(byte[] bytes) {
        mLoginProgress.setVisibility(View.GONE);
        mLoginText.setText(getString(R.string.sign_in_failed));
        if (bytes != null)
            mLoginText.setText(Html.fromHtml(new String(bytes)));
    }

    public void retryLogin(View view) {
        mRetryButton.setVisibility(View.GONE);
        login(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.check_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Toast.makeText(this, getString(R.string.developing), Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
