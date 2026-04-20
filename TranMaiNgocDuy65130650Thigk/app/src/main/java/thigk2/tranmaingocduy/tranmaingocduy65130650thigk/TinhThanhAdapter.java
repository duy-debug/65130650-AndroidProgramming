package thigk2.tranmaingocduy.tranmaingocduy65130650thigk;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TinhThanhAdapter extends RecyclerView.Adapter<TinhThanhAdapter.TinhThanhViewHolder> {

    private final Context context;
    private final ArrayList<TinhThanhItem> dsTinhThanh;

    public TinhThanhAdapter(Context context, ArrayList<TinhThanhItem> dsTinhThanh) {
        this.context = context;
        this.dsTinhThanh = dsTinhThanh;
    }

    @NonNull
    @Override
    public TinhThanhViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tinh_thanh, parent, false);
        return new TinhThanhViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TinhThanhViewHolder holder, int position) {
        TinhThanhItem item = dsTinhThanh.get(position);
        holder.tvTen.setText(item.getTen());
        holder.itemView.setOnClickListener(v ->
                Toast.makeText(context, item.getTen(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return dsTinhThanh.size();
    }

    public static class TinhThanhViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen;

        public TinhThanhViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTenTinhThanh);
        }
    }
}