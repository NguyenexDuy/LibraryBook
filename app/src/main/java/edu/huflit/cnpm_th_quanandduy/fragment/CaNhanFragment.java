package edu.huflit.cnpm_th_quanandduy.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import edu.huflit.cnpm_th_quanandduy.Activity.DangNhapActivity;
import edu.huflit.cnpm_th_quanandduy.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaNhanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaNhanFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaNhanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaNhanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaNhanFragment newInstance(String param1, String param2) {
        CaNhanFragment fragment = new CaNhanFragment();
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
        return inflater.inflate(R.layout.fragment_ca_nhan, container, false);
    }
    TextView tv_nameUser;
    Button btn_dangXuat;
    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_nameUser=view.findViewById(R.id.tv_nameUser);
        btn_dangXuat=view.findViewById(R.id.btn_dangXuat);
        firebaseAuth=FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            String userUid = firebaseUser.getUid();

            // Tạo reference đến tài liệu Firestore tương ứng với tài khoản người dùng hiện tại
            DocumentReference docRef = db.collection("user").document(userUid);

            docRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {
                    // Document tồn tại, bạn có thể lấy dữ liệu từ nó
                    String name = documentSnapshot.getString("HovaTen"); // Thay "name" bằng tên trường dữ liệu bạn muốn lấy
                    tv_nameUser.setText(name); // Hiển thị dữ liệu lấy được vào TextView
                } else {
                    // Document không tồn tại hoặc có lỗi xảy ra
                    tv_nameUser.setText("Document không tồn tại");
                }
            }).addOnFailureListener(e -> {
                // Xử lý lỗi nếu việc lấy dữ liệu thất bại
                tv_nameUser.setText("Lỗi khi lấy dữ liệu từ Firestore");
            });
        } else {
            // Người dùng chưa đăng nhập, xử lý thông báo tương ứng
            tv_nameUser.setText("Chưa đăng nhập");
        }
        btn_dangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                signOutUser();
            }
        });

        }

    private void signOutUser() {
        Intent intent=new Intent(getContext(),DangNhapActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}