package tmnduy.ntu.docbaotonghop;

public class ItemBaiBao {
    private String tieuDe;
    private int urlAnhDaiDien;
    private String ngayDang;

    public ItemBaiBao(String tieuDe, int urlAnhDaiDien, String ngayDang) {
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

    public int getUrlAnhDaiDien() {
        return urlAnhDaiDien;
    }

    public void setUrlAnhDaiDien(int urlAnhDaiDien) {
        this.urlAnhDaiDien = urlAnhDaiDien;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }
}
