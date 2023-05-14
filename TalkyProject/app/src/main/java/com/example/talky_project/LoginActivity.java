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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    //파이어베이스 인증 SDK 사용하기
    private FirebaseAuth mAuth; //파이어베이스 인스턴스 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPasswordResetbutton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.loginButton) { //switch -> if !! final 선언 안 됨 문제
                login();
            }
            if (v.getId() == R.id.gotoPasswordResetbutton) { //
                myStartActivity(PasswordResetActivity.class);
            }
        }
    };

    private void login() { //이메일 주소와 비밀번호를 가져와 유효성을 검사한 후 신규 사용자를 만듬
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwdEditText)).getText().toString();

        if(email.length() > 0 && password.length() > 0) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) { //로그인 성공
                                startToast("로그인 성공 ><");
                                FirebaseUser user = mAuth.getCurrentUser();
                                myStartActivity(MainActivity.class); //메인화면으로 이동
                            } else { //실패1
                                if(task.getException() != null)
                                    startToast(task.getException().toString());
                            }
                        }
                    });
        }else { //실패2
            startToast("이메일 또는 비밀번호를 입력해주세요!"); //+
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