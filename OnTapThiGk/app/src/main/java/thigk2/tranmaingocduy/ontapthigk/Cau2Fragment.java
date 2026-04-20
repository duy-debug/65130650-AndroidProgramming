package thigk2.tranmaingocduy.ontapthigk;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cau2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cau2Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Cau2Fragment() {
        // Required empty public constructor
    }

    public static Cau2Fragment newInstance(String param1, String param2) {
        Cau2Fragment fragment = new Cau2Fragment();
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
        return inflater.inflate(R.layout.fragment_cau2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edtChieuCao = view.findViewById(R.id.edtChieuCao);
        EditText edtCanNang = view.findViewById(R.id.edtCanNang);
        Button btnTinhBMI = view.findViewById(R.id.btnTinhBMI);
        TextView tvBMI = view.findViewById(R.id.tvBMI);
        TextView tvPhanLoai = view.findViewById(R.id.tvPhanLoai);
        TextView tvKetQua = view.findViewById(R.id.tvKetQua);

        btnTinhBMI.setOnClickListener(v -> {
            String rawChieuCao = edtChieuCao.getText() == null ? "" : edtChieuCao.getText().toString().trim();
            String rawCanNang = edtCanNang.getText() == null ? "" : edtCanNang.getText().toString().trim();

            boolean hasError = false;
            if (TextUtils.isEmpty(rawChieuCao)) {
                edtChieuCao.setError("Vui lòng nhập chiều cao");
                hasError = true;
            }
            if (TextUtils.isEmpty(rawCanNang)) {
                edtCanNang.setError("Vui lòng nhập cân nặng");
                hasError = true;
            }
            if (hasError) {
                Toast.makeText(requireContext(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                return;
            }

            double chieuCaoCm;
            double canNangKg;
            try {
                chieuCaoCm = Double.parseDouble(rawChieuCao);
                canNangKg = Double.parseDouble(rawCanNang);
            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (chieuCaoCm <= 0) {
                edtChieuCao.setError("Chiều cao phải > 0");
                return;
            }
            if (canNangKg <= 0) {
                edtCanNang.setError("Cân nặng phải > 0");
                return;
            }

            double chieuCaoM = chieuCaoCm / 100.0;
            double bmi = canNangKg / (chieuCaoM * chieuCaoM);

            tvKetQua.setText("Chỉ số BMI của bạn:");
        tvBMI.setText(formatNumber(bmi, 1));

        String phanLoai;
            if (bmi < 18.5) {
                phanLoai = "Thiếu cân";
            } else if (bmi < 25.0) {
                phanLoai = "Bình thường";
            } else if (bmi < 30.0) {
                phanLoai = "Thừa cân";
            } else {
                phanLoai = "Béo phì";
            }
            tvPhanLoai.setText(phanLoai);
        });
    }

    private static String formatNumber(double value, int maxDecimals) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "--";
        }
        long asLong = (long) value;
        if (value == asLong) {
            return String.valueOf(asLong);
        }
        String pattern = "%." + Math.max(0, maxDecimals) + "f";
        String formatted = String.format(Locale.getDefault(), pattern, value);
        formatted = formatted.replaceAll("0+$", "").replaceAll("\\.$", "");
        return formatted;
    }
}
