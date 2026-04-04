package tmnduy.ntu.ontapthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.materialswitch.MaterialSwitch;
import com.google.android.material.slider.Slider;

// SettingsFragment - Màn hình Cài đặt (Settings Screen)
// Cho phép người dùng tuỳ chỉnh các cài đặt của ứng dụng:
//   - Bật/tắt thông báo học tập.
//   - Bật/tắt chế độ tối (Dark Mode).
//   - Chọn màn hình khởi động (Welcome hoặc Home).
//   - Điều chỉnh thời lượng học đề xuất (15 đến 120 phút).
//   - Các mục điều hướng: Ngôn ngữ, Bảo mật, Đổi mật khẩu, Trợ giúp.
//   - Nút Đăng xuất.
// Luồng:
//   1. onCreateView() map tất cả view và gán listener.
//   2. bindData() nạp giá trị đang lưu vào từng switch/slider.
//   3. Khi nhấn "Lưu Settings" thì saveSettings() lưu và áp dụng theme ngay.
//   4. onResume() đồng bộ lại dữ liệu nếu có thay đổi từ nơi khác.
public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Lớp trợ giúp đọc/ghi SharedPreferences
    private AppPreferences appPreferences;

    // Switch bật/tắt thông báo nhắc nhở học tập
    private MaterialSwitch switchNotifications;

    // Switch bật/tắt chế độ tối toàn ứng dụng
    private MaterialSwitch switchDarkMode;

    // Switch chọn màn hình mở app: true = Welcome, false = Home
    private MaterialSwitch switchWelcome;

    // Slider cho phép chọn từ 15 đến 120 phút, bước nhảy 15 phút
    private Slider sliderStudyMinutes;

    // Label hiển thị giá trị slider hiện tại, ví dụ "45 phút"
    private TextView tvStudyMinutesValue;

    public SettingsFragment() {
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

    // Inflate layout, ánh xạ tất cả view và thiết lập listener.
    // Mọi logic UI đều được khởi tạo trong phương thức này.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        appPreferences = new AppPreferences(requireContext());

        // Ánh xạ Switches
        switchNotifications = view.findViewById(R.id.switchNotifications);
        switchDarkMode      = view.findViewById(R.id.switchDarkMode);
        switchWelcome       = view.findViewById(R.id.switchWelcomeScreen);

        // Ánh xạ Slider và nhãn giá trị
        sliderStudyMinutes  = view.findViewById(R.id.sliderStudyMinutes);
        tvStudyMinutesValue = view.findViewById(R.id.tvStudyMinutesValue);

        // Ánh xạ Buttons
        Button btnSaveSettings = view.findViewById(R.id.btnSaveSettings);
        Button btnLogout       = view.findViewById(R.id.btnLogoutSettings);

        // Ánh xạ các row điều hướng
        View rowLanguage       = view.findViewById(R.id.rowLanguage);
        View rowSecurity       = view.findViewById(R.id.rowSecurity);
        View rowChangePassword = view.findViewById(R.id.rowChangePassword);
        View rowHelp           = view.findViewById(R.id.rowHelp);

        // Khi người dùng kéo slider thì cập nhật nhãn giá trị ngay lập tức
        sliderStudyMinutes.addOnChangeListener((slider, value, fromUser) ->
                tvStudyMinutesValue.setText(
                        getString(R.string.study_minutes_value_format, (int) value)));

        // Lưu tất cả cài đặt khi nhấn nút "Lưu Settings"
        btnSaveSettings.setOnClickListener(v -> saveSettings());

        // Đăng xuất, hiện Toast (mở rộng logic xác thực sau nếu cần)
        btnLogout.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show());

        // Các row chỉ hiển thị phản hồi Toast, là placeholder cho tính năng tương lai
        rowLanguage.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Ngôn ngữ", Toast.LENGTH_SHORT).show());

        rowSecurity.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Bảo mật", Toast.LENGTH_SHORT).show());

        rowChangePassword.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Đổi mật khẩu", Toast.LENGTH_SHORT).show());

        rowHelp.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Trợ giúp", Toast.LENGTH_SHORT).show());

        bindData();
        return view;
    }

    // Đồng bộ dữ liệu mỗi khi fragment hiển thị lại.
    // Đảm bảo các switch/slider phản ánh đúng giá trị đang lưu.
    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    // Nạp giá trị đã lưu trong SharedPreferences vào từng switch và slider.
    // Được gọi khi fragment mới tạo và khi quay lại từ tab khác.
    private void bindData() {
        // Thoát sớm nếu view chưa được khởi tạo
        if (appPreferences == null || switchNotifications == null) return;

        // Đặt trạng thái switch theo giá trị đã lưu
        switchNotifications.setChecked(appPreferences.isNotificationsEnabled());
        switchDarkMode.setChecked(appPreferences.isDarkModeEnabled());
        switchWelcome.setChecked(appPreferences.shouldShowWelcomeOnLaunch());

        // Đặt giá trị slider và cập nhật nhãn tương ứng
        sliderStudyMinutes.setValue(appPreferences.getStudyMinutes());
        tvStudyMinutesValue.setText(
                getString(R.string.study_minutes_value_format, appPreferences.getStudyMinutes()));
    }

    // Lưu tất cả cài đặt hiện tại vào SharedPreferences,
    // sau đó áp dụng theme sáng/tối ngay lập tức qua MainActivity.
    // Thứ tự: đọc giá trị -> ghi vào AppPreferences -> đổi theme -> hiện Toast.
    private void saveSettings() {
        boolean darkModeEnabled = switchDarkMode.isChecked();

        appPreferences.setNotificationsEnabled(switchNotifications.isChecked());
        appPreferences.setDarkModeEnabled(darkModeEnabled);
        appPreferences.setShowWelcomeOnLaunch(switchWelcome.isChecked());
        appPreferences.setStudyMinutes((int) sliderStudyMinutes.getValue());

        // Áp dụng theme sáng/tối ngay mà không cần restart app
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).applyTheme(darkModeEnabled);
        }

        Toast.makeText(requireContext(), R.string.message_settings_saved, Toast.LENGTH_SHORT).show();
    }
}
