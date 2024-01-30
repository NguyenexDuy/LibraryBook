package edu.huflit.cnpm_th_quanandduy.model;

import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class Firebase {
    FirebaseFirestore mfirestore;
    FirebaseAuth mfirebaseAuth;
    FirebaseUser mfirebaseUser;
    FirebaseStorage mfirebaseStorage;
    StorageReference mstorageRef;
    Context mcontext;

    public Firebase(Context context) {
        mfirestore = FirebaseFirestore.getInstance();
        mfirebaseAuth = FirebaseAuth.getInstance();
        mfirebaseStorage = FirebaseStorage.getInstance();
        mstorageRef = mfirebaseStorage.getReference();
        mfirebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        this.mcontext = context;
    }
    public interface FirebaseCallback<T> {
        void onCallback(ArrayList<T> list);
    }

    public void GetAllSach(FirebaseCallback<Sach> callback) {

        ArrayList<Sach> saches=new ArrayList<>();

        mfirestore=FirebaseFirestore.getInstance();

        mfirestore.collection("sach").get().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                for (QueryDocumentSnapshot document : task.getResult()){
                    Sach sach=new Sach(document.getId(),
                            document.getString("TenSach"),
                            document.getString("GiaSach"),
                            document.getString("LoaiSach"),
                            document.getString("TacGia"),
                            document.getString("MoTa"),
                            document.getString("HinhSach"));
                    saches.add(sach);
                }
                callback.onCallback(saches);
            }
        });
    }
}
