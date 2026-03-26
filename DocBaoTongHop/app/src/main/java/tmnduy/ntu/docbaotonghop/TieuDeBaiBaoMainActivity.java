package tmnduy.ntu.docbaotonghop;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TieuDeBaiBaoMainActivity extends AppCompatActivity {

    private RecyclerView rvDanhSachBaiBao;
    private ArrayBaiBaoAdapter adapter;
    private ArrayList<ItemBaiBao> danhSachBaiBao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tieu_de_bai_bao_main);

        // Xử lý padding cho hệ thống (status bar, navigation bar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ImageView imageView2 = findViewById(R.id.imageView2);
        imageView2.setImageResource(R.drawable.logo);

        // Khởi tạo RecyclerView
        rvDanhSachBaiBao = findViewById(R.id.lvDSBaiBao);

        // Lấy dữ liệu mẫu
        danhSachBaiBao = getDataMau();

        // Thiết lập LayoutManager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvDanhSachBaiBao.setLayoutManager(layoutManager);

        // Thiết lập Adapter
        adapter = new ArrayBaiBaoAdapter(this, danhSachBaiBao);
        rvDanhSachBaiBao.setAdapter(adapter);
    }

    // Hàm tạo dữ liệu mẫu (tạm thời)
    private ArrayList<ItemBaiBao> getDataMau() {
        ArrayList<ItemBaiBao> ds = new ArrayList<>();

        ds.add(new ItemBaiBao("Bài 1 - Giáo dục Việt Nam", R.drawable.r3, "26/02/2026"));
        ds.add(new ItemBaiBao("Bài 2 - Học sinh xuất sắc", R.drawable.r4, "26/03/2026"));
        ds.add(new ItemBaiBao("Bài 3 - Phương pháp dạy học mới", R.drawable.r5, "26/04/2026"));
        ds.add(new ItemBaiBao("Bài 4 - Đại học top đầu", R.drawable.r6, "26/05/2026"));
        ds.add(new ItemBaiBao("Bài 5 - Học bổng du học", R.drawable.r7, "26/06/2026"));

        return ds;
    }
}