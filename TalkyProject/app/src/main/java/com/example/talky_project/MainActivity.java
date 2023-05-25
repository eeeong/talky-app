package com.example.talky_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

public class MainActivity extends AppCompatActivity { //앱 실행시 시작화면

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null) { //기존에 로그인 안 되어있으면
            myStartActivity(JoinActivity.class); //회원가입 화면으로
        }
        else { //로그인 되어있으면
            //회원가입 or 로그인
            myStartActivity(CameraActivity.class);

            for (UserInfo profile : user.getProviderData()) { //사용자 프로필 정보 가져오기
                // Name, email address, and profile photo Url
                String name = profile.getDisplayName(); //사용자 이름
                if(name != null) {
                    if(name.length() == 0) { //사용자 이름이 없을 때(등록 안 되어있을 때) 등록화면 ㄱ
                        myStartActivity(MemberInitActivity.class);
                    }
                }
            }
        }

        //로그인 되어있을 때 메인화면
        findViewById(R.id.logoutButton).setOnClickListener(onClickListener); //로그아웃 버튼
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.logoutButton) { //로그아웃 버튼 눌렸을 때
                FirebaseAuth.getInstance().signOut(); //로그아웃
                myStartActivity(JoinActivity.class);

            }
        }
    };

    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }
}