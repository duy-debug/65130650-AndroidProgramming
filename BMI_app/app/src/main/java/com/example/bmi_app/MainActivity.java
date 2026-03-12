package com.example.bmi_app;

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
    EditText edtWeight, edtHeight, edtBMI, edtResult;
    Button btnCalculate, btnReset;
    public void timDieuKhien(){
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtHeight = (EditText) findViewById(R.id.edtHeight);
        edtBMI = (EditText) findViewById(R.id.edtBMI);
        edtResult = (EditText) findViewById(R.id.edtResult);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnReset = (Button) findViewById(R.id.btnReset);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        timDieuKhien();
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    // b1. Lấy chỉ số
                    String strWeight = edtWeight.getText().toString();
                    String strHeight = edtHeight.getText().toString();
                    // b2 Chuyển dữ liệu từ chuỗi sang số
                    float weight = Float.parseFloat(strWeight);
                    float height = Float.parseFloat(strHeight);
                    // Tính toán
                    String danhGia;
                    float bmi = weight / (height * height);
                    if(bmi < 18.5){
                        danhGia = "Gầy";
                    }
                    else if(bmi < 25){
                        danhGia = "Bình thường";
                    }
                    else if(bmi < 30){
                        danhGia = "Thừa cân";
                    }
                    else{
                        danhGia = "Béo phì";
                    }
                    edtBMI.setText(String.format("%.2f", bmi));
                    edtResult.setText(danhGia);
                } catch (Exception e) {
                    edtBMI.setText("Lỗi nhập liệu!");
                    edtResult.setText("Lỗi nhập liệu!");
                }
            }
        });
        btnReset.setOnClickListener(
                new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edtWeight.setText("");
                    edtHeight.setText("");
                    edtBMI.setText("");
                    edtResult.setText("");
                }
            }
        );
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}