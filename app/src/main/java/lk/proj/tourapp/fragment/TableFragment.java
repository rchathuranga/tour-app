package lk.proj.tourapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import lk.proj.tourapp.Hotel_Book;
import lk.proj.tourapp.Hotel_Details;
import lk.proj.tourapp.R;
import lk.proj.tourapp.RestaurantBook;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.adapter.HotelListAdapter;
import lk.proj.tourapp.adapter.Table;
import lk.proj.tourapp.adapter.TableListAdapter;
import lk.proj.tourapp.dto.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private User user;
    private ListView listView;
    private FirebaseFirestore db;

    public TableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TableFragment newInstance(String param1, String param2) {
        TableFragment fragment = new TableFragment();
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
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.tableListView);

        user = (User) requireActivity().getIntent().getSerializableExtra("user");

        ArrayList<Table> tableList = new ArrayList<>();
        TableListAdapter ad = new TableListAdapter(getActivity(), R.layout.table_list, tableList, user);
        listView.setAdapter(ad);

        db.collection("resturants").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Table table = new Table();

                                table.setTableId(document.getId());
                                table.setRestaurantName(document.getData().get("restaurantName").toString());
                                table.setNoOfSeats(Integer.parseInt(document.getData().get("noOfSeats").toString()));
                                table.setBookingPrice(Double.parseDouble(document.getData().get("bookingCharge").toString()));
                                table.setImageUrl(document.getData().get("imageUrl").toString());
                                table.setLocation(document.getData().get("location").toString());
                                table.setBookBtnAction(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        table.setBookBtnAction(null);
                                        
                                        Intent intent = new Intent(getActivity(), RestaurantBook.class);
                                        intent.putExtra("user", user);
                                        intent.putExtra("table", table);
                                        startActivity(intent);

                                    }
                                });

                                tableList.add(table);
                                ad.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}
