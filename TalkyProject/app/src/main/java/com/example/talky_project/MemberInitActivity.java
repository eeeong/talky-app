package com.example.talky_project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class MemberInitActivity extends AppCompatActivity {
    //파이어베이스 인증 SDK 사용하기
    private FirebaseAuth mAuth; //파이어베이스 인스턴스 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_init);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.checkButton) { //switch -> if !! final 선언 안 됨 문제
                profileUpdate();
            }
        }
    };

    private void profileUpdate() { //회원정보 입력받고 최초 업데이트?
        //FB 사용자 프로필 업데이트
        String name = ((EditText)findViewById(R.id.nameEditText)).getText().toString();

        if(name.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build();

            if(user != null) {
                user.updateProfile(profileUpdates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startToast("회원정보 등록 성공!");
                                    finish(); //등록하면 끝남!
                                }
                            }
                        });
            }
        }else { //실패
            startToast("회원 정보를 입력해주세요!"); //+
        }
    }

    private void startToast(String msg) { //토스트 메시지를 띄워주는 함수
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}