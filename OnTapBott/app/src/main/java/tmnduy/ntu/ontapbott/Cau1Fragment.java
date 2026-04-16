package tmnduy.ntu.ontapbott;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Cau1Fragment extends Fragment {
    EditText editText_Met;
    EditText editText_KiLoMet;
    Button btnKetQua;
    Button btnReset;
    public Cau1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cau1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Cau1Fragment newInstance(String param1, String param2) {
        Cau1Fragment fragment = new Cau1Fragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewCau1 = inflater.inflate(R.layout.fragment_cau1, container, false);
        // Tìm điều khiển trong view này
        editText_Met = viewCau1.findViewById(R.id.donViDoM);
        editText_KiLoMet = viewCau1.findViewById(R.id.donViDoKM);
        btnKetQua = viewCau1.findViewById(R.id.btnKetQuaDo);
        btnReset = viewCau1.findViewById(R.id.reset);
        btnKetQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String duLieuMet = editText_Met.getText().toString();
                String duLieuKiLoMet = editText_KiLoMet.getText().toString();
                //
                if(!duLieuMet.isEmpty()) // user có nhập dữ liệu mét, cần đổi sang km
                {
                    double met = Double.parseDouble(duLieuMet);
                    double km = met/1000;
                    Toast.makeText(viewCau1.getContext(), met+" m = " + km +" km", Toast.LENGTH_SHORT).show();
                    editText_KiLoMet.setText(String.valueOf(km));
                }
                else{
                    double km = Double.parseDouble(duLieuKiLoMet);
                    double met = km*1000;
                    Toast.makeText(viewCau1.getContext(), km+" km = " + met +" m", Toast.LENGTH_SHORT).show();
                    editText_Met.setText(String.valueOf(met));
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_Met.setText("");
                editText_KiLoMet.setText("");
            }
        });

        return viewCau1;
    }
}