# Lập trình thiết bị di động - 65130650

### Install:
---
*   **Android Studio** (Hedgehog or newer recommended)
*   **Android 7.0 (Nougat API 24)** or higher
*   **Java SE Development Kit (JDK 11)**

*Mô tả*
## Đây là kho lưu trữ bài tập khi tôi làm bài tập lớn và nhỏ, đây là lưu trữ tham khảo, không phải dự án lâu dài hoặc có thể sử dụng.

---
*Quá trình thực hiện bài tập*
### Mô tả
---
Đây là bài thi giữa kì của mình trên Android, xây dựng theo mô hình `BottomNavigationView` kết hợp `Fragment`. Ứng dụng gồm một màn hình trang chủ và các chức năng con:
* Giới thiệu thông tin cá nhân
* Danh sách tỉnh thành
* Tính diện tích hình chữ nhật
* Đọc tin tức RSS từ VnExpress
* Danh sách địa điểm du lịch

---
## Quá trình thực hiện bài tập

### Trang chủ
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/MainActivity.java)

<img src="./Images/anhthigk_2.png" width="220" alt="Trang chủ">

*Màn hình trang chủ đóng vai trò điều hướng nhanh đến các chức năng chính của ứng dụng.*

---

### Câu 4: Giới thiệu bản thân
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/Cau1Fragment.java)

<img src="./Images/anhthigk_1.png" width="220" alt="Giới thiệu bản thân">

*Hiển thị hồ sơ cá nhân, thông tin liên hệ và liên kết GitHub của sinh viên.*

---

### Câu 2: Danh sách tỉnh thành
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/Cau2Fragment.java)

<img src="./Images/anhthigk_6.png" width="220" alt="Danh sách tỉnh thành">

*Sử dụng `RecyclerView` để hiển thị danh sách các tỉnh thành Việt Nam theo dạng thẻ.*

---

### Câu 1: Tính diện tích và chu vi
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/Cau3Fragment.java)

<img src="./Images/anhthigk_3.png" width="220" alt="Tính diện tích và chu vi">

*Nhập chiều dài và chiều rộng để tính diện tích hình chữ nhật, đồng thời hiển thị công thức tính.*

---

### Câu 5: Địa điểm du lịch
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/Cau5Fragment.java)

<img src="./Images/anhthigk_4.png" width="220" alt="Địa điểm du lịch">

*Danh sách các địa điểm du lịch tại Nha Trang được hiển thị bằng `RecyclerView` kèm hình ảnh và địa chỉ.*

---

### Câu 3: Tin tức RSS
[Chi tiết bài tập](./app/src/main/java/thigk2/tranmaingocduy/tranmaingocduy65130650thigk/Cau4Fragment.java)

<img src="./Images/anhthigk_5.png" width="220" alt="Tin tức RSS">

*Ứng dụng đọc RSS từ VnExpress, hiển thị danh sách tin tức và cho phép mở bài báo chi tiết khi chọn một mục.*

---

## Hướng dẫn sử dụng
1. Mở **Android Studio**.
2. Chọn **Open** và trỏ đến thư mục `TranMaiNgocDuy65130650Thigk`.
3. Chờ Gradle đồng bộ hoàn tất.
4. Nhấn **Run** để chạy ứng dụng trên thiết bị hoặc máy ảo.

---
## Công nghệ sử dụng
* `BottomNavigationView`
* `Fragment`
* `RecyclerView`
* `RSS parsing`
* `Glide`



### Bài tập 13: Ôn tập thi (OnTapThi)
[Chi tiết bài tập](./OnTapThi/app/src/main/java/tmnduy/ntu/ontapthi/MainActivity.java)

<img src="./Images/o1.png" width="200"> <img src="./Images/o2.png" width="200"> <img src="./Images/o3.png" width="200"> <img src="./Images/o4.png" width="200"> <img src="./Images/o5.png" width="200"><img src="./Images/bg.png" width="200">

*Ứng dụng tổng hợp các kiến thức đã học bao gồm Fragment, Navigation Drawer, SharedPreferences để ôn tập cho kỳ thi.*

---

### Bài tập 12: Fragment Tĩnh (FragmentEx_Statically)
[Chi tiết bài tập](./FragmentEx_Statically/app/src/main/java/tmnduy/ntu/fragmentex_statically/MainActivity.java)

<img src="./Images/f1.png" width="200"> <img src="./Images/f2.png" width="200"> <img src="./Images/f3.png" width="200">

*Thực hành chia giao diện người dùng thành các phần độc lập (Fragment) và nhúng chúng trực tiếp vào Layout XML.*

---

