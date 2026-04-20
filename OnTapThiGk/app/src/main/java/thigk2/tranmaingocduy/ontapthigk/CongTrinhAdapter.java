package thigk2.tranmaingocduy.ontapthigk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CongTrinhAdapter extends RecyclerView.Adapter<CongTrinhAdapter.ItemCongTrinhViewHolder> {

    private final Context context;
    private final ArrayList<CongTrinh.ItemCongTrinh> dsCongTrinh;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(CongTrinh.ItemCongTrinh congTrinh);
    }

    public CongTrinhAdapter(Context context, ArrayList<CongTrinh.ItemCongTrinh> dsCongTrinh) {
        this(context, dsCongTrinh, null);
    }

    public CongTrinhAdapter(Context context, ArrayList<CongTrinh.ItemCongTrinh> dsCongTrinh, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.dsCongTrinh = dsCongTrinh;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ItemCongTrinhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater napLayout = LayoutInflater.from(context);
        View viewItemBB = napLayout.inflate(R.layout.item_baibao, parent, false);
        return new ItemCongTrinhViewHolder(viewItemBB);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCongTrinhViewHolder holder, int position) {
        CongTrinh.ItemCongTrinh congTrinhCuThe = dsCongTrinh.get(position);
        holder.tvTieuDeCongTrinh.setText(congTrinhCuThe.getTieuDe());
        holder.tvNgayDangCongTrinh.setText(congTrinhCuThe.getNgayDang());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(congTrinhCuThe);
                }
            }
        });

        // urlAnhDaiDien luc nay duoc dung de luu ten anh trong drawable, vi du: "anh1"
        String tenAnh = congTrinhCuThe.getUrlAnhDaiDien();
        if (tenAnh == null || tenAnh.trim().isEmpty()) {
            holder.anhCongTrinh.setImageResource(R.drawable.lg);
            return;
        }

        int resId = context.getResources().getIdentifier(
                tenAnh,
                "drawable",
                context.getPackageName()
        );

        if (resId != 0) {
            holder.anhCongTrinh.setImageResource(resId);
        } else {
            holder.anhCongTrinh.setImageResource(R.drawable.lg);
        }
    }

    @Override
    public int getItemCount() {
        return dsCongTrinh.size();
    }

    public static final class ItemCongTrinhViewHolder extends RecyclerView.ViewHolder {
        ImageView anhCongTrinh;
        TextView tvTieuDeCongTrinh;
        TextView tvNgayDangCongTrinh;

        public ItemCongTrinhViewHolder(@NonNull View itemView) {
            super(itemView);
            anhCongTrinh = itemView.findViewById(R.id.ivAnhDaiDien);
            tvTieuDeCongTrinh = itemView.findViewById(R.id.tvTieuDe);
            tvNgayDangCongTrinh = itemView.findViewById(R.id.tvNgayDang);
        }
    }
}
