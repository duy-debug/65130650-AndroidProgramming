package tmnduy.ntu.ontapthi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

// AboutFragment - Màn hình Giới thiệu (About Screen)
// Hiển thị thông tin tổng quan về ứng dụng và dữ liệu người dùng:
//   - Logo, tên app, số phiên bản.
//   - Mô tả mục đích ứng dụng.
//   - Thông tin project (công nghệ sử dụng).
//   - Thông tin nhà phát triển (email, website, mạng xã hội).
//   - Dữ liệu hiện tại: nickname, thời lượng học, họ tên, email, SĐT.
// Lưu ý: Phần lớn nội dung là tĩnh trong XML.
// Chỉ có 2 TextView cuối là dynamic kéo từ SharedPreferences.
public class AboutFragment extends Fragment {

    // Argument keys dùng khi khởi tạo fragment qua newInstance()
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Lớp trợ giúp đọc/ghi dữ liệu người dùng qua SharedPreferences
    private AppPreferences appPreferences;

    // Hiển thị lời tóm tắt tuỳ chỉnh: nickname + thời lượng học
    // Ví dụ: "Bạn đang học với tên gọi Duy và 45 phút/buổi"
    private TextView tvAboutSummary;

    // Hiển thị thông tin hồ sơ đã lưu: họ tên, email, số điện thoại
    // Nội dung thay đổi theo những gì đã điền ở trang Profile
    private TextView tvAboutProfile;

    public AboutFragment() {
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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

    // Inflate layout fragment_about.xml, ánh xạ 2 TextView động rồi gọi bindData().
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        appPreferences = new AppPreferences(requireContext());

        // Chỉ cần map 2 view có nội dung động; phần còn lại tĩnh trong XML
        tvAboutSummary = view.findViewById(R.id.tvAboutSummary);
        tvAboutProfile = view.findViewById(R.id.tvAboutProfile);

        bindData();
        return view;
    }

    // Làm mới dữ liệu mỗi khi fragment được hiển thị lại.
    // Hữu ích khi người dùng vừa lưu Profile rồi chuyển sang tab About.
    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    // Đọc dữ liệu từ SharedPreferences và cập nhật 2 TextView:
    //   tvAboutSummary — tóm tắt nickname + thời lượng học
    //   tvAboutProfile — họ tên, email, SĐT đã lưu
    // Nếu một trường chưa điền thì hiển thị "Chưa cập nhật"
    private void bindData() {
        // Thoát sớm nếu view chưa được khởi tạo
        if (tvAboutSummary == null || appPreferences == null) return;

        // Nếu chưa đặt nickname thì dùng nhãn "Khách"
        String nickname = appPreferences.getNickname().isEmpty()
                ? getString(R.string.about_guest_user)
                : appPreferences.getNickname();

        tvAboutSummary.setText(getString(
                R.string.about_summary_format,
                nickname,
                appPreferences.getStudyMinutes())); // thời lượng học tính bằng phút

        // Với mỗi trường: nếu rỗng thì thay bằng "Chưa cập nhật"
        String notSet   = getString(R.string.about_not_updated);
        String fullName = appPreferences.getFullName().isEmpty() ? notSet : appPreferences.getFullName();
        String email    = appPreferences.getEmail().isEmpty()    ? notSet : appPreferences.getEmail();
        String phone    = appPreferences.getPhone().isEmpty()    ? notSet : appPreferences.getPhone();

        tvAboutProfile.setText(getString(
                R.string.about_profile_format, fullName, email, phone));
    }
}
