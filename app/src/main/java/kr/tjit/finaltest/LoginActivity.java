package kr.tjit.finaltest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import kr.tjit.finaltest.datas.User;
import kr.tjit.finaltest.utils.ConnectServer;
import kr.tjit.finaltest.utils.GlobalData;

public class LoginActivity extends BaseActivity {

    private android.widget.EditText idEdt;
    private android.widget.EditText passwordEdt;
    private android.widget.Button signInBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectServer.postRequestLogin(mContext,
                        idEdt.getText().toString(),
                        passwordEdt.getText().toString(),
                        new ConnectServer.JsonResponseHandler() {
                    @Override
                    public void onResponse(JSONObject json) {
                        Log.d("로그인응답", json.toString());

                        try {
                            int code = json.getInt("code");
                            final String message = json.getString("message");

                            if (code == 200){
                                JSONObject data = json.getJSONObject("data");
                                JSONObject userJson = data.getJSONObject("user");

                                User user = User.getUserFromJson(userJson);
                                GlobalData.loginUser = user;
                                Log.d("로그인응답", "로그인한사람이름 : "+user.getName());

                                String token = data.getString("token");
                                GlobalData.token = token;

                                Intent intent = new Intent(mContext, MainActivity.class);
                                startActivity(intent);
                                finish();

                            }
                            else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }
                });

            }
        });

    }

    @Override
    public void setValues() {

    }

    @Override
    public void bindViews() {
        this.signInBtn = (Button) findViewById(R.id.signInBtn);
        this.passwordEdt = (EditText) findViewById(R.id.passwordEdt);
        this.idEdt = (EditText) findViewById(R.id.idEdt);

    }
}
