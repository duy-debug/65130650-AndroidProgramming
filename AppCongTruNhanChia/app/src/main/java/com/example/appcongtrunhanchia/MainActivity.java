package com.example.appcongtrunhanchia;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // Khai báo các đối tượng gắn với điều khiển tương ứng ở đây
    EditText editText1;
    EditText editText2;
    EditText editText3;
    Button nutCong, nutTru, nutNhan, nutChia;
    void TimDieuKhien(){
        editText1 = (EditText) findViewById(R.id.edtSoThuNhat);
        editText2 = (EditText) findViewById(R.id.edtSoThuHai);
        editText3 = (EditText) findViewById(R.id.edtKetQua);
        nutCong = (Button) findViewById(R.id.btnCong);
        nutTru = (Button) findViewById(R.id.btnTru);
        nutNhan = (Button) findViewById(R.id.btnNhan);
        nutChia = (Button) findViewById(R.id.btnChia);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        TimDieuKhien();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void XuLyCong(View v){
        try{
            // b1. Lấy dữ liệu 2 số

            // b2. Lấy dữ liệu từ 2 điều khiển trên
            String soThu1 = editText1.getText().toString();
            String soThu2 =editText2.getText().toString();
            // b3. Chuyển dữ liệu từ chuỗi sang số
            float so1 = Float.parseFloat(soThu1);
            float so2 = Float.parseFloat(soThu2);
            // Tính toán
            float tong = so1 + so2;
            // b4. Hiển thị kết quả

            String chuoiKetQua = String.valueOf(tong);
            // b5. Gán kết quả
            editText3.setText(chuoiKetQua);
        } catch (Exception e) {
            editText3.setText("Lỗi nhập liệu vui lòng nhập lại");
        }
    }
    public void XuLyTru(View v){
        try{
            // b1. Lấy dữ liệu 2 số

            // b2. Lấy dữ liệu từ 2 điều khiển trên
            String soThu1 = editText1.getText().toString();
            String soThu2 =editText2.getText().toString();
            // b3. Chuyển dữ liệu từ chuỗi sang số
            float so1 = Float.parseFloat(soThu1);
            float so2 = Float.parseFloat(soThu2);
            // Tính toán
            float tong = so1 - so2;
            // b4. Hiển thị kết quả

            String chuoiKetQua = String.valueOf(tong);
            // b5. Gán kết quả
            editText3.setText(chuoiKetQua);
        } catch (Exception e) {
            editText3.setText("Lỗi nhập liệu vui lòng nhập lại");
        }
    }
    public void XuLyNhan(View v){
        try{
            // b1. Lấy dữ liệu 2 số

            // b2. Lấy dữ liệu từ 2 điều khiển trên
            String soThu1 = editText1.getText().toString();
            String soThu2 =editText2.getText().toString();
            // b3. Chuyển dữ liệu từ chuỗi sang số
            float so1 = Float.parseFloat(soThu1);
            float so2 = Float.parseFloat(soThu2);
            // Tính toán
            float tong = so1 * so2;
            // b4. Hiển thị kết quả

            String chuoiKetQua = String.valueOf(tong);
            // b5. Gán kết quả
            editText3.setText(chuoiKetQua);
        } catch (Exception e) {
            editText3.setText("Lỗi nhập liệu vui lòng nhập lại");
        }
    }
    public void XuLyChia(View v){
        try{
            // b1. Lấy dữ liệu 2 số

            // b2. Lấy dữ liệu từ 2 điều khiển trên
            String soThu1 = editText1.getText().toString();
            String soThu2 =editText2.getText().toString();
            // b3. Chuyển dữ liệu từ chuỗi sang số
            float so1 = Float.parseFloat(soThu1);
            float so2 = Float.parseFloat(soThu2);
            if(so2 == 0){
                editText3.setText("Không thể chia cho 0");
                return;
            }
            // Tính toán
            float tong = so1 / so2;
            // b4. Hiển thị kết quả

            String chuoiKetQua = String.valueOf(tong);
            // b5. Gán kết quả
            editText3.setText(chuoiKetQua);
        } catch (Exception e) {
            editText3.setText("Lỗi nhập liệu vui lòng nhập lại");
        }
    }
}