package com.example.talky_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class PasswordResetActivity extends AppCompatActivity {
    //파이어베이스 인증 SDK 사용하기
    private FirebaseAuth mAuth; //파이어베이스 인스턴스 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.sendButton) {
                send();
            }
        }
    };

    private void send() { //이메일 주소로 비밀번호 변경 메시지 보내기
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();

        if(email.length() > 0) {
            mAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) { //이메일 전송 성공
                                startToast("이메일 전송 완료");
                                myStartActivity(LoginActivity.class);
                            }
                        }
                    });
        }else { //실패1
            startToast("이메일을 입력해주세요!"); //+
        }
    }

    private void startToast(String msg) { //토스트 메시지를 띄워주는 함수
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }

}