package com.rsin.hellohomeo.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Crews.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract MyDao dao();
}
