package tmnduy.ntu.docbaotonghop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArrayBaiBaoAdapter extends RecyclerView.Adapter<ArrayBaiBaoAdapter.ItemBBViewHolder> {
    Context context;
    ArrayList<ItemBaiBao> dsBaiBao;

    public ArrayBaiBaoAdapter(Context context, ArrayList<ItemBaiBao> dsBaiBao) {
        this.context = context;
        this.dsBaiBao = dsBaiBao;
    }

    @NonNull
    @Override
    public ItemBBViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater napLayout = LayoutInflater.from(context);
        View viewItemBB = napLayout.inflate(R.layout.item_baibao, parent, false);
        ItemBBViewHolder holder = new ItemBBViewHolder(viewItemBB);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemBBViewHolder holder, int position) {
        ItemBaiBao baiBaoCuThe = dsBaiBao.get(position);
        holder.tvTieuDeBaiBao.setText(baiBaoCuThe.getTieuDe());
        holder.tvNgayDangBaiBao.setText(baiBaoCuThe.getNgayDang());
        // ảnh đại diện
        // vì ta sẽ tìm thư viện để load ảnh từ URL
        holder.anhBB.setImageResource(baiBaoCuThe.getUrlAnhDaiDien());
    }

    @Override
    public int getItemCount() {
        return dsBaiBao.size();
    }

    static final class ItemBBViewHolder extends RecyclerView.ViewHolder{
        ImageView anhBB;
        TextView tvTieuDeBaiBao;
        TextView tvNgayDangBaiBao;
        public ItemBBViewHolder(@NonNull View itemView) {
            super(itemView);
            anhBB = itemView.findViewById(R.id.ivAnhDaiDien);
            tvTieuDeBaiBao = itemView.findViewById(R.id.tvTieuDe);
            tvNgayDangBaiBao = itemView.findViewById(R.id.tvNgayDang);
        }
    }
}
