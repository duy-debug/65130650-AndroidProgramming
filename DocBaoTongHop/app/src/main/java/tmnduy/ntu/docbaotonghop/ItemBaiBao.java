package tmnduy.ntu.docbaotonghop;

public class ItemBaiBao {
    private String tieuDe;
    private String urlAnhDaiDien;
    private String ngayDang;

    public ItemBaiBao(String tieuDe, String urlAnhDaiDien, String ngayDang) {
        this.tieuDe = tieuDe;
        this.urlAnhDaiDien = urlAnhDaiDien;
        this.ngayDang = ngayDang;
    }

    public ItemBaiBao() {
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
