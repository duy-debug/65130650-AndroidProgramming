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

        // Khởi tạo RecyclerView
        rvDanhSachBaiBao = findViewById(R.id.lvDSBaiBao);

        danhSachBaiBao = new ArrayList<>();
        adapter = new ArrayBaiBaoAdapter(this, danhSachBaiBao);
        rvDanhSachBaiBao.setLayoutManager(new LinearLayoutManager(this));
        rvDanhSachBaiBao.setAdapter(adapter);
        // Gọi trong Thread riêng vì Android cấm đọc mạng trên Main Thread
        new Thread(() -> {
            ArrayList<ItemBaiBao> dsFromRSS = GetDataFromRSS.layDuLieuRSS(
                    "https://vnexpress.net/rss/giao-duc.rss");
            // Cập nhật UI phải quay về Main Thread
            runOnUiThread(() -> {
                danhSachBaiBao.addAll(dsFromRSS);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
}