package thigk2.tranmaingocduy.tranmaingocduy65130650thigk;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetDataFromRSS {

    private static final String TAG = "GetDataFromRSS";
    private static volatile String lastError;

    public static String getLastError() {
        return lastError;
    }

    private static void setLastError(String error) {
        lastError = error;
    }

    public static ArrayList<TinTucRSS> layDuLieuRSS(String rssUrl) {
        Log.d(TAG, "Bat dau doc RSS: " + rssUrl);
        ArrayList<TinTucRSS> danhSachTin = tryFetch(rssUrl);
        if (!danhSachTin.isEmpty()) {
            Log.d(TAG, "Doc RSS thanh cong, so item = " + danhSachTin.size());
            return danhSachTin;
        }

        if (rssUrl != null && rssUrl.contains("://vnexpress.net/")) {
            String fallbackUrl = rssUrl.replace("://vnexpress.net/", "://www.vnexpress.net/");
            Log.d(TAG, "Thu fallback sang domain www: " + fallbackUrl);
            ArrayList<TinTucRSS> fallback = tryFetch(fallbackUrl);
            if (!fallback.isEmpty()) {
                Log.d(TAG, "Fallback thanh cong, so item = " + fallback.size());
                return fallback;
            }
        }

        Log.e(TAG, "Khong doc duoc RSS, danh sach rong. lastError=" + lastError);
        return danhSachTin;
    }

    private static ArrayList<TinTucRSS> tryFetch(String rssUrl) {
        ArrayList<TinTucRSS> danhSachTin = new ArrayList<>();
        HttpURLConnection ketNoi = null;
        InputStream luongNhap = null;
        setLastError(null);

        try {
            Log.d(TAG, "1. Mo ket noi: " + rssUrl);

            URL url = new URL(rssUrl);
            ketNoi = (HttpURLConnection) url.openConnection();
            ketNoi.setRequestMethod("GET");
            ketNoi.setInstanceFollowRedirects(true);
            ketNoi.setConnectTimeout(10000);
            ketNoi.setReadTimeout(10000);
            ketNoi.setDoInput(true);
            ketNoi.setUseCaches(false);
            ketNoi.setRequestProperty("User-Agent", "Mozilla/5.0 (Android)");
            ketNoi.setRequestProperty("Accept", "application/rss+xml, application/xml;q=0.9, */*;q=0.8");
            ketNoi.setRequestProperty("Connection", "close");
            ketNoi.connect();

            int responseCode = ketNoi.getResponseCode();
            Log.d(TAG, "2. Response code = " + responseCode);
            if (responseCode != HttpURLConnection.HTTP_OK) {
                setLastError("HTTP error: " + responseCode);
                return danhSachTin;
            }

            luongNhap = ketNoi.getInputStream();
            Log.d(TAG, "3. Bat dau doc InputStream");

            XmlPullParserFactory nhaMay = XmlPullParserFactory.newInstance();
            nhaMay.setNamespaceAware(false);
            XmlPullParser boXuLy = nhaMay.newPullParser();
            boXuLy.setInput(luongNhap, "UTF-8");

            boolean dangTrongItem = false;
            TinTucRSS tinHienTai = null;
            String tenThe = "";

            int loaiSuKien = boXuLy.getEventType();
            while (loaiSuKien != XmlPullParser.END_DOCUMENT) {
                switch (loaiSuKien) {
                    case XmlPullParser.START_TAG:
                        tenThe = boXuLy.getName() == null ? "" : boXuLy.getName();
                        if (tenThe.equalsIgnoreCase("item")) {
                            dangTrongItem = true;
                            tinHienTai = new TinTucRSS();
                            Log.d(TAG, "4. Gap item moi");
                        } else if (dangTrongItem && tinHienTai != null && laTheAnh(tenThe)) {
                            String urlAnh = layUrlThuocTinh(boXuLy);
                            if (!laChuoiRong(urlAnh) && laChuoiRong(tinHienTai.getUrlAnhDaiDien())) {
                                tinHienTai.setUrlAnhDaiDien(chuanHoaUrlAnh(urlAnh));
                                Log.d(TAG, "4. Anh tu the [" + tenThe + "] = " + tinHienTai.getUrlAnhDaiDien());
                            }
                        }
                        break;

                    case XmlPullParser.TEXT:
                    case XmlPullParser.CDSECT:
                        if (dangTrongItem && tinHienTai != null) {
                            String noiDung = boXuLy.getText();
                            if (noiDung != null) {
                                noiDung = noiDung.trim();
                            }

                            if (!laChuoiRong(noiDung)) {
                                if (tenThe.equalsIgnoreCase("title")) {
                                    tinHienTai.setTieuDe(noiDung);
                                } else if (tenThe.equalsIgnoreCase("description")) {
                                    tinHienTai.setMoTa(trichXuatMoTa(noiDung));
                                    if (laChuoiRong(tinHienTai.getUrlAnhDaiDien())) {
                                        String urlAnh = trichXuatUrlAnh(noiDung);
                                        if (!laChuoiRong(urlAnh)) {
                                            tinHienTai.setUrlAnhDaiDien(chuanHoaUrlAnh(urlAnh));
                                            Log.d(TAG, "4. Anh tu description = " + tinHienTai.getUrlAnhDaiDien());
                                        }
                                    }
                                } else if (tenThe.equalsIgnoreCase("pubDate")) {
                                    tinHienTai.setNgayDang(noiDung);
                                } else if (tenThe.equalsIgnoreCase("link")) {
                                    tinHienTai.setLinkBaiBao(noiDung);
                                }
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (boXuLy.getName() != null && boXuLy.getName().equalsIgnoreCase("item")) {
                            if (tinHienTai != null && !laChuoiRong(tinHienTai.getTieuDe())) {
                                danhSachTin.add(tinHienTai);
                                Log.d(TAG, "5. Item xong: title=" + tinHienTai.getTieuDe()
                                        + ", imageUrl=" + tinHienTai.getUrlAnhDaiDien());
                            }
                            dangTrongItem = false;
                            tinHienTai = null;
                        }
                        tenThe = "";
                        break;
                }
                loaiSuKien = boXuLy.next();
            }

            Log.d(TAG, "6. Tong item parse duoc = " + danhSachTin.size());
        } catch (Exception e) {
            setLastError(e.getClass().getSimpleName() + ": " + e.getMessage());
            Log.e(TAG, "Loi lay/parse RSS", e);
        } finally {
            try {
                if (luongNhap != null) {
                    luongNhap.close();
                }
            } catch (Exception ignored) {
            }

            if (ketNoi != null) {
                ketNoi.disconnect();
            }
        }

        return danhSachTin;
    }

    private static String layUrlThuocTinh(XmlPullParser boXuLy) {
        String urlAnh = boXuLy.getAttributeValue(null, "url");
        if (!laChuoiRong(urlAnh)) {
            return urlAnh;
        }

        for (int i = 0; i < boXuLy.getAttributeCount(); i++) {
            String tenThuocTinh = boXuLy.getAttributeName(i);
            if ("url".equalsIgnoreCase(tenThuocTinh) || "src".equalsIgnoreCase(tenThuocTinh)) {
                return boXuLy.getAttributeValue(i);
            }
        }

        return null;
    }

    private static boolean laTheAnh(String tenThe) {
        if (tenThe == null) {
            return false;
        }

        String tenTheThuong = tenThe.toLowerCase();
        return tenTheThuong.equals("enclosure")
                || tenTheThuong.equals("media:content")
                || tenTheThuong.equals("media:thumbnail")
                || tenTheThuong.endsWith(":content")
                || tenTheThuong.endsWith(":thumbnail");
    }

    private static String trichXuatUrlAnh(String html) {
        if (html == null) {
            return "";
        }

        try {
            String htmlGiaiMa = html
                    .replace("&lt;", "<")
                    .replace("&gt;", ">")
                    .replace("&quot;", "\"")
                    .replace("&amp;", "&");

            Pattern mauImg = Pattern.compile("<img[^>]+(?:src|data-src)=[\"']([^\"']+)[\"']", Pattern.CASE_INSENSITIVE);
            Matcher boSoKhop = mauImg.matcher(htmlGiaiMa);
            if (boSoKhop.find()) {
                return boSoKhop.group(1);
            }
        } catch (Exception e) {
            Log.e(TAG, "Loi trichXuatUrlAnh", e);
        }

        return "";
    }

    private static String trichXuatMoTa(String html) {
        if (html == null) {
            return "";
        }

        String vanBan = html
                .replace("&lt;", "<")
                .replace("&gt;", ">")
                .replace("&quot;", "\"")
                .replace("&amp;", "&")
                .replaceAll("<[^>]+>", " ")
                .replaceAll("\\s+", " ")
                .trim();

        if (vanBan.length() > 160) {
            return vanBan.substring(0, 160).trim() + "...";
        }
        return vanBan;
    }

    private static String chuanHoaUrlAnh(String urlAnh) {
        if (laChuoiRong(urlAnh)) {
            return "";
        }

        String url = urlAnh.trim();
        if (url.startsWith("//")) {
            return "https:" + url;
        }
        return url;
    }

    private static boolean laChuoiRong(String giaTri) {
        return giaTri == null || giaTri.trim().isEmpty();
    }
}

