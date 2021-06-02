package com.rsin.hellohomeo;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rsin.hellohomeo.Room.Crews;
import com.rsin.hellohomeo.Room.MyDatabase;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleview);
        refresh = findViewById(R.id.refresh_bnt);


        MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"CreqwDB")
                .allowMainThreadQueries()
                .build();
        List<Crews> crews =  myDatabase.dao().getcrews();
        if (crews.isEmpty())
        {
            getdata();
        }
        else {
            Adapter adapter = new Adapter(crews,getApplicationContext());
            recyclerView.setAdapter(adapter);
        }
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDatabase.dao().dropTable();
                getdata();
            }
        });

    }

    private void getdata()
    {
        Call<List<CrewModel>> listCall = EndPoints.getCrewService().get_crew_data();
        listCall.enqueue(new Callback<List<CrewModel>>() {
            @Override
            public void onResponse(Call<List<CrewModel>> call, Response<List<CrewModel>> response) {

                List<CrewModel> crewModelList = response.body();

                Log.e("rsin","sucess");

                MyDatabase myDatabase = Room.databaseBuilder(getApplicationContext(),MyDatabase.class,"CreqwDB")
                        .allowMainThreadQueries()
                        .build();

                for (int i = 0;i<crewModelList.size(); i++)
                {
                    Crews crews = new Crews(
                            crewModelList.get(i).getId(),
                            crewModelList.get(i).getName(),
                            crewModelList.get(i).getAgency(),
                            crewModelList.get(i).getWikipedia(),
                            crewModelList.get(i).getStatus(),
                            crewModelList.get(i).getImage());
                    myDatabase.dao().crewInsert(crews);
                }

                List<Crews> crews = myDatabase.dao().getcrews();
                Adapter adapter = new Adapter(crews,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<CrewModel>> call, Throwable t) {
                Log.e("eee",t.getMessage().toString());

            }
        });

    }


}