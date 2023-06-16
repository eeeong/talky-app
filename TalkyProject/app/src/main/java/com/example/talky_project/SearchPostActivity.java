package com.example.talky_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchPostActivity extends AppCompatActivity {
    //private  static final String TAG = "WritePostActivity";
    //private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        findViewById(R.id.searchButton).setOnClickListener(onClickListener);
        findViewById(R.id.backButton3).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.searchButton) { //검색 버튼 눌렸을 때
                //검색 로직 추가
                myStartActivity(MainActivity.class);
            }
            else if (v.getId() == R.id.backButton3) { //뒤로가기 버튼 눌렸을 때
                myStartActivity(MainActivity.class);
            }
        }
    };

    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }

    private void startToast(String msg) { //토스트 메시지를 띄워주는 함수
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
