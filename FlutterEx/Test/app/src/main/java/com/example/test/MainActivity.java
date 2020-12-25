package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_main_enroll;
    private ImageButton image_main_search;
    private EditText edit_main_search;
    private TextView txt_main_eng,txt_main_kor;

    private String result,txt_search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_main_kor = (TextView) findViewById(R.id.txt_main_kor);
        txt_main_eng = (TextView) findViewById(R.id.txt_main_eng);
        edit_main_search = (EditText) findViewById(R.id.edit_main_search);
        image_main_search = (ImageButton) findViewById(R.id.image_main_search);
        btn_main_enroll = (Button)findViewById(R.id.btn_main_enroll);


        image_main_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //이 값을 서버로 전달(파파고)
                txt_search = edit_main_search.getText().toString();

                //위의 search한 값을 바로 입력  ex) apple이라고 치면 apple 작성
                txt_main_eng.setText(txt_search);

                Retrofit retrofit = new Retrofit.Builder().baseUrl("http://59.14.77.197:9000/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetrofitService service = retrofit.create(RetrofitService.class);


                retrofit2.Call<PostResult> call = service.getPosts(txt_search);

                call.enqueue(new retrofit2.Callback<PostResult>() {
                    @Override
                    public void onResponse(retrofit2.Call<PostResult> call, retrofit2.Response<PostResult> response) {
                        if(response.isSuccessful())
                        {
                            PostResult result = response.body();
                            txt_main_kor.setText(result.toString());
                            Log.d("success","성공\n"+result.toString());
                        }
                        else{
                            Log.d("fail","실패\n");

                        }
                    }
                    @Override
                    public void onFailure(retrofit2.Call<PostResult> call, Throwable t) {
                    }
                });

            }
        });


        btn_main_enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}