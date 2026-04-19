package tmnduy.ntu.ontapthigk;

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
 * Use the {@link Cau3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cau3Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Cau3Fragment() {
        // Required empty public constructor
    }

    public static Cau3Fragment newInstance(String param1, String param2) {
        Cau3Fragment fragment = new Cau3Fragment();
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
        return inflater.inflate(R.layout.fragment_cau3, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edtGiaTri1 = view.findViewById(R.id.edtGiaTri1);
        EditText edtGiaTri2 = view.findViewById(R.id.edtGiaTri2);
        Button btnTinh = view.findViewById(R.id.btnTinh);
        TextView tvKetQua = view.findViewById(R.id.tvKetQua);
        TextView tvCongThuc = view.findViewById(R.id.tvCongThuc);
        TextView tvLabel1 = view.findViewById(R.id.tvLabel1);
        TextView tvLabel2 = view.findViewById(R.id.tvLabel2);

        btnTinh.setOnClickListener(v -> {
            String raw1 = edtGiaTri1.getText() == null ? "" : edtGiaTri1.getText().toString().trim();
            String raw2 = edtGiaTri2.getText() == null ? "" : edtGiaTri2.getText().toString().trim();

            boolean hasError = false;
            if (TextUtils.isEmpty(raw1)) {
                edtGiaTri1.setError("Vui lòng nhập chiều dài");
                hasError = true;
            }
            if (TextUtils.isEmpty(raw2)) {
                edtGiaTri2.setError("Vui lòng nhập chiều rộng");
                hasError = true;
            }
            if (hasError) {
                Toast.makeText(requireContext(), "Vui lòng nhập đủ dữ liệu", Toast.LENGTH_SHORT).show();
                return;
            }

            double dai;
            double rong;
            try {
                dai = Double.parseDouble(raw1);
                rong = Double.parseDouble(raw2);
            } catch (NumberFormatException e) {
                Toast.makeText(requireContext(), "Dữ liệu không hợp lệ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (dai <= 0) {
                edtGiaTri1.setError("Chiều dài phải > 0");
                return;
            }
            if (rong <= 0) {
                edtGiaTri2.setError("Chiều rộng phải > 0");
                return;
            }

            double dienTich = dai * rong;
            tvKetQua.setText(formatNumber(dienTich));
            tvCongThuc.setText(String.format(Locale.getDefault(), "S = %s × %s", formatNumber(dai), formatNumber(rong)));
        });
    }

    private static String formatNumber(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return "--";
        }
        long asLong = (long) value;
        if (value == asLong) {
            return String.valueOf(asLong);
        }
        String formatted = String.format(Locale.getDefault(), "%.2f", value);
        formatted = formatted.replaceAll("0+$", "").replaceAll("\\.$", "");
        return formatted;
    }
}
