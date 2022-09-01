package lk.proj.tourapp.service;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import lk.proj.tourapp.adapter.Advisor;

public class FirebaseService {


    public static List<Advisor> getAdvisors(FirebaseFirestore db) {

        ArrayList<Advisor> advisorList = new ArrayList<>();
        db.collection("advisors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                Advisor advisor = new Advisor();
                                advisor.setName(document.getData().get("name").toString());
                                advisorList.add(advisor);
                                System.out.println("User = >" + document.getId());
                                System.out.println("User = >" + document.getData());
                            }
                        } else {
                            Log.w("TAG", "Error getting documents.", task.getException());
                        }
                    }
                });

        return advisorList;
    }
}
