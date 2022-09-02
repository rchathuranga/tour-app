package lk.proj.tourapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Cab;
import lk.proj.tourapp.adapter.CabListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CabFragment extends Fragment {

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
        ArrayList<Cab> advisorArrayList= new ArrayList<>();
        int[] image={R.drawable.sample_car,R.drawable.sample_car,
                R.drawable.sample_car,R.drawable.sample_car,R.drawable.sample_car,
                R.drawable.sample_car,R.drawable.sample_car};
        String[] name ={"Driver : Vin Diesel","Driver : Vin Diesel","Driver : Vin Diesel",
                "Driver : Vin Diesel","Driver : Vin Diesel","Driver : Vin Diesel","Driver : Vin Diesel"};
        String[] emails={"+94789632544","+94789632544",
                "+94789632544","+94789632544","+94789632544",
                "+94789632544","+94789632544"};
        for(int i=0;i<image.length;i++){
            Cab advisor=new Cab(name[i],emails[i],image[i]);
            advisorArrayList.add(advisor);
        }
        ListView viewById = (ListView) view.findViewById(R.id.cabListView);
        CabListAdapter ad= new CabListAdapter(getActivity(),R.layout.cab_list,advisorArrayList);
        viewById.setAdapter(ad);
    }
}