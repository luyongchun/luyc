package com.luyc.bnd.myapplication.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luyc.bnd.myapplication.MainActivity;
import com.luyc.bnd.myapplication.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends BaseActivity {

    @InjectView(R.id.tv)
    TextView tv;
    @InjectView(R.id.et_userName)
    EditText etUserName;
    @InjectView(R.id.ll_user)
    LinearLayout llUser;
    @InjectView(R.id.textView)
    TextView textView;
    @InjectView(R.id.et_userPassword)
    EditText etUserPassword;
    @InjectView(R.id.ll_password)
    LinearLayout llPassword;
    @InjectView(R.id.btn_login)
    Button btnLogin;
    @InjectView(R.id.activity_login)
    RelativeLayout activityLogin;
    private String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        etUserPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                s = etUserPassword.getText().toString();
                if (!s.equals("")){
                    btnLogin.setBackgroundResource(R.drawable.shape_login_rbed);
                }else if (s.equals("")){
                    btnLogin.setBackgroundResource(R.drawable.shape_login_rb);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



    }

    public void onBtnLoginClick(View view) {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }


}