### Bài tập 11: Đọc báo tổng hợp (DocBaoTongHop)
[Chi tiết bài tập](./DocBaoTongHop/app/src/main/java/tmnduy/ntu/docbaotonghop/MainActivity.java)

<img src="./Images/appdocbao.png" width="200" alt="DocBaoTongHop Screenshot">

*Ứng dụng đọc báo tổng hợp từ các nguồn RSS, hiển thị danh sách tin tức và xem nội dung chi tiết.*

---

### Bài tập 10: Sử dụng RecyclerView (UsingRecyclerView)
[Chi tiết bài tập](./UsingRecyclerView/app/src/main/java/tmnduy/ntu/usingrecyclerview/MainActivity.java)

<img src="./Images/rv1.png" width="200"> <img src="./Images/rv2.png" width="200"> <img src="./Images/rv3.png" width="200">

*Sử dụng RecyclerView để hiển thị danh sách dữ liệu tối ưu và linh hoạt hơn so với ListView.*

---

### Bài tập 9: Danh sách Món Ăn (AppMonAn)
[Chi tiết bài tập](./AppMonAn/app/src/main/java/tmnduy/ntu/appmonan/MainActivity.java)

<img src="./Images/dsMonAn.png" width="250" alt="AppMonAn Screenshot">

*Ứng dụng danh sách các loại món ăn với khả năng xem chi tiết thông tin từng món.*

---

### Bài tập 8: Danh sách Vật Liệu (DanhSachVatLieu)
[Chi tiết bài tập](./DanhSachVatLieu/app/src/main/java/tmnduy/ntu/danhsachvatlieu/MainActivity.java)

![DanhSachVatLieu Screenshot](./Images/listviewvatlieu.png)
*Sử dụng ListView để hiển thị danh sách các loại vật liệu xây dựng.*

---

### Bài tập 7: Danh sách Tỉnh Thành (DanhSachTinhThanh)
[Chi tiết bài tập](./DanhSachTinhThanh/app/src/main/java/tmnduy/ntu/danhsachtinhthanh/MainActivity.java)

![DanhSachTinhThanh Screenshot](./Images/listview.png)
*Sử dụng ListView để hiển thị danh sách các tỉnh thành Việt Nam.*

---

### Bài tập 6: Máy tính Calculator (Calculator)
[Chi tiết bài tập](./Calculator/app/src/main/java/com/example/calculator/MainActivity.java)

![Calculator Screenshot](./Images/calculator.png)
![Calculator Screenshot](./Images/calculator1.png)
*Ứng dụng máy tính bỏ túi với giao diện đầy đủ các phím số và phép tính nâng cao.*

---

### Bài tập 5: App Tính Chỉ số khối cơ thể (BMI_app)
[Chi tiết bài tập](./BMI_app/app/src/main/java/com/example/bmi_app/MainActivity.java)

![BMI_app Screenshot](./Images/bmi.png)
*Ứng dụng tính toán chỉ số BMI dựa trên chiều cao và cân nặng của người dùng.*

---

### Bài tập 4: App Tính Cộng Trừ Nhân Chia (AppCongTruNhanChia)
[Chi tiết bài tập](./AppCongTruNhanChia/app/src/main/java/com/example/appcongtrunhanchia/MainActivity.java)

![AppCongTruNhanChia Screenshot](./Images/pheptoan.png)
*Ứng dụng thực hiện các phép tính cơ bản giữa hai số nhập từ người dùng.*

---

### Bài tập 3: Giao diện LinearLayout (LinearLayout)
[Chi tiết bài tập](./LinearLayout/app/src/main/java/com/example/linearlayout/MainActivity.java)

![LinearLayout Screenshot](./Images/linearlayout.png)
*Thực hành thiết kế giao diện sử dụng LinearLayout với các thuộc tính cơ bản.*

---

### Bài tập 2: Tính Tổng (TinhTong)
[Chi tiết bài tập](./TinhTong/app/src/main/java/com/example/tinhtong/MainActivity.java)

![TinhTong Screenshot](./Images/tinhtong.png)
*Ứng dụng nhập hai số và hiển thị kết quả tổng của chúng.*

---

### Bài tập 1: Hello World
[Chi tiết bài tập](./HelloWorld/app/src/main/java/com/example/helloworld/MainActivity.java)

![HelloWorld Screenshot](./Images/helloworld.png)
*Ứng dụng Android đầu tiên hiển thị thông điệp "Hello World!".*

---

## Hướng dẫn sử dụng
1. Mở **Android Studio**.
2. Chọn **Open** và dẫn đến thư mục của từng bài tập cụ thể.
3. Chờ Gradle đồng bộ và nhấn **Run** để chạy trên thiết bị.
