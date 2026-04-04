package tmnduy.ntu.ontapthi;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

// WelcomeFragment - Màn hình chào mừng
// Hiển thị khi người dùng mở app lần đầu hoặc chọn tab Welcome.
// Cho phép người dùng nhập nickname để cá nhân hoá lời chào.
// Luồng:
//   1. Đọc nickname đã lưu từ AppPreferences khi fragment tạo.
//   2. Hiển thị lời chào preview phía trên nút "Bắt đầu".
//   3. Cả 2 nút "Bắt đầu" và "Đăng nhập" đều chuyển sang Home.
//   4. Nickname lưu liên tục qua SharedPreferences.
public class WelcomeFragment extends Fragment {

    // Argument keys dùng khi khởi tạo fragment qua newInstance()
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Lớp lưu / đọc dữ liệu người dùng bằng SharedPreferences
    private AppPreferences appPreferences;

    // Ô nhập liệu ẩn (visibility=gone) — giữ để logic lưu nickname hoạt động
    private EditText edtNickname;

    // TextView hiển thị lời chào preview phía dưới phần mô tả
    private TextView tvPreview;

    // Constructor mặc định bắt buộc với Fragment
    public WelcomeFragment() {
    }

    // Factory method — tạo instance với tham số truyền vào.
    // Dùng Bundle thay vì constructor có tham số để Android
    // có thể tái tạo fragment sau khi ứng dụng bị kill.
    public static WelcomeFragment newInstance(String param1, String param2) {
        WelcomeFragment fragment = new WelcomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Khôi phục tham số nếu fragment được tạo qua newInstance()
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    // Inflate layout XML và ánh xạ (map) các view.
    // Tất cả logic UI được thiết lập tại đây.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate file fragment_welcome.xml thành đối tượng View
        View view = inflater.inflate(R.layout.fragment_welcome, container, false);

        appPreferences = new AppPreferences(requireContext());

        // Ánh xạ các view từ XML theo id đã khai báo trong layout
        edtNickname = view.findViewById(R.id.edtNickname);      // ô nhập nickname (ẩn)
        tvPreview   = view.findViewById(R.id.tvWelcomePreview); // lời chào preview

        Button btnSave     = view.findViewById(R.id.btnSaveNickname); // nút lưu (ẩn)
        Button btnOpenHome = view.findViewById(R.id.btnOpenHome);     // nút "Bắt đầu"
        Button btnLogin    = view.findViewById(R.id.btnLogin);        // nút "Đăng nhập"

        // Nạp nickname đã lưu vào ô nhập và cập nhật preview
        edtNickname.setText(appPreferences.getNickname());
        bindPreview();

        // Gán sự kiện click cho từng nút
        btnSave.setOnClickListener(v -> saveNickname());

        // Cả hai nút đều điều hướng sang Home
        btnOpenHome.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateTo(R.id.nav_home);
            }
        });

        btnLogin.setOnClickListener(v -> {
            if (getActivity() instanceof MainActivity) {
                ((MainActivity) getActivity()).navigateTo(R.id.nav_home);
            }
        });

        return view;
    }

    // Gọi mỗi khi fragment quay lại màn hình.
    // Dùng để đồng bộ nickname mới nhất từ SharedPreferences.
    @Override
    public void onResume() {
        super.onResume();
        if (edtNickname != null) {
            edtNickname.setText(appPreferences.getNickname());
            bindPreview();
        }
    }

    // Xác thực và lưu nickname vào SharedPreferences.
    // Hiển thị lỗi nếu ô nhập rỗng.
    private void saveNickname() {
        String nickname = edtNickname.getText().toString().trim();

        // Kiểm tra không để trống
        if (TextUtils.isEmpty(nickname)) {
            edtNickname.setError(getString(R.string.error_empty_nickname));
            edtNickname.requestFocus();
            return;
        }

        appPreferences.setNickname(nickname);
        bindPreview(); // cập nhật preview ngay lập tức
        Toast.makeText(requireContext(), R.string.message_welcome_saved, Toast.LENGTH_SHORT).show();
    }

    // Cập nhật nội dung tvWelcomePreview dựa trên nickname hiện tại.
    // Nếu nickname rỗng thì hiển thị hướng dẫn mặc định.
    // Nếu có nickname thì hiển thị lời chào cá nhân hoá.
    private void bindPreview() {
        String nickname = appPreferences.getNickname();
        if (nickname.isEmpty()) {
            tvPreview.setText(R.string.welcome_preview_default);
        } else {
            tvPreview.setText(getString(R.string.welcome_preview_format, nickname));
        }
    }
}
