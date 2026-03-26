    package tmnduy.ntu.usingrecyclerview;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.google.android.material.snackbar.Snackbar;

    import java.util.ArrayList;

    public class LandScapeAdapter extends RecyclerView.Adapter<LandScapeAdapter.ItemLandHolder> {
        Context context;
        ArrayList<LandScape> lstData;
        public LandScapeAdapter(Context context, ArrayList<LandScape> lstData) {
            this.context = context;
            this.lstData = lstData;
        }
        @NonNull
        @Override
        public ItemLandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater cai_bom = LayoutInflater.from(context);
            View viewItem = cai_bom.inflate(R.layout.item_land, parent, false);
            ItemLandHolder holder = new ItemLandHolder(viewItem);
            return new ItemLandHolder(viewItem); // ✅ BẮT BUỘC phải return cái này
        }

        @Override
        public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
            // Lay doi tuong tai vi tri position
            LandScape landScapeHienThi = lstData.get(position);
            // Trich thong tin
            String caption = landScapeHienThi.getLandCation();
            String tenAnh = landScapeHienThi.getLandImageFileName();
            // Dat cac truong thong tin cua holder
            holder.tvCaption.setText(caption);
            // Đặt ảnh
            String path = holder.itemView.getContext().getPackageName();
            int imageID = holder.itemView.getResources().getIdentifier(tenAnh, "drawable", path);
            holder.ivLandScape.setImageResource(imageID);

        }

        @Override
        public int getItemCount() {
            return lstData.size();
        }

        class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView tvCaption;
            ImageView ivLandScape;

            public ItemLandHolder(@NonNull View itemView) {
                super(itemView);
                tvCaption = itemView.findViewById(R.id.textViewCaption);
                ivLandScape = itemView.findViewById(R.id.imageViewLand);
                itemView.setOnClickListener(this);

            }

            @Override
            public void onClick(View v) {
                // CODE Ở ĐÂY
                int viTriDuocClick = getAdapterPosition();
                LandScape phanTuDuocChon = lstData.get(viTriDuocClick);
                // Bốc thông tin
                String ten = phanTuDuocChon.getLandCation();
                String tenFile = phanTuDuocChon.getLandImageFileName();
                Toast.makeText(context, "Bạn vừa chọn: " + ten, Toast.LENGTH_SHORT).show();
            }
        }
    }
