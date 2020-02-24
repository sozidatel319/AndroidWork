package com.aseevei.githubuserstest;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.aseevei.githubuserstest.user.data.SimpleUser;
import com.aseevei.githubuserstest.user.data.User;
import com.aseevei.githubuserstest.user.database.AboutUserDao;
import com.aseevei.githubuserstest.user.database.UserDao;

@Database(entities = {User.class, SimpleUser.class}, version = 1,exportSchema = false)
public abstract class ApplicationDatabase extends RoomDatabase {

    public abstract UserDao userDao();

    public abstract AboutUserDao aboutUserDao();

}
