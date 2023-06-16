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

public class JoinActivity extends AppCompatActivity {
    //파이어베이스 인증 SDK 사용하기
    private FirebaseAuth mAuth; //파이어베이스 인스턴스 생성

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.joinButton).setOnClickListener(onClickListener);
        findViewById(R.id.gotoLoginButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed() { //메인에서 로그아웃하고 회원가입화면에서 뒤로가기 했을 때 앱 종료해줌 (다시 메인으로 돌아가는 참사 막음)
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.joinButton) { //switch -> if !! final 선언 안 됨 문제
                join();
            }
            if (v.getId() == R.id.gotoLoginButton) { //switch -> if !! final 선언 안 됨 문제
                //startLoginActivity();
                myStartActivity(LoginActivity.class);
            }
        }
    };


    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }

    //private void startLoginActivity() { //로그인 액티비티로 전환 (액티비티는 꼭 매니패스트에 등록되어 있어야함)
    //    Intent intent = new Intent(this, LoginActivity.class);
    //    startActivity(intent);
    //}

    //+닉네임 추가해야됨
    private void join() { //이메일 주소와 비밀번호를 가져와 유효성을 검사한 후 신규 사용자를 만듬
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwdEditText)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.passwdCheckEditText)).getText().toString(); //+

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0) { //+
            if(password.equals(passwordCheck)) {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) { //회원가입 성공
                                    startToast("회원가입 성공 ><");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else { //실패1
                                    if(task.getException() != null)
                                        startToast(task.getException().toString());
                                }
                            }
                        });
            }else { //실패2
                startToast("비밀번호가 일치하지 않습니다!");
            }
        }else { //실패3
            startToast("이메일 또는 비밀번호를 입력해주세요!"); //+
        }
    }

    private void startToast(String msg) { //토스트 메시지를 띄워주는 함수
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}