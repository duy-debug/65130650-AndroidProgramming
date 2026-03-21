package tmnduy.ntu.danhsachtinhthanh;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        // Hiển thị list view
        // B1: Cần có dữ liệu để hiển thị
        // từ cơ sở dữ liệu(SQL, MySQL, XML,...)
        // Ở bài này hard-code dữ liệu trực tiếp
        // Cần biến phù hợp để chứa dữ liệu
        ArrayList<String> dsTenTinhThanh = new ArrayList<String>(); // tạo thể hiện cụ thể
        // Thêm dữ liệu ở đây
        dsTenTinhThanh.add("Hà Nội");
        dsTenTinhThanh.add("Hồ Chí Minh");
        dsTenTinhThanh.add("Đà Nẵng");
        dsTenTinhThanh.add("Cần Thơ");
        dsTenTinhThanh.add("An Giang");
        dsTenTinhThanh.add("Bà Rịa - Vũng Tàu");
        dsTenTinhThanh.add("Bạc Liêu");
        dsTenTinhThanh.add("Bắc Kạn");
        dsTenTinhThanh.add("Bắc Giang");
        dsTenTinhThanh.add("Bắc Ninh");
        dsTenTinhThanh.add("Bến Tre");
        dsTenTinhThanh.add("Bình Dương");
        dsTenTinhThanh.add("Bình Định");
        dsTenTinhThanh.add("Bình Phước");
        dsTenTinhThanh.add("Bình Thuận");
        dsTenTinhThanh.add("Cà Mau");
        dsTenTinhThanh.add("Cao Bằng");

        // B2: Tạo Adapter
        ArrayAdapter<String> adapterTinhThanh = new ArrayAdapter<String>(
                this, android.R.layout.simple_list_item_1, dsTenTinhThanh);
        // B3: Gắn vào điều khiển hiện thị ListView
        // 3.1 Tìm điều khiển hiện thị ListView
        ListView lvTenTinhThanh = findViewById(R.id.lvDanhSachTT);
        // 3.2 Gắn Adapter vào ListView
        lvTenTinhThanh.setAdapter(adapterTinhThanh);
        // 3.3 Lắng nghe và xử lý sự kiện user tương tác để sau
        // Tạo bộ lắng nghe và xử lý sự kiện OnItemClick, đặt vào một biến
        lvTenTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            // Thay đổi trong MainActivity.java tại phần setOnItemClickListenerlvTenTinhThanh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Lấy tên tỉnh thành tại vị trí được click
                String tenTinhThanh = dsTenTinhThanh.get(position);
                // Hiển thị thông báo
                Toast.makeText(MainActivity.this,
                        "Bạn vừa chọn: " + tenTinhThanh,
                        Toast.LENGTH_SHORT).show();
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (
                v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}