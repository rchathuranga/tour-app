package com.tourapp.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tourapp.R;
import com.tourapp.adapter.Advisor;
import com.tourapp.adapter.AdvisorListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AdvisorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AdvisorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AdvisorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AdvisorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AdvisorFragment newInstance(String param1, String param2) {
        AdvisorFragment fragment = new AdvisorFragment();
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
        View view = inflater.inflate(R.layout.fragment_advisor, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> advisorArrayList= new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("advisor");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    advisorArrayList.add(dataSnapshot.getValue().toString());
                    System.out.println("advisorArrayList "+dataSnapshot.getValue().toString());

                }
                System.out.println(advisorArrayList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//        ArrayList<Advisor> advisorArrayList= new ArrayList<>();
//        int[] image={R.drawable.test_advisor,R.drawable.test_advisor,
//                R.drawable.test_advisor,R.drawable.test_advisor,R.drawable.test_advisor,
//                R.drawable.test_advisor,R.drawable.test_advisor};
//        String[] name ={"Test Advisor","Test Advisor","Test Advisor",
//                "Test Advisor","Test Advisor","Test Advisor","Test Advisor"};
//        String[] emails={"+947689521123","+947689521123",
//                "+947689521123","+947689521123","+947689521123",
//                "+947689521123","+947689521123"};
//        for(int i=0;i<image.length;i++){
//            Advisor advisor=new Advisor(name[i],emails[i],image[i]);
//            advisorArrayList.add(advisor);
//        }
//        ListView viewById = (ListView) view.findViewById(R.id.adviserListView);
//        AdvisorListAdapter ad= new AdvisorListAdapter(getActivity(),R.layout.advisor_list,advisorArrayList);
//        viewById.setAdapter(ad);
    }
}