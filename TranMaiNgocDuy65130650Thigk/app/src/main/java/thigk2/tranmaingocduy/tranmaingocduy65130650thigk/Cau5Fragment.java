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
        ArrayList<CongTrinh.ItemCongTrinh> dsCongTrinh = new ArrayList<>();
        dsCongTrinh.add(new CongTrinh.ItemCongTrinh("Công trình nghiên cứu về cảnh biển và môi trường sinh thái ven bờ", "anh1", "Ngày đăng: 01/04/2025"));
        dsCongTrinh.add(new CongTrinh.ItemCongTrinh("Công trình khảo sát thiên nhiên và hệ sinh thái tại khu vực rừng nhiệt đới", "anh2", "Ngày đăng: 02/04/2025"));
        dsCongTrinh.add(new CongTrinh.ItemCongTrinh("Công trình phân tích phong cảnh tự nhiên và giá trị bảo tồn cảnh quan", "anh3", "Ngày đăng: 03/04/2025"));
        dsCongTrinh.add(new CongTrinh.ItemCongTrinh("Công trình tìm hiểu tiềm năng phát triển du lịch sinh thái bền vững", "anh4", "Ngày đăng: 04/04/2025"));
        dsCongTrinh.add(new CongTrinh.ItemCongTrinh("Công trình minh họa ứng dụng hình ảnh trong giới thiệu không gian tự nhiên", "anh5", "Ngày đăng: 05/04/2025"));

        rvDanhSachAnh.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Gan adapter de RecyclerView hien thi du lieu cong trinh
        CongTrinhAdapter adapter = new CongTrinhAdapter(
                requireContext(),
                dsCongTrinh,
                new CongTrinhAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(CongTrinh.ItemCongTrinh congTrinh) {
                        Toast.makeText(requireContext(), "Bạn vừa chọn: " + congTrinh.getTieuDe(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        rvDanhSachAnh.setAdapter(adapter);
    }
}
