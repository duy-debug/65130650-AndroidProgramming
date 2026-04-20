package thigk2.tranmaingocduy.ontapthigk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Cau4Fragment extends Fragment {

    private static final String TAG = "Cau4Fragment";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String RSS_URL = "https://www.vnexpress.net/rss/giao-duc.rss";

    private String mParam1;
    private String mParam2;

    public Cau4Fragment() {
    }

    public static Cau4Fragment newInstance(String param1, String param2) {
        Cau4Fragment fragment = new Cau4Fragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cau4, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "1. Bat dau khoi tao Cau4Fragment");

        RecyclerView rvDanhSachBaiBao = view.findViewById(R.id.rvDanhSachBaiBao);
        Log.d(TAG, "2. RecyclerView = " + rvDanhSachBaiBao);
        rvDanhSachBaiBao.setLayoutManager(new LinearLayoutManager(requireContext()));
        Log.d(TAG, "3. Da gan LayoutManager");

        ArrayList<TinTucRSS> dsTinTuc = new ArrayList<>();
        TinTucRSSAdapter adapter = new TinTucRSSAdapter(
                requireContext(),
                dsTinTuc,
                new TinTucRSSAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(TinTucRSS tinTucRSS) {
                        Toast.makeText(requireContext(),
                                "Ban vua chon: " + tinTucRSS.getTieuDe(),
                                Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "8. Click item: " + tinTucRSS.getTieuDe());

                        String link = tinTucRSS.getLinkBaiBao();
                        if (link != null && !link.trim().isEmpty()) {
                            Log.d(TAG, "8. Mo link bai bao: " + link);
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
                        }
                    }
                }
        );
        rvDanhSachBaiBao.setAdapter(adapter);
        Log.d(TAG, "4. Da gan Adapter");

        Toast.makeText(requireContext(), "Dang tai RSS...", Toast.LENGTH_SHORT).show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "5. Thread lay RSS bat dau chay");
                final ArrayList<TinTucRSS> duLieuRSS = GetDataFromRSS.layDuLieuRSS(RSS_URL);
                Log.d(TAG, "6. GetDataFromRSS tra ve size = " + duLieuRSS.size());

                if (!isAdded()) {
                    Log.e(TAG, "6. Fragment da detach truoc khi update UI");
                    return;
                }

                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "7. Dang cap nhat UI tren main thread");
                        dsTinTuc.clear();
                        dsTinTuc.addAll(duLieuRSS);
                        Log.d(TAG, "7. dsTinTuc sau khi addAll = " + dsTinTuc.size());
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "7. Da goi notifyDataSetChanged()");

                        if (duLieuRSS.isEmpty()) {
                            String loi = GetDataFromRSS.getLastError();
                            Log.e(TAG, "7. Danh sach RSS rong. lastError=" + loi);
                            Toast.makeText(requireContext(),
                                    loi == null || loi.trim().isEmpty() ? "Khong doc duoc RSS" : loi,
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        }).start();
    }
}
