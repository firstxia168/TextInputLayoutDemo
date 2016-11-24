package com.xialm.textinputlayoutdemo;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout layout_name;
    private TextInputLayout layout_pwd;
    private Button btn_login;
    private EditText et_name;
    private EditText et_pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        layout_name = (TextInputLayout) findViewById(R.id.layout_name);
        layout_pwd = (TextInputLayout) findViewById(R.id.layout_pwd);

        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd = (EditText) findViewById(R.id.et_pwd);

        btn_login = (Button) findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        et_name.addTextChangedListener(new MyTextWatcher(et_name));
        et_pwd.addTextChangedListener(new MyTextWatcher(et_pwd));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                canLogin();
                break;
            default:
                break;

        }
    }

    private void canLogin() {
        if (!isNameValid() || !isPwdValid()) {
            Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
    }

    private class MyTextWatcher implements TextWatcher {
        private View view;

        public MyTextWatcher(View view) {

            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            switch (view.getId()) {
                case R.id.et_name:
                    isNameValid();
                    break;
                case R.id.et_pwd:
                    isPwdValid();
                    break;
            }
        }
    }

    private boolean isPwdValid() {
        String pwdStr = et_pwd.getText().toString().trim();
        if ("".equals(pwdStr) || TextUtils.isEmpty(pwdStr)) {
            layout_pwd.setErrorEnabled(true);
//            et_pwd.setError(getResources().getString(R.string.error_pwd)); //如果给EditText设置setError时,弹出的提示错误是在输入框的右侧,以PopupWindow的形式,上对齐et的底部弹出
            layout_pwd.setError(getResources().getString(R.string.error_pwd)); //如果给TextInputLayout设置setError时,弹出的错误信息是在整个TextInputLayout布局的左下角,直接显示文本的
            et_pwd.requestFocus();
            return false;
        }
        layout_pwd.setErrorEnabled(false);
        return true;
    }

    private boolean isNameValid() {
        String nameStr = et_name.getText().toString().trim();
        if ("".equals(nameStr) || TextUtils.isEmpty(nameStr)) {
            layout_name.setErrorEnabled(true); //开启错误提示
//            et_name.setError(getResources().getString(R.string.error_name));
            layout_name.setError(getResources().getString(R.string.error_name)); //设置要显示的错误提示的文字
            et_name.requestFocus();
            return false;
        }
        layout_name.setErrorEnabled(false); //关闭错误提示(记得及时关闭,否则会一直出现)
        return true;
    }
}
