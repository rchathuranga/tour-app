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
import lk.proj.tourapp.Hotel_Book;
import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Advisor;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.adapter.HotelListAdapter;
import lk.proj.tourapp.Hotel_Details;
import lk.proj.tourapp.dto.User;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {
    private FirebaseFirestore db;
    private ListView listView;
    private User user;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HotelFragment() {
        // Required empty public constructor
    }

    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
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
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        listView = (ListView) view.findViewById(R.id.hotelListView);
        View viewById = view.findViewById(R.id.btnMoreInfo);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<Hotel> hotelArrayList = new ArrayList<>();
        HotelListAdapter ad = new HotelListAdapter(getActivity(), R.layout.hotel_list, hotelArrayList);
        listView.setAdapter(ad);

        user = (User) requireActivity().getIntent().getSerializableExtra("user");

        db.collection("hotel").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Hotel hotel = new Hotel();

                                hotel.setHotelId(document.getId());
                                hotel.setHotelName(document.getData().get("name").toString());
                                hotel.setAvailableRooms(Integer.parseInt(document.getData().get("availableRoom").toString()));
                                hotel.setRoomTypes(document.getData().get("roomType").toString());
                                hotel.setRating(document.getData().get("rating").toString());
                                hotel.setDescription(document.getData().get("description").toString());
                                hotel.setContactNo(document.getData().get("contactNo").toString());
                                hotel.setEmail(document.getData().get("email").toString());
                                hotel.setImageUrl(document.getData().get("imageUrl").toString());
                                hotel.setLocation(document.getData().get("location").toString());
                                hotel.setPrice(Double.valueOf(document.getData().get("price").toString()));


                                hotel.setBtnMoreInfoClickEvent(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getActivity(), Hotel_Details.class);
                                        hotel.setBtnMoreInfoClickEvent(null);
                                        hotel.setBtnBookNowClickEvent(null);
                                        intent.putExtra("hotel", hotel);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    }
                                });

                                hotel.setBtnBookNowClickEvent(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getActivity(), Hotel_Book.class);
                                        hotel.setBtnMoreInfoClickEvent(null);
                                        hotel.setBtnBookNowClickEvent(null);
                                        intent.putExtra("hotel", hotel);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    }
                                });

                                hotelArrayList.add(hotel);

                                ad.notifyDataSetChanged();
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

    }
}