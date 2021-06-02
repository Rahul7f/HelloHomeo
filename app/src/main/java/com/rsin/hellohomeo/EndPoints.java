package com.rsin.hellohomeo;

import com.rsin.hellohomeo.Room.Crews;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class EndPoints {

    private static final String uri = "https://api.spacexdata.com/v4/";

    public static CrewService notificationService = null;

    public static CrewService getCrewService()
    {
        if (notificationService == null)
        {
            //create new object
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(uri)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            notificationService = retrofit.create(CrewService.class);
        }
        return notificationService;

    }

    public interface CrewService{
        @GET("crew/")
        Call<List<CrewModel>> get_crew_data();
    }


}
