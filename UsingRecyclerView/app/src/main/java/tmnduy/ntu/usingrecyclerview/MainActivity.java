package tmnduy.ntu.usingrecyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    LandScapeAdapter landScapeAdapter;
    ArrayList<LandScape> recylerViewData;
    RecyclerView recyclerViewLand;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // 3 chuẩn bị dữ liệu
        recylerViewData = getDataForRecyclerView();
        // 4 tìm điều khiển recycler
        recyclerViewLand = findViewById(R.id.recyclerLand);
        // 5 tạo layout manager để đặt bố cục cho recycler
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewLand.setLayoutManager(layoutManager);
//        RecyclerView.LayoutManager layoutLinearHorizonal = new
//        LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
//        recyclerViewLand.setLayoutManager(layoutLinearHorizonal);
//        RecyclerView.LayoutManager layoutGrid = new GridLayoutManager(this,2);
//        recyclerViewLand.setLayoutManager(layoutGrid);
        // 6 tạo adapter để đặt dữ liệu cho recycler
        landScapeAdapter = new LandScapeAdapter(this, recylerViewData);
        // 7 gắn adapter cho recycler
        recyclerViewLand.setAdapter(landScapeAdapter);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    ArrayList<LandScape> getDataForRecyclerView(){
        ArrayList<LandScape> dsDuLieu = new ArrayList<>();
        LandScape landScape1 = new LandScape("r2","Chung cư 2 phòng ngủ");
        dsDuLieu.add(landScape1);
        dsDuLieu.add(new LandScape("r3","Chung cư 3 phòng ngủ"));
        dsDuLieu.add(new LandScape("r4","Chung cư 1 phòng đơn gác"));
        dsDuLieu.add(new LandScape("r5","Chung cư 2 phòng đôi"));
        dsDuLieu.add(new LandScape("r6","Chung cư 2 phòng đơn"));
        dsDuLieu.add(new LandScape("r7","Chung cư 1 phòng đôi 2 phòng đơn"));
        return dsDuLieu;
    }
}