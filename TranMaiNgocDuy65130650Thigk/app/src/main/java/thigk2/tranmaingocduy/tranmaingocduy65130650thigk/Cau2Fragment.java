package thigk2.tranmaingocduy.tranmaingocduy65130650thigk;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cau2Fragment extends Fragment {

    public Cau2Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cau2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvDanhSach = view.findViewById(R.id.rvDanhSachTinhThanh);
        rvDanhSach.setLayoutManager(new LinearLayoutManager(requireContext()));

        ArrayList<TinhThanhItem> dsTinhThanh = new ArrayList<>();
        dsTinhThanh.add(new TinhThanhItem("Hà Nội"));
        dsTinhThanh.add(new TinhThanhItem("TP Hồ Chí Minh"));
        dsTinhThanh.add(new TinhThanhItem("Đà Nẵng"));
        dsTinhThanh.add(new TinhThanhItem("Khánh Hòa"));
        dsTinhThanh.add(new TinhThanhItem("Cần Thơ"));
        dsTinhThanh.add(new TinhThanhItem("Hải Phòng"));
        dsTinhThanh.add(new TinhThanhItem("Huế"));
        dsTinhThanh.add(new TinhThanhItem("Lâm Đồng"));
        dsTinhThanh.add(new TinhThanhItem("Nghệ An"));
        dsTinhThanh.add(new TinhThanhItem("Trần Mai Ngoc Duy"));

        rvDanhSach.setAdapter(new TinhThanhAdapter(requireContext(), dsTinhThanh));
    }
}
