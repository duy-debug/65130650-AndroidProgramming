package tmnduy.ntu.ontapthigk;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TrangChuFragment extends Fragment {

    public interface OnNavigationRequestListener {
        void onNavigateTo(int menuId);
    }

    private OnNavigationRequestListener navigationRequestListener;

    public TrangChuFragment() {
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnNavigationRequestListener) {
            navigationRequestListener = (OnNavigationRequestListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        navigationRequestListener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trang_chu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnAboutMe = view.findViewById(R.id.btnTrangChuAboutMe);
        Button btnBMI = view.findViewById(R.id.btnTrangChuBMI);
        Button btnDienTich = view.findViewById(R.id.btnTrangChuDienTich);
        Button btnRSS = view.findViewById(R.id.btnTrangChuRSS);
        Button btnThuVienAnh = view.findViewById(R.id.btnTrangChuThuVienAnh);

        btnAboutMe.setOnClickListener(v -> navigateTo(R.id.nav_cau1));
        btnBMI.setOnClickListener(v -> navigateTo(R.id.nav_cau2));
        btnDienTich.setOnClickListener(v -> navigateTo(R.id.nav_cau3));
        btnRSS.setOnClickListener(v -> navigateTo(R.id.nav_cau4));
        btnThuVienAnh.setOnClickListener(v -> navigateTo(R.id.nav_cau5));
    }

    private void navigateTo(int menuId) {
        if (navigationRequestListener != null) {
            navigationRequestListener.onNavigateTo(menuId);
        }
    }
}
