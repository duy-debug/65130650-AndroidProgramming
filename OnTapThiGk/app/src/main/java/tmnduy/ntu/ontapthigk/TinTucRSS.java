package tmnduy.ntu.ontapthigk;

public class TinTucRSS {
    private String tieuDe;
    private String moTa;
    private String urlAnhDaiDien;
    private String ngayDang;
    private String linkBaiBao;

    public TinTucRSS() {
    }

    public TinTucRSS(String tieuDe, String moTa, String urlAnhDaiDien, String ngayDang, String linkBaiBao) {
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.urlAnhDaiDien = urlAnhDaiDien;
        this.ngayDang = ngayDang;
        this.linkBaiBao = linkBaiBao;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
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

    public String getLinkBaiBao() {
        return linkBaiBao;
    }

    public void setLinkBaiBao(String linkBaiBao) {
        this.linkBaiBao = linkBaiBao;
    }
}
