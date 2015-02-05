package com.richluick.android.roomie.ui.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseFacebookUtils;
import com.parse.ParseUser;
import com.richluick.android.roomie.R;
import com.richluick.android.roomie.facebook.FacebookRequest;
import com.richluick.android.roomie.utils.Constants;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(ParseUser.getCurrentUser().isAuthenticated()) {
            if(checkIfAlreadyOnBoarded()) {
                mainIntent();
            }
            else {
                onBoardIntent();
            }
        }

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseFacebookUtils.logIn(LoginActivity.this, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user == null) {
                            //todo:handle sign in errors
                        }
                        else if (user.isNew()) {
                            onBoardIntent();
                        }
                        else {
                            mainIntent();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ParseFacebookUtils.finishAuthentication(requestCode, resultCode, data);
    }

    /**
     * This method  checks if the user has already gone through the onboard process and either
     * sends them to onboarding if they have not or skips that step and brings them to the
     * Main Activity
     *
     * @return Boolean true or false depending on if the user has onboarded or not
     */
    private Boolean checkIfAlreadyOnBoarded() {
        SharedPreferences pref = getSharedPreferences(ParseUser.getCurrentUser().getUsername(),
                Context.MODE_PRIVATE);
        return pref.getBoolean(Constants.ALREADY_ONBOARD, false);
    }

    /**
     * This method  sends the user to onboarding using an intent and also set the current facebook
     * user id in the shared preferences.
     */
    private void onBoardIntent() {
        new FacebookRequest(this).setCurrentFacebookUser(); //sets the user to shared prefs

        Intent intent = new Intent(this, OnBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    /**
     * This method  sends the user to the main activity using an intent
     */
    private void mainIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
