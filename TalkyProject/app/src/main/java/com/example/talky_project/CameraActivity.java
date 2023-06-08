/*
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.talky_project;
//package com.example.android.camera2basic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.support.v7.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.talky_project.Camera2BasicFragment;

public class CameraActivity extends BasicActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        if (null == savedInstanceState) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, Camera2BasicFragment.newInstance())
                    .commit();
        }

        findViewById(R.id.closeButton).setOnClickListener(onClickListener);
        //findViewById(R.id.picture).setOnClickListener(onClickListener); 이거 살려보자공
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.closeButton) { //switch -> if !! final 선언 안 됨 문제
                myStartActivity(MainActivity.class);
            }
            //else if()
        }
    };

    private void myStartActivity(Class c) { //클래스 명으로 액티비티 전환
        Intent intent = new Intent(this, c);
        intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP); //플래그를 지워서 뒤로가기 했을 때 종료 (다시 메인으로 돌아오는 참사를 막기)
        startActivity(intent);
    }

}
