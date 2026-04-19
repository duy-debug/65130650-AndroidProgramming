package tmnduy.ntu.ontapthigk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class TinTucRSSAdapter extends RecyclerView.Adapter<TinTucRSSAdapter.ItemTinTucViewHolder> {

    private static final String TAG = "TinTucRSSAdapter";

    private final Context context;
    private final ArrayList<TinTucRSS> dsTinTuc;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(TinTucRSS tinTucRSS);
    }

    public TinTucRSSAdapter(Context context, ArrayList<TinTucRSS> dsTinTuc, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dsTinTuc = dsTinTuc;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemTinTucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder()");
        View view = LayoutInflater.from(context).inflate(R.layout.item_tintuc_rss, parent, false);
        return new ItemTinTucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemTinTucViewHolder holder, int position) {
        TinTucRSS tinTucRSS = dsTinTuc.get(position);
        holder.tvTieuDe.setText(tinTucRSS.getTieuDe());
        holder.tvMoTaTin.setText(tinTucRSS.getMoTa());
        holder.tvNgayDang.setText(tinTucRSS.getNgayDang());

        String imageUrl = tinTucRSS.getUrlAnhDaiDien();
        Log.d(TAG, "Bind item: title=" + tinTucRSS.getTieuDe() + ", imageUrl=" + imageUrl);
        Glide.with(context).clear(holder.ivAnhDaiDien);

        if (imageUrl == null || imageUrl.trim().isEmpty()) {
            Log.d(TAG, "Bind item fallback drawable cho: " + tinTucRSS.getTieuDe());
            holder.ivAnhDaiDien.setImageResource(R.drawable.lg);
        } else {
            GlideUrl glideUrl = new GlideUrl(
                    imageUrl,
                    new LazyHeaders.Builder()
                            .addHeader("User-Agent", "Mozilla/5.0 (Android)")
                            .addHeader("Referer", "https://vnexpress.net/")
                            .build()
            );

            Glide.with(context)
                    .load(glideUrl)
                    .transform(new CenterCrop())
                    .placeholder(R.drawable.lg)
                    .error(R.drawable.lg)
                    .listener(new RequestListener<android.graphics.drawable.Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<android.graphics.drawable.Drawable> target, boolean isFirstResource) {
                            Log.e(TAG, "Glide load anh that bai: title=" + tinTucRSS.getTieuDe() + ", imageUrl=" + imageUrl, e);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(android.graphics.drawable.Drawable resource, Object model, Target<android.graphics.drawable.Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            Log.d(TAG, "Glide load anh thanh cong: title=" + tinTucRSS.getTieuDe() + ", imageUrl=" + imageUrl);
                            return false;
                        }
                    })
                    .into(holder.ivAnhDaiDien);
        }

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(tinTucRSS);
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount() = " + dsTinTuc.size());
        return dsTinTuc.size();
    }

    public static class ItemTinTucViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAnhDaiDien;
        TextView tvTieuDe;
        TextView tvMoTaTin;
        TextView tvNgayDang;

        public ItemTinTucViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAnhDaiDien = itemView.findViewById(R.id.ivAnhDaiDien);
            tvTieuDe = itemView.findViewById(R.id.tvTieuDe);
            tvMoTaTin = itemView.findViewById(R.id.tvMoTaTin);
            tvNgayDang = itemView.findViewById(R.id.tvNgayDang);
        }
    }
}
