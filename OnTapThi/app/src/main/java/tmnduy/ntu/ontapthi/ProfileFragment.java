package tmnduy.ntu.ontapthi;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

// ProfileFragment - Màn hình Hồ sơ cá nhân (Profile Screen)
// Hiển thị thông tin người dùng và cho phép chỉnh sửa:
//   - Header: ảnh đại diện tròn, tên, email, nút "Chỉnh sửa hồ sơ".
//   - Card thông tin: số điện thoại, địa chỉ, ngày sinh (chỉ hiển thị).
//   - Card chỉnh sửa: form nhập họ tên, email, SĐT kèm nút Lưu / Xóa.
//   - Nút Đăng xuất ở cuối trang.
// Luồng:
//   1. onCreateView() map tất cả view (header + info card + form + buttons).
//   2. bindData() nạp dữ liệu từ SharedPreferences lên cả header lẫn form.
//   3. saveProfile() xác thực rồi lưu; cập nhật header ngay sau đó.
//   4. clearProfile() xóa toàn bộ dữ liệu đã lưu và reset giao diện.
//   5. onResume() làm mới dữ liệu khi quay lại tab Profile.
public class ProfileFragment extends Fragment {

    // Argument keys dùng khi khởi tạo fragment qua newInstance()
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    // Lớp trợ giúp đọc/ghi SharedPreferences
    private AppPreferences appPreferences;

    // Ô nhập họ và tên — bắt buộc phải điền (validated)
    private EditText edtFullName;

    // Ô nhập email — không bắt buộc, nhưng nếu điền phải đúng định dạng
    private EditText edtEmail;

    // Ô nhập số điện thoại — không bắt buộc, nhưng nếu điền phải ≥ 9 ký tự
    private EditText edtPhone;

    // Tên hiển thị trong header, cập nhật ngay sau khi lưu profile
    private TextView tvProfileName;

    // Email hiển thị trong header, cập nhật ngay sau khi lưu profile
    private TextView tvProfileEmail;

    // Hiển thị số điện thoại đã lưu; dùng "Chưa cập nhật" nếu rỗng
    private TextView tvInfoPhone;

    // Hiển thị địa chỉ — hiện tại luôn là "Chưa cập nhật"
    // vì chưa có key address trong AppPreferences
    private TextView tvInfoAddress;

    // Hiển thị ngày sinh — tương tự tvInfoAddress, chưa được lưu trữ
    private TextView tvInfoBirthdate;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

    // Inflate layout fragment_profile.xml, ánh xạ toàn bộ view và thiết lập listener.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        appPreferences = new AppPreferences(requireContext());

        // Ánh xạ header labels
        tvProfileName  = view.findViewById(R.id.tvProfileName);
        tvProfileEmail = view.findViewById(R.id.tvProfileEmail);

        // Ánh xạ info card labels (chỉ hiển thị, không chỉnh sửa trực tiếp)
        tvInfoPhone     = view.findViewById(R.id.tvInfoPhone);
        tvInfoAddress   = view.findViewById(R.id.tvInfoAddress);
        tvInfoBirthdate = view.findViewById(R.id.tvInfoBirthdate);

        // Ánh xạ form inputs
        edtFullName = view.findViewById(R.id.edtFullName);
        edtEmail    = view.findViewById(R.id.edtEmail);
        edtPhone    = view.findViewById(R.id.edtPhone);

        // Ánh xạ buttons
        Button btnEditProfile  = view.findViewById(R.id.btnEditProfile);  // header button
        Button btnSaveProfile  = view.findViewById(R.id.btnSaveProfile);  // lưu form
        Button btnClearProfile = view.findViewById(R.id.btnClearProfile); // xóa form
        Button btnLogout       = view.findViewById(R.id.btnLogoutProfile);// đăng xuất

        // Nút "Chỉnh sửa hồ sơ" ở header, hiện thông báo gợi ý cuộn xuống form
        btnEditProfile.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Chỉnh sửa thông tin bên dưới", Toast.LENGTH_SHORT).show());

        btnSaveProfile.setOnClickListener(v -> saveProfile());
        btnClearProfile.setOnClickListener(v -> clearProfile());

