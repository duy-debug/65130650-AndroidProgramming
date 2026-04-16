package tmnduy.ntu.ontapbott;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        ItemLandHolder viewholderCreated = new ItemLandHolder(viewItem);
        return viewholderCreated;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLandHolder holder, int position) {
        //Lay doi tuong hien thi
        LandScape landScapeHienThi = lstData.get(position);
        //Trich thong tin
        String caption = landScapeHienThi.getLandCation();
        String tenAnh = landScapeHienThi.getLandImageFileName();
        //Dat vao cac truong thong tin cua Holder
        holder.tvCaption.setText(caption);
        //dat anh
        String packageName = holder.itemView.getContext().getPackageName();
        int imageID = holder.itemView.getResources().getIdentifier(tenAnh, "mipmap", packageName);
        holder.ivLandscape.setImageResource(imageID);
    }

    @Override
    public int getItemCount() {
        return lstData.size();
    }

    class ItemLandHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvCaption;
        ImageView ivLandscape;

        public ItemLandHolder(@NonNull View itemView) {
            super(itemView);
            tvCaption = itemView.findViewById(R.id.textViewCation);
            ivLandscape = itemView.findViewById(R.id.imageViewLand);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int viTriDuocClick = getAbsoluteAdapterPosition();
            LandScape phanTuDuocClick = lstData.get(viTriDuocClick);
            //lay thong tin
            String ten = phanTuDuocClick.getLandCation();
            String tenFile = phanTuDuocClick.getLandImageFileName();
            //
            String chuoiThongBao = "Ban vua click vao : " + ten;
            Toast.makeText(v.getContext(), chuoiThongBao, Toast.LENGTH_SHORT).show();
        }
    }
}