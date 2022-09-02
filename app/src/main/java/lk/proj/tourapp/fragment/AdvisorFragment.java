package lk.proj.tourapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import lk.proj.tourapp.Advisor_Details;
import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Advisor;
import lk.proj.tourapp.adapter.AdvisorListAdapter;

import java.util.ArrayList;

public class AdvisorFragment extends Fragment {

    private FirebaseFirestore db;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AdvisorFragment() {
        // Required empty public constructor
    }

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
        db = FirebaseFirestore.getInstance();

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
        ListView viewById = (ListView) view.findViewById(R.id.adviserListView);

        ArrayList<Advisor> advisorList = new ArrayList<>();
        AdvisorListAdapter ad = new AdvisorListAdapter(getActivity(), R.layout.advisor_list, advisorList);
        viewById.setAdapter(ad);


        db.collection("advisors").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Advisor advisor = new Advisor();

                                advisor.setName(document.getData().get("name").toString());
                                advisor.setId(document.getId());
                                advisor.setContact(document.getData().get("contactNo").toString());
                                advisor.setBadReviews(Integer.parseInt(document.getData().get("badReview").toString()));
                                advisor.setGoodReviews(Integer.parseInt(document.getData().get("goodReview").toString()));
                                advisor.setHiredCount(Integer.parseInt(document.getData().get("hiredCount").toString()));
                                advisor.setImg(document.getData().get("imageUrl").toString());
                                advisor.setEmail(document.getData().get("email").toString());
                                advisorList.add(advisor);

                                ad.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });


        viewById.setClickable(true);
        viewById.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), Advisor_Details.class);
                intent.putExtra("advisorId",advisorList.get(i).getId());
                System.out.println(advisorList.get(i).getId());
                startActivity(intent);

            }
        });
    }
}