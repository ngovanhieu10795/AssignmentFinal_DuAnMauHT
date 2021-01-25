package com.example.myapplication.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.controller.TheLoaiDAO;


public class ChiTietTheLoaiActivity extends AppCompatActivity {


    TheLoaiDAO theLoaiDao;
    String matheloai, tentheloai, mota,vitri;
    private EditText edMaTheLoai;
    private EditText edTenTheLoai;
    private EditText edMota;
    private EditText edVitriTheloai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_the_loai);
        initView();

        setTitle("Chi Tiết Thể Loại");


        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        theLoaiDao = new TheLoaiDAO(ChiTietTheLoaiActivity.this);
        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        matheloai = b.getString("MATHELOAI");
        tentheloai = b.getString("TENTHELOAI");
        mota = b.getString("MOTA");
        vitri = b.getString("VITRI");

        edMaTheLoai.setText(matheloai);
        edTenTheLoai.setText(tentheloai);
        edMota.setText(mota);
        edVitriTheloai.setText(vitri);
    }

    private void initView() {

        edMaTheLoai = (EditText) findViewById(R.id.edMaTheLoai);
        edTenTheLoai = (EditText) findViewById(R.id.edTenTheLoai);
        edMota = (EditText) findViewById(R.id.edMota);
        edVitriTheloai = (EditText) findViewById(R.id.edVitriTheloai);
    }

    public void UpdateTheLoai(View view) {
        if (theLoaiDao.updateTheLoai(edMaTheLoai.getText().toString(),
                edTenTheLoai.getText().toString(), edMota.getText().toString(), edVitriTheloai.getText().toString()) > 0) {
            Toast.makeText(getApplicationContext(), "Lưu Thành Công", Toast.LENGTH_SHORT).show();

            Intent a = new Intent(ChiTietTheLoaiActivity.this, TheLoaiActivity.class);
            startActivity(a);
        }
    }

    public void HuyTheLoai(View view) {
        finish();
    }
}
