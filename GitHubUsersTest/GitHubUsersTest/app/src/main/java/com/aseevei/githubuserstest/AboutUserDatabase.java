package com.aseevei.githubuserstest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.aseevei.githubuserstest.user.data.SimpleUser;
import com.aseevei.githubuserstest.user.database.AboutUserDao;

@Database(entities = SimpleUser.class, version = 1,exportSchema = false)
public abstract class AboutUserDatabase extends RoomDatabase{

        public abstract AboutUserDao aboutUserDao();

}
