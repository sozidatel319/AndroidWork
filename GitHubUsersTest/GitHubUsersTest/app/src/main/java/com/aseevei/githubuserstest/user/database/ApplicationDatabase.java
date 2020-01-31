package com.aseevei.githubuserstest.user.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aseevei.githubuserstest.user.data.SimpleUser;

@Database(entities = SimpleUser.class, version = 1,exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract UserDao userDao();

}
