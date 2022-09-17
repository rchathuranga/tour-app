package lk.proj.tourapp.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import lk.proj.tourapp.Advisor_Details;
import lk.proj.tourapp.Hotel_Details;
import lk.proj.tourapp.ProfileActivity;
import lk.proj.tourapp.R;
import lk.proj.tourapp.adapter.Advisor;
import lk.proj.tourapp.adapter.Cab;
import lk.proj.tourapp.adapter.Hotel;
import lk.proj.tourapp.dto.User;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private User user;
    private FirebaseFirestore db;

    TextView lblHomeUsername;
    ImageButton btnHomeAccount;

    ImageView homeAdvisorImg;
    TextView lblHomeAdvisorName;
    TextView lblNoAdvisor;
    TextView lblHomeAdvisorContact;
    MaterialButton btnHomeAdvisorInfo;
    
    ImageView imgHomeHotelImage;
    TextView lblHomeHotelName;
    TextView lblHomeHotelRating;
    TextView lblHomeHotelLocation;
    TextView lblHomeHotelContact;
    MaterialButton btnHomeHotelInfo;

    TextView lblHomeCabName;
    TextView lblHomeCabContactNo;
    ImageView imgHomeCabVehicleType;
    ImageView imgHomeCabImage;

    public HomeFragment() {
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = FirebaseFirestore.getInstance();
        user = (User) requireActivity().getIntent().getSerializableExtra("user");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lblHomeUsername = view.findViewById(R.id.lblHomeUsername);
        btnHomeAccount = view.findViewById(R.id.btnHomeAccount);

        homeAdvisorImg = view.findViewById(R.id.homeAdvisorImg);
        lblHomeAdvisorName = view.findViewById(R.id.lblHomeAdvisorName);
        lblNoAdvisor = view.findViewById(R.id.lblNoAdvisor);
        lblHomeAdvisorContact = view.findViewById(R.id.lblHomeAdvisorContact);
        btnHomeAdvisorInfo = view.findViewById(R.id.btnHomeAdvisorInfo);

        imgHomeHotelImage = view.findViewById(R.id.imgHomeHotelImage);
        lblHomeHotelName = view.findViewById(R.id.lblHomeHotelName);
        lblHomeHotelRating = view.findViewById(R.id.lblHomeHotelRating);
        lblHomeHotelLocation = view.findViewById(R.id.lblHomeHotelLocation);
        lblHomeHotelContact = view.findViewById(R.id.lblHomeHotelContact);
        btnHomeHotelInfo = view.findViewById(R.id.btnHomeHotelInfo);

        lblHomeCabName = view.findViewById(R.id.lblHomeCabName);
        lblHomeCabContactNo = view.findViewById(R.id.lblHomeCabContactNo);
        imgHomeCabVehicleType = view.findViewById(R.id.imgHomeCabVehicleType);
        imgHomeCabImage = view.findViewById(R.id.imgHomeCabImage);

        System.out.println("user "+ user);
        lblHomeUsername.setText(user.getName());
        btnHomeAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });


        loadAdvisorData();
        loadHotelData();
        loadCabData();
    }

    @SuppressLint("SetTextI18n")
    public void loadAdvisorData() {
        if (user.getAdvisorId().isEmpty()) {
            lblNoAdvisor.setText("No Advisor Selected");
            lblNoAdvisor.setVisibility(View.VISIBLE);
            lblHomeAdvisorName.setVisibility(View.INVISIBLE);
            lblHomeAdvisorContact.setVisibility(View.INVISIBLE);
            btnHomeAdvisorInfo.setText("Hire");
        }else {
            db.collection("advisors").document(user.getAdvisorId()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                Advisor advisor = new Advisor();
                                DocumentSnapshot document = task.getResult();
                                advisor.setId(document.getId());
                                advisor.setName(document.getData().get("name").toString());
                                advisor.setContact(document.getData().get("contactNo").toString());
                                advisor.setBadReviews(Integer.parseInt(document.getData().get("badReview").toString()));
                                advisor.setGoodReviews(Integer.parseInt(document.getData().get("goodReview").toString()));
                                advisor.setHiredCount(Integer.parseInt(document.getData().get("hiredCount").toString()));
                                advisor.setImg(document.getData().get("imageUrl").toString());
                                advisor.setEmail(document.getData().get("email").toString());


                                lblHomeAdvisorName.setText(advisor.getName());
                                lblHomeAdvisorContact.setText(advisor.getContact());
                                Picasso.get().load(advisor.getImg()).into(homeAdvisorImg);

                                btnHomeAdvisorInfo.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent = new Intent(getActivity(), Advisor_Details.class);
                                        intent.putExtra("advisorId",advisor.getId());
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                    }
                                });
                            }
                        }
                    });
        }
    }

    public void loadHotelData() {
        if (user.getHotelId().isEmpty()) {
            lblHomeHotelLocation.setText("No Hotel Booked");
            lblHomeHotelLocation.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            lblHomeHotelRating.setVisibility(View.GONE);
            lblHomeHotelContact.setVisibility(View.GONE);
            btnHomeHotelInfo.setText("Book a Hotel");
            btnHomeHotelInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    FragmentManager fm = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.fragmentHome, new HotelFragment());
//                    fragmentTransaction.commit();
                }
            });
        }else {
            db.collection("hotel").document(user.getHotelId()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();

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


                                Picasso.get().load(hotel.getImageUrl()).into(imgHomeHotelImage);
                                imgHomeHotelImage.setScaleType(ImageView.ScaleType.CENTER_CROP);
                                lblHomeHotelName.setText(hotel.getHotelName());
                                lblHomeHotelRating.setText(hotel.getRating());
                                lblHomeHotelLocation.setText(hotel.getLocation());
                                lblHomeHotelLocation.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
                                lblHomeHotelContact.setText(hotel.getContactNo());

                                btnHomeHotelInfo.setOnClickListener(new View.OnClickListener() {
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
                            }
                        }
                    });
        }
    }


    public void loadCabData() {
        if (user.getCabId().isEmpty()) {
            lblHomeCabName.setText("");
//            lblHomeHotelLocation.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            lblHomeCabContactNo.setText("No Cab Hired");
            imgHomeCabVehicleType.setVisibility(View.GONE);
//            btnHomeHotelInfo.setText("Book a Hotel");
//            btnHomeHotelInfo.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    FragmentManager fm = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
//                    fragmentTransaction.replace(R.id.fragmentHome, new HotelFragment());
//                    fragmentTransaction.commit();
//                }
//            });
        }else {
            db.collection("drivers").document(user.getCabId()).get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();

                                Cab cab = new Cab();

                                cab.setDriverId(document.getId());
                                cab.setDriverName(document.getData().get("driverName").toString());
                                cab.setVehicleType(document.getData().get("vehicleType").toString());
                                cab.setContactNo(document.getData().get("contactNo").toString());
                                cab.setEmail(document.getData().get("email").toString());
                                cab.setImageUrl(document.getData().get("imageUrl").toString());


                                Picasso.get().load(cab.getImageUrl()).into(imgHomeCabImage);
                                lblHomeCabName.setText(cab.getDriverName());
                                lblHomeCabContactNo.setText(cab.getContactNo());
                            }
                        }
                    });
        }
    }
}