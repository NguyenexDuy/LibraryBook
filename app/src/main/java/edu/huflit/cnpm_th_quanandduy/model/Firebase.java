package edu.huflit.cnpm_th_quanandduy.model;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
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

        mfirestore.collection("Book").get().addOnCompleteListener(task -> {
            if (task.isSuccessful())
            {
                for (QueryDocumentSnapshot document : task.getResult()){
                    Sach sach=new Sach(
                            document.getId(),
                            document.getString("TenSach"),
                            document.getString("IdTacGia"),
                            document.getString("GiaSach"),
                            document.getString("LoaiSach"),
                            document.getString("TacGia"),
                            document.getString("MoTa"),
                            document.getString("HinhSach"),
                            document.getString("MP3")
                    );
                    saches.add(sach);
                }
                callback.onCallback(saches);
            }
        });
    }

    public void GetAllAuthor(FirebaseCallback<TacGia> callback)
    {
        ArrayList<TacGia> tacGias=new ArrayList<>();
        mfirestore=FirebaseFirestore.getInstance();
        mfirestore.collection("User")
                .whereEqualTo("Status","A")
                .get()
                .addOnCompleteListener(task -> {
                    if(task.isSuccessful())
                    {
                        for (QueryDocumentSnapshot queryDocumentSnapshot:task.getResult())
                        {
                            TacGia tacGia=new TacGia(
                                    queryDocumentSnapshot.getString("Ten"),
                                    queryDocumentSnapshot.getString("Gmail"),
                                    queryDocumentSnapshot.getString("PassWord"),
                                    queryDocumentSnapshot.getString("Age"),
                                    queryDocumentSnapshot.getString("Experience"),
                                    queryDocumentSnapshot.getString("Avatar")
                            );
                            tacGias.add(tacGia);
                        }
                        callback.onCallback(tacGias);
                    }
                    else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
    public void GetAllSachOfAdmin( String idTacGia,FirebaseCallback<Sach> callback)
    {
        ArrayList<Sach> saches=new ArrayList<>();

        mfirestore=FirebaseFirestore.getInstance();
        mfirestore.collection("Book")
                .whereEqualTo("IdTacGia",idTacGia)
                .get()
                .addOnCompleteListener(task ->
                {
                    if (task.isSuccessful())
                    {
                        for (QueryDocumentSnapshot documentSnapshot:task.getResult())
                        {
                            Sach sach=new Sach(
                                    documentSnapshot.getId(),
                                    documentSnapshot.getString("TenSach"),
                                    documentSnapshot.getString("IdTacGia"),
                                    documentSnapshot.getString("GiaSach"),
                                    documentSnapshot.getString("LoaiSach"),
                                    documentSnapshot.getString("TacGia"),
                                    documentSnapshot.getString("MoTa"),
                                    documentSnapshot.getString("HinhSach"),
                                    documentSnapshot.getString("MP3")
                            );
                            saches.add(sach);
                        }
                        callback.onCallback(saches);
                    }
                    else {
                        Log.w(TAG, "Error getting documents.", task.getException());
                    }
                });
    }
    public void GetNameUser(TextView name)
    {
        if(mfirebaseUser!=null)
        {
            String id=mfirebaseUser.getUid();
            DocumentReference user=mfirestore.collection("User").document(id);
            user.get().addOnSuccessListener(documentSnapshot -> {
                if(documentSnapshot.exists())
                {
                    name.setText(documentSnapshot.getString("HovaTen"));
                }
                else {
                    name.setText("CC");
                }
            });
        }
    }
}
