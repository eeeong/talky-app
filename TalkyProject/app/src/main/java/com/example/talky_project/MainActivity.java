package com.example.talky_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{ //앱 실행시 시작화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) { //기존에 로그인 안 되어있으면
            myStartActivity(LoginActivity.class); //회원가입 화면으로
        }
        else { //로그인 되어있으면
            //회원가입 or 로그인
            //myStartActivity(CameraActivity.class);
            /*
            for (UserInfo profile : user.getProviderData()) { //사용자 프로필 정보 가져오기
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName(); //사용자 이름
                if(name != null) {
                    if(name.length() == 0) { //사용자 이름이 없을 때(등록 안 되어있을 때) 등록화면 ㄱ
                        myStartActivity(MemberInitActivity.class);
                    }
                }
            }*/
/*
            //회원 정보를 등록했으면 그대로 메인화면 / 아직 등록 안 했으면 회원 정보 화면 ㄱ
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("users").document(user.getUid());
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if(document != null){
                            if (document.exists()) {
                                Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                            } else {
                                Log.d(TAG, "No such document");
                                myStartActivity(MemberInitActivity.class);
                            }
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });*/
        }

        //로그인 되어있을 때 메인화면
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener); //로그아웃 버튼
        findViewById(R.id.memberButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoWritingButton).setOnClickListener(onClickListener); //글 쓰기 버튼
        findViewById(R.id.gotoSearchButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.logoutButton) { //로그아웃 버튼 눌렸을 때
                FirebaseAuth.getInstance().signOut(); //로그아웃
                myStartActivity(LoginActivity.class);

            }
            else if (v.getId() == R.id.memberButton) { //회원정보 버튼 눌렸을 때
                myStartActivity(MemberInitActivity.class); //회원정도 화면으로

            }
            else if (v.getId() == R.id.gotoWritingButton) { //글 쓰기 버튼 눌렸을 때
                myStartActivity(WritingPostActivity.class); //글쓰기 화면으로

            }
            else if (v.getId() == R.id.gotoSearchButton) { //검색 버튼 눌렸을 때
                myStartActivity(SearchPostActivity.class); //검색 화면으로

            }
        }
    };

    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }
}