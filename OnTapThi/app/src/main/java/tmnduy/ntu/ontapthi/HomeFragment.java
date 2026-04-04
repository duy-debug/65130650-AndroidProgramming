package tmnduy.ntu.ontapthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

// HomeFragment - Trang chủ (Home Screen)
// Màn hình trung tâm hiển thị:
//   - Header với lời chào cá nhân hoá theo nickname đã lưu.
//   - Thanh tìm kiếm.
//   - Banner nổi bật dẫn vào màn hình Welcome.
//   - Hai card chức năng nhanh: Hồ sơ và Cài đặt.
//   - Danh sách sản phẩm nổi bật (RecyclerView).
// Luồng:
//   1. onCreateView() map view và gán listener cho 3 nút điều hướng.
//   2. bindData() đọc dữ liệu từ AppPreferences và đổ vào các TextView.
//   3. onResume() gọi lại bindData() để đồng bộ khi quay về từ tab khác.
public class HomeFragment extends Fragment {

    // Argument keys dùng khi khởi tạo fragment qua newInstance()
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Lớp trợ giúp đọc/ghi SharedPreferences
    private AppPreferences appPreferences;

    // Lời chào trong phần header, ví dụ: "Xin chào, Duy!"
    private TextView tvHeaderGreeting;

    // Dòng tóm tắt mức độ hoàn thành hồ sơ, hiển thị trong card Profile
    private TextView tvProfileProgress;

    // Dòng tóm tắt trạng thái cài đặt, hiển thị trong card Settings
    private TextView tvSettingsSummary;

    // TextView ẩn (visibility=gone), giữ để tương thích với bindData() cũ
    private TextView tvFocusSummary;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

    // Inflate layout, ánh xạ view và gán listener cho các nút điều hướng.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        appPreferences = new AppPreferences(requireContext());

        // Ánh xạ view
        tvHeaderGreeting  = view.findViewById(R.id.tvHomeGreeting);   // tiêu đề chào
        tvProfileProgress = view.findViewById(R.id.tvProfileProgress); // % hoàn thành hồ sơ
        tvSettingsSummary = view.findViewById(R.id.tvSettingsSummary); // tóm tắt cài đặt
        tvFocusSummary    = view.findViewById(R.id.tvFocusSummary);    // phút học (ẩn)

        // Nút điều hướng sang các tab khác
        Button btnGoProfile  = view.findViewById(R.id.btnGoProfile);
        Button btnGoSettings = view.findViewById(R.id.btnGoSettings);
        Button btnGoWelcome  = view.findViewById(R.id.btnGoWelcome);

        // navigateTo() gọi hàm của MainActivity để chuyển tab BottomNavigation
        btnGoProfile.setOnClickListener(v -> navigateTo(R.id.navigation_profile));
        btnGoSettings.setOnClickListener(v -> navigateTo(R.id.navigation_settings));
        btnGoWelcome.setOnClickListener(v -> navigateTo(R.id.navigation_welcome));

        bindData();
        return view;
    }

    // Gọi mỗi khi fragment hiển thị lại.
    // Đảm bảo dữ liệu luôn cập nhật sau khi người dùng chỉnh Profile/Settings.
    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    // Đọc thông tin từ AppPreferences và điền vào các TextView trên giao diện.
    // Bao gồm: lời chào, % hoàn thành hồ sơ, tóm tắt cài đặt, thời lượng học.
    private void bindData() {
        // Thoát sớm nếu view chưa được khởi tạo
        if (appPreferences == null || tvHeaderGreeting == null) return;

        // Nếu chưa có nickname thì dùng tên mặc định "bạn"
        String displayName = appPreferences.getNickname().isEmpty()
                ? getString(R.string.home_default_name)
                : appPreferences.getNickname();
        tvHeaderGreeting.setText(getString(R.string.home_greeting_format, displayName));

        // Hiển thị % hoàn thành hồ sơ (tính từ số trường đã điền)
        int completion = appPreferences.getProfileCompletionPercent();
        tvProfileProgress.setText(getString(R.string.home_profile_progress_format, completion));

        // Tóm tắt trạng thái thông báo và màn hình khởi động
        String notifStatus = appPreferences.isNotificationsEnabled()
                ? getString(R.string.status_on)
                : getString(R.string.status_off);
        String startScreen = appPreferences.shouldShowWelcomeOnLaunch()
                ? getString(R.string.tab_welcome)
                : getString(R.string.tab_home);
        tvSettingsSummary.setText(
                getString(R.string.home_settings_summary_format, notifStatus, startScreen));

        // Ghi thời lượng học vào view ẩn (dùng cho tích hợp sau)
        tvFocusSummary.setText(
                getString(R.string.home_focus_summary_format, appPreferences.getStudyMinutes()));
    }

    // Điều hướng sang tab khác thông qua MainActivity.
    // menuId là ID của item trong BottomNavigationView, ví dụ R.id.navigation_profile
    private void navigateTo(int menuId) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).navigateTo(menuId);
        }
    }
}
