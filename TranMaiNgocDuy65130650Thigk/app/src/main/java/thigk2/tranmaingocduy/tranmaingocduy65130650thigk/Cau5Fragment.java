package thigk2.tranmaingocduy.tranmaingocduy65130650thigk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cau5Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Cau5Fragment() {
    }

    public static Cau5Fragment newInstance(String param1, String param2) {
        Cau5Fragment fragment = new Cau5Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cau5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvDanhSachAnh = view.findViewById(R.id.rvDanhSachBaiBao);

        // Tao danh sach anh co san trong drawable de hien thi len RecyclerView
        ArrayList<DiaDiemDuLich.ItemCongTrinh> dsCongTrinh = new ArrayList<>();
        dsCongTrinh.add(new DiaDiemDuLich.ItemCongTrinh("Địa điểm du lịch Tháp Bà Ponagar tại Nha Trang", "anh1", "Địa chỉ: 61 Hai Tháng Tư, Vĩnh Phước, Nha Trang, Khánh Hòa"));
        dsCongTrinh.add(new DiaDiemDuLich.ItemCongTrinh("Địa điểm du lịch Hòn Chồng Nha Trang", "anh2", "Địa chỉ: Phạm Văn Đồng, Vĩnh Phước, Nha Trang, Khánh Hòa"));
        dsCongTrinh.add(new DiaDiemDuLich.ItemCongTrinh("Địa điểm du lịch VinWonders trên đảo Hòn Tre Nha Trang", "anh3", "Địa chỉ: Đảo Hòn Tre, Vĩnh Nguyên, Nha Trang, Khánh Hòa"));
        dsCongTrinh.add(new DiaDiemDuLich.ItemCongTrinh("Địa điểm du lịch Bãi Dài nổi tiếng ở Nha Trang", "anh4", "Địa chỉ: Xã Cam Hải Đông, huyện Cam Lâm, Khánh Hòa"));
        dsCongTrinh.add(new DiaDiemDuLich.ItemCongTrinh("Địa điểm du lịch đảo Bình Ba", "anh5", "Địa chỉ: Xã Cam Bình, thành phố Cam Ranh, Khánh Hòa"));

        rvDanhSachAnh.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Gan adapter de RecyclerView hien thi du lieu cong trinh
        DiaDiemDuLichAdapter adapter = new DiaDiemDuLichAdapter(
                requireContext(),
                dsCongTrinh,
                new DiaDiemDuLichAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DiaDiemDuLich.ItemCongTrinh congTrinh) {
                        Toast.makeText(requireContext(), "Bạn vừa chọn: " + congTrinh.getTieuDe(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        rvDanhSachAnh.setAdapter(adapter);
    }
}
