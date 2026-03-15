package com.example.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText result, bieuThuc;
    Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8,
            btn9, btnCham, btnCong, btnTru, btnNhan, btnChia, btnBang, btnC, btnDEL;
    float so1=0, so2=so1;
    String phepTinh="";
    public void timDieuKhien(){
        result = findViewById(R.id.result);
        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnCong = findViewById(R.id.btnCong);
        btnTru = findViewById(R.id.btnTru);
        btnNhan = findViewById(R.id.btnNhan);
        btnChia = findViewById(R.id.btnChia);

        bieuThuc = findViewById(R.id.bieuThuc);
        btnBang = findViewById(R.id.btnBang);
        btnCham = findViewById(R.id.btnCham);
        btnDEL = findViewById(R.id.btnDEL);
        btnC = findViewById(R.id.btnC);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        timDieuKhien();
        btn1.setOnClickListener(v -> result.append("1"));
        btn2.setOnClickListener(v->result.append("2"));
        btn3.setOnClickListener(v->result.append("3"));
        btn4.setOnClickListener(v->result.append("4"));
        btn5.setOnClickListener(v->result.append("5"));
        btn6.setOnClickListener(v->result.append("6"));
        btn7.setOnClickListener(v->result.append("7"));
        btn8.setOnClickListener(v->result.append("8"));
        btn9.setOnClickListener(v->result.append("9"));
        btn0.setOnClickListener(v->result.append("0"));
        btnCham.setOnClickListener(v->result.append("."));
        // Phep cong
        btnCong.setOnClickListener(v->{
            so1 = Float.parseFloat(result.getText().toString());
            phepTinh = "+";
            bieuThuc.setText(so1 + " + ");
            result.setText("");
        });
        // Phep tru
        btnTru.setOnClickListener(v->{
            so1 = Float.parseFloat(result.getText().toString());
            phepTinh = "-";
            bieuThuc.setText(so1 + " - ");
            result.setText("");
        });
        // Phep nhan
        btnNhan.setOnClickListener(v->{
            so1 = Float.parseFloat(result.getText().toString());
            phepTinh = "*";
            bieuThuc.setText(so1 + " x   ");
            result.setText("");
        });
        // Phep chia
        btnChia.setOnClickListener(v->{
            so1 = Float.parseFloat(result.getText().toString());
            phepTinh = "/";
            bieuThuc.setText(so1 + " / ");
            result.setText("");
        });
        // Nut bang
        btnBang.setOnClickListener(v ->{
           so2 = Float.parseFloat(result.getText().toString());
           float kq = 0;
           switch (phepTinh){
               case "+":
                   kq = so1 + so2;
                   result.setText(String.valueOf(kq));
                   break;
               case "-":
                   kq = so1 - so2;
                   result.setText(String.valueOf(kq));
                   break;
               case "*":
                   kq = so1 * so2;
                   result.setText(String.valueOf(kq));
                   break;
               case "/":
                   if(so2 != 0) {
                       kq = so1 / so2;
                       result.setText(String.valueOf(kq));
                   }
                   else{
                       result.setText("Error 0!");
                       break;
                   }
           }
           // Hiển thị biểu thức
            bieuThuc.setText(so1 + " " + phepTinh + " " + so2 + " = ");
        });
        // Xóa
        btnC.setOnClickListener(v->{
            result.setText("");
            bieuThuc.setText("");
            so1=0;
            so2=0;
        });
        btnDEL.setOnClickListener(v->{
            String str = result.getText().toString();
            result.setText(str.substring(0, str.length()-1));
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}