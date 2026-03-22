package tmnduy.ntu.retrofitandroidexample;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Job job = new Job(1, "Developer");
        ArrayList<Favorite> favoritesList = new ArrayList<>();
        favoritesList.add(new Favorite(1, "Facebook"));
        favoritesList.add(new Favorite(2, "Đọc sách"));
        favoritesList.add(new Favorite(3, "Nghe nhạc"));
        User user = new User(1, "Trần Mai Ngọc Duy", true, job, favoritesList);
        Gson gson = new Gson(); // Dùng thư viện Gson để chuyển đổi Object sang JSON
        String strJson = gson.toJson(user);
        Log.e("String Json", strJson); // Tìm và click vào tab có tên là Logcat
        // chữ "e" viết tắt của Error - Lỗi
        // nên chuỗi JSON sẽ được in ra với dòng chữ màu đỏ
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}