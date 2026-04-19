package tmnduy.ntu.ontapthigk;

public class CongTrinh {
    public static class ItemCongTrinh {
        private String tieuDe;
        private String urlAnhDaiDien;
        private String ngayDang;

        public ItemCongTrinh() {
        }

        public ItemCongTrinh(String tieuDe, String urlAnhDaiDien, String ngayDang) {
            this.tieuDe = tieuDe;
            this.urlAnhDaiDien = urlAnhDaiDien;
            this.ngayDang = ngayDang;
        }

        public String getTieuDe() {
            return tieuDe;
        }

        public void setTieuDe(String tieuDe) {
            this.tieuDe = tieuDe;
        }

        public String getUrlAnhDaiDien() {
            return urlAnhDaiDien;
        }

        public void setUrlAnhDaiDien(String urlAnhDaiDien) {
            this.urlAnhDaiDien = urlAnhDaiDien;
        }

        public String getNgayDang() {
            return ngayDang;
        }

        public void setNgayDang(String ngayDang) {
            this.ngayDang = ngayDang;
        }
    }
}
