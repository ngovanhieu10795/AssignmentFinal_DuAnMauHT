package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.dao.NguoiDungDao;
import com.example.myapplication.model.NguoiDung;


public class ThemNguoiDungActivity extends AppCompatActivity {

    private EditText edUserName;
    private EditText edfullname;
    private EditText edtPassword;
    private EditText edtPassword2;
    private EditText edtGmail;
    private Button btnadduser;
    private Button btnlistnguoidung;
    NguoiDungDao nguoiDungDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nguoi_dung);
        setTitle("Thêm Người Dùng");
        initView();


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        btnlistnguoidung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThemNguoiDungActivity.this, NguoidungActivity.class);
                startActivity(intent);
            }
        });
        btnadduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
            }
        });

    }

    private void initView() {

        edUserName = (EditText) findViewById(R.id.edUserName);
        edfullname = (EditText) findViewById(R.id.edfullname);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtPassword2 = (EditText) findViewById(R.id.edtPassword2);
        edtGmail = (EditText) findViewById(R.id.edtGmail);
        btnadduser = (Button) findViewById(R.id.btnadduser);
        btnlistnguoidung = (Button) findViewById(R.id.btnlistnguoidung);
    }

    public void addUser() {
        nguoiDungDao = new NguoiDungDao(ThemNguoiDungActivity.this);
        NguoiDung user = new NguoiDung(edUserName.getText().toString(), edtPassword.getText().toString(),
                edtGmail.getText().toString(), edfullname.getText().toString());
        try {
            if (validateForm()>0){
                if (nguoiDungDao.insertNguoiDung(user)>0){
                    Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    Intent b = new Intent(ThemNguoiDungActivity.this, NguoidungActivity.class);
                    startActivity(b);

                }else {
                    Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e){
            Log.e("Error",e.toString());

        }


    }

    public int validateForm() {
        int check = 1;
        if (edUserName.getText().length() == 0 || edfullname.getText().length() == 0
                || edtGmail.getText().length() == 0 || edtPassword.getText().length() == 0
                || edtPassword2.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String pass = edtPassword.getText().toString();
            String rePass= edtPassword2.getText().toString();
            if (pass.length()<6){
                edtPassword.setError(getString(R.string.notify_length_pass));
                check = -1;
            }
            if (!pass.equals(rePass)){
               edtPassword2.setError("Mật khẩu phải trùng nhau");
                check =-1;
            }

        }
        return check;
    }
}

