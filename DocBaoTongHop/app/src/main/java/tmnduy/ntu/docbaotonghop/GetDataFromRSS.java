package tmnduy.ntu.docbaotonghop;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GetDataFromRSS {

    public static ArrayList<ItemBaiBao> layDuLieuRSS(String urlString) {
        ArrayList<ItemBaiBao> danhSach = new ArrayList<>();
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(10000);
            connection.setReadTimeout(10000);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);

            int eventType = parser.getEventType();
            String tieuDe = "", anhUrl = "", ngayDang = "";
            boolean isInsideItem = false;
            String currentTagName = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String name = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = name;
                        if (name.equals("item")) {
                            isInsideItem = true;
                            tieuDe = ""; anhUrl = ""; ngayDang = "";
                        } else if (isInsideItem) {
                            // Ưu tiên lấy ảnh từ thuộc tính 'url' (enclosure, media:content...)
                            String attrUrl = parser.getAttributeValue(null, "url");
                            if (attrUrl != null && !attrUrl.isEmpty()) {
                                anhUrl = attrUrl;
                            }
                        }
                        break;

                    case XmlPullParser.CDSECT:
                    case XmlPullParser.TEXT:
                        if (isInsideItem && !currentTagName.isEmpty()) {
                            String text = parser.getText();
                            if (text != null && !text.trim().isEmpty()) {
                                if (currentTagName.equals("title")) {
                                    tieuDe = text.trim();
                                } else if (currentTagName.equals("pubDate")) {
                                    ngayDang = text.trim();
                                } else if (currentTagName.equals("description")) {
                                    // Nếu chưa có ảnh từ thẻ url, tìm trong description HTML
                                    if (anhUrl.isEmpty()) {
                                        anhUrl = trichXuatUrlAnh(text);
                                    }
                                }
                            }
                        }
                        break;

                    case XmlPullParser.END_TAG:
                        if (name.equals("item")) {
                            danhSach.add(new ItemBaiBao(tieuDe, anhUrl, ngayDang));
                            isInsideItem = false;
                        }
                        currentTagName = "";
                        break;
                }
                eventType = parser.next();
            }
            inputStream.close();
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    private static String trichXuatUrlAnh(String desc) {
        String result = "";
        try {
            // Tìm kiếm src=" hoặc src=' hoặc src=&quot;
            String[] patterns = {"src=\"", "src='", "src=&quot;"};
            for (String p : patterns) {
                if (desc.contains(p)) {
                    int start = desc.indexOf(p) + p.length();
                    String quoteType = p.contains("&quot;") ? "&quot;" : (p.contains("'") ? "'" : "\"");
                    int end = desc.indexOf(quoteType, start);
                    if (end > start) {
                        result = desc.substring(start, end);
                        break;
                    }
                }
            }
        } catch (Exception e) {
            return "";
        }
        return result;
    }
}
