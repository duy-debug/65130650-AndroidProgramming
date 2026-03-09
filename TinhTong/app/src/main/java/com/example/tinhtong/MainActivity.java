package com.example.tinhtong;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtA, edtB;
    Button btnTong;
    TextView edtKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // Sự kiện tính tổng
    public void XuLyCong(View view){
        // ánh xạ ID
        edtA = findViewById(R.id.edtA);
        edtB = findViewById(R.id.edtB);
        btnTong = findViewById(R.id.btnTong);
        edtKQ = findViewById(R.id.edtKQ);
        try {
            double a = Double.parseDouble(edtA.getText().toString());
            double b = Double.parseDouble(edtB.getText().toString());
            double tong = a + b;
            edtKQ.setText(String.valueOf(tong));
        }
        catch (Exception e){
            edtKQ.setText("Lỗi nhập liệu!");
        }
    }
}