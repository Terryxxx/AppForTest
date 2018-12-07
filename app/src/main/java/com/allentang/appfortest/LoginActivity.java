package com.allentang.appfortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.allentang.appfortest.model.ResObj;
import com.allentang.appfortest.model.UserInfo;
import com.allentang.appfortest.remote.ApiUtils;
import com.allentang.appfortest.remote.UserService;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    // UI references.
    private EditText editEmail;
    private EditText editPassword;
    private Button btnLogin;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        userService = ApiUtils.getUserService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                if(validateLogin(email, password)){
                    // login
                    doLogin(email, password);
                }
            }
        });
    }

    private boolean validateLogin(String email, String password){
        // Check for a valid email address.
//        if(email == null || email.trim().length() == 0){
//            Toast.makeText(LoginActivity.this, "Email is required", Toast.LENGTH_SHORT).show();
//            return false;
//        }

        if(TextUtils.isEmpty(email)){
            Toast.makeText(LoginActivity.this, getString(R.string.error_field_required), Toast.LENGTH_SHORT).show();
            return false;
        }else if(!isEmailValid(email)){
            Toast.makeText(LoginActivity.this, getString(R.string.error_invalid_email), Toast.LENGTH_SHORT).show();
            return false;
        }

        // Check for a valid password, if the user entered one.
        if(!TextUtils.isEmpty(password) && !isPasswordValid(password)){
            Toast.makeText(LoginActivity.this, getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


    private void doLogin(final String email, final String password){
        Call<ResObj> call = userService.login(email, password);
        call.enqueue(new Callback<ResObj>() {
            @Override
            public void onResponse(Call<ResObj> call, Response<ResObj> response) {
                if(response.isSuccessful()){
                    ResObj resObj = response.body();
                    if(resObj.getCode().equals(0) && resObj.getMsg().equals("成功")){
                        Map<String, Object> data = resObj.getData();
                        UserInfo info = (UserInfo) data.get("content");
                        // login start main activity
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("Email", info.getEmail());
                        startActivity(intent);
                    }else {
                        Toast.makeText(LoginActivity.this, "This email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Error! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResObj> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isEmailValid(String email){
        return email.contains("@");
    }

    private boolean isPasswordValid(String password){
        return password.length() > 4;
    }

}
