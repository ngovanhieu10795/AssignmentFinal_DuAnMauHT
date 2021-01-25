package com.example.myapplication.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;


import com.example.myapplication.R;
import com.example.myapplication.controller.HoaDonChiTietDAO;
import com.example.myapplication.customAdapter.CartAdapter;
import com.example.myapplication.model.HoaDonChiTiet;

import java.util.ArrayList;
import java.util.List;

public class ListHoaDonChiTietByIDActivity extends AppCompatActivity {
    public List<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    CartAdapter adapter = null;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("HOÁ ĐƠN CHI TIẾT");
        setContentView(R.layout.activity_list_hoa_don_chi_tiet_by_i_d);
        lvCart = (ListView) findViewById(R.id.lvHoaDonChiTiet);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(ListHoaDonChiTietByIDActivity.this);
        Intent in = getIntent();
        Bundle b = in.getExtras();
        if (b != null) {
            dsHDCT = hoaDonChiTietDAO.getAllHoaDonChiTietByID(b.getString("MAHOADON"));
        }
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);
    }
}