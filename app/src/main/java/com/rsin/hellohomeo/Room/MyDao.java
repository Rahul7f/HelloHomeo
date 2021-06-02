package com.rsin.hellohomeo.Room;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rsin.hellohomeo.CrewModel;

import java.util.List;

@androidx.room.Dao
public interface MyDao {
    @Insert
    public void crewInsert(Crews crews);

    @Delete
    public void crewDelete(Crews crews);

    @Query("Select * from Crews")
    public List<Crews> getcrews();

    @Query("Delete from crews ")
    public void dropTable();
}