        // Đăng xuất, hiện Toast (tích hợp xác thực/điều hướng sau nếu cần)
        btnLogout.setOnClickListener(v ->
                Toast.makeText(requireContext(), "Đã đăng xuất", Toast.LENGTH_SHORT).show());

        bindData();
        return view;
    }

    // Đồng bộ giao diện khi người dùng quay lại tab Profile.
    // Đảm bảo hiển thị đúng dữ liệu mới nhất từ SharedPreferences.
    @Override
    public void onResume() {
        super.onResume();
        bindData();
    }

    // Đọc dữ liệu đã lưu và đổ lên toàn bộ giao diện:
    //   - Header: tên & email (dùng giá trị mặc định nếu rỗng)
    //   - Info card: SĐT, địa chỉ, ngày sinh (dùng "Chưa cập nhật" nếu rỗng)
    //   - Form: điền sẵn giá trị để người dùng chỉnh sửa tiếp
    private void bindData() {
        // Thoát sớm nếu view chưa sẵn sàng
        if (appPreferences == null || edtFullName == null) return;

        String savedName  = appPreferences.getFullName();
        String savedEmail = appPreferences.getEmail();
        String savedPhone = appPreferences.getPhone();
        String notSet     = getString(R.string.about_not_updated); // "Chưa cập nhật"

        // Cập nhật header
        tvProfileName.setText(savedName.isEmpty()
                ? getString(R.string.profile_default_user) : savedName);
        tvProfileEmail.setText(savedEmail.isEmpty()
                ? getString(R.string.profile_default_email) : savedEmail);

        // Cập nhật info card
        tvInfoPhone.setText(savedPhone.isEmpty() ? notSet : savedPhone);
        tvInfoAddress.setText(notSet);    // chưa có field address trong AppPreferences
        tvInfoBirthdate.setText(notSet);  // chưa có field birthdate trong AppPreferences

        // Điền sẵn form chỉnh sửa
        edtFullName.setText(savedName);
        edtEmail.setText(savedEmail);
        edtPhone.setText(savedPhone);
    }

    // Xác thực dữ liệu nhập rồi lưu vào SharedPreferences.
    // Quy tắc xác thực:
    //   - Họ tên: bắt buộc, không được để trống.
    //   - Email: không bắt buộc, nhưng nếu có phải đúng định dạng abc@xyz.com
    //   - Số điện thoại: không bắt buộc, nhưng nếu có phải ≥ 9 ký tự số.
    // Sau khi lưu thành công thì gọi bindData() để header cập nhật ngay.
    private void saveProfile() {
        String fullName = edtFullName.getText().toString().trim();
        String email    = edtEmail.getText().toString().trim();
        String phone    = edtPhone.getText().toString().trim();

        // Kiểm tra họ tên không trống (trường bắt buộc duy nhất)
        if (TextUtils.isEmpty(fullName)) {
            edtFullName.setError(getString(R.string.error_empty_full_name));
            edtFullName.requestFocus();
            return;
        }

        // Kiểm tra định dạng email nếu người dùng có nhập
        if (!TextUtils.isEmpty(email) && !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            edtEmail.setError(getString(R.string.error_invalid_email));
            edtEmail.requestFocus();
            return;
        }

        // Kiểm tra độ dài SĐT tối thiểu 9 ký tự
        if (!TextUtils.isEmpty(phone) && phone.length() < 9) {
            edtPhone.setError(getString(R.string.error_invalid_phone));
            edtPhone.requestFocus();
            return;
        }

        // Lưu vào SharedPreferences
        appPreferences.setFullName(fullName);
        appPreferences.setEmail(email);
        appPreferences.setPhone(phone);

        // Làm mới header và info card ngay sau khi lưu
        bindData();
        Toast.makeText(requireContext(), R.string.message_profile_saved, Toast.LENGTH_SHORT).show();
    }

    // Xóa toàn bộ thông tin hồ sơ đã lưu (đặt về chuỗi rỗng)
    // và làm mới giao diện về trạng thái mặc định.
    private void clearProfile() {
        appPreferences.setFullName("");
        appPreferences.setEmail("");
        appPreferences.setPhone("");
        bindData(); // reset cả header lẫn form về mặc định
        Toast.makeText(requireContext(), R.string.message_profile_cleared, Toast.LENGTH_SHORT).show();
    }
}
