package lk.proj.tourapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Cab;
import lk.proj.tourapp.adapter.CabListAdapter;
import lk.proj.tourapp.adapter.Hotel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CabFragment extends Fragment {
    private FirebaseFirestore db;
    private GridView gridView;
    Button btnSearch;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CabFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CabFragment newInstance(String param1, String param2) {
        CabFragment fragment = new CabFragment();
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
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Cab> cabList= new ArrayList<>();
        CabListAdapter adapter= new CabListAdapter(getActivity(),R.layout.cab_list,cabList);
        gridView = (GridView) view.findViewById(R.id.cabListView);
        gridView.setAdapter(adapter);

        btnSearch =  view.findViewById(R.id.btnSearchCabs);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                db.collection("drivers").get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Cab hotel = new Cab();
                                        hotel.setDriverId(document.getId());
                                        hotel.setDriverName(document.getData().get("driverName").toString());
                                        hotel.setContactNo(document.getData().get("contactNo").toString());
                                        hotel.setEmail(document.getData().get("email").toString());
                                        hotel.setImageUrl(document.getData().get("imageUrl").toString());
                                        hotel.setVehicleType(document.getData().get("vehicleType").toString());
                                        cabList.add(hotel);
                                    }
                                    adapter.notifyDataSetChanged();
                                } else {
                                    Log.w("TAG", "Error getting documents.", task.getException());
                                }
                            }
                        });

            }
        });
    }
}