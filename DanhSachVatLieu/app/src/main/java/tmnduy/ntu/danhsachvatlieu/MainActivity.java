package tmnduy.ntu.danhsachvatlieu;

import android.os.Bundle;
import android.util.Log;
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

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView myListView;
    public void timDieuKhien(){
        myListView = findViewById(R.id.myListView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        timDieuKhien();
        ArrayList<String> vatLieuList = new ArrayList<>();
        vatLieuList.add("Xi măng");
        vatLieuList.add("Gạch");
        vatLieuList.add("Đá ốp lát");
        vatLieuList.add("Ống nhựa");
        vatLieuList.add("Sơn chống thấm");
        // Tạo adapter
        ArrayAdapter<String> adapterVatLieu = new ArrayAdapter<String>(
                this,R.layout.item_custom,R.id.tvItemName,vatLieuList);
        myListView.setAdapter(adapterVatLieu);
        // Tạo bộ lắng nghe và xử lý sự kiện OnItemClick, đặt vào một biến
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String tenVatLieu = vatLieuList.get(position);

                // 1. Toast.makeText(MainActivity.this, "Bạn vừa chọn: " + tenVatLieu, Toast.LENGTH_SHORT).show();

                // 2. Dòng Log giữ nguyên để kiểm tra cho chắc
                // Log.e("KIEM_TRA_CLICK", "Đã click vào: " + tenVatLieu);

                // 3. THÊM SNACKBAR VÀO ĐÂY:
                // Lưu ý: Snackbar cần một cái 'view' để biết nó sẽ hiển thị đè lên màn hình nào.
                // Chữ 'view' ở đây chính là biến View được truyền vào ở hàm onItemClick ở trên.
                Snackbar.make(view, "Bạn vừa chọn: " + tenVatLieu, Snackbar.LENGTH_LONG)
                        .setAction("ĐÓNG", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // Code xử lý khi người dùng bấm vào chữ ĐÓNG trên thông báo
                                // Log.e("KIEM_TRA_CLICK", "Người dùng đã tự tay đóng thông báo!");
                            }
                        }).show();
            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}