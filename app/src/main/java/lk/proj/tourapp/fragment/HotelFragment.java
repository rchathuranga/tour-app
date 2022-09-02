package lk.proj.tourapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.adapter.HotelListAdapter;
import lk.proj.tourapp.Hotel_Details;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hotel, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Hotel> hotelArrayList= new ArrayList<>();
        int[] image={R.drawable.hotel_sample,R.drawable.hotel_sample,
                R.drawable.hotel_sample,R.drawable.hotel_sample,R.drawable.hotel_sample,
                R.drawable.hotel_sample,R.drawable.hotel_sample};
        String[] name ={"Test Hotel","Test Hotel","Test Hotel",
                "Test Hotel","Test Hotel","Test Hotel","Test Hotel"};
        String[] emails={"$50   Pandura,Sri Lanka","$50   Pandura,Sri Lanka",
                "$50   Pandura,Sri Lanka","$50   Pandura,Sri Lanka","$50   Pandura,Sri Lanka",
                "$50   Pandura,Sri Lanka","$50   Pandura,Sri Lanka"};
        for(int i=0;i<image.length;i++){
            Hotel hotel=new Hotel(name[i],emails[i],image[i]);
            hotelArrayList.add(hotel);
        }
        ListView viewById = (ListView) view.findViewById(R.id.hotelListView);
        HotelListAdapter ad= new HotelListAdapter(getActivity(),R.layout.hotel_list,hotelArrayList);
        viewById.setAdapter(ad);
        viewById.setClickable(true);
        viewById.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), Hotel_Details.class);
                intent.putExtra("name",name[i]);
                startActivity(intent);
            }
        });
    }
}