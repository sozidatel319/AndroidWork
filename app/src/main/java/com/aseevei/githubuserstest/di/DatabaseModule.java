package com.aseevei.githubuserstest.di;

import android.content.Context;
import androidx.room.Room;
import com.aseevei.githubuserstest.ApplicationDatabase;
import com.aseevei.githubuserstest.user.database.UserDao;
import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    @Provides
    static UserDao providesUserDao(ApplicationDatabase database) {
        return database.userDao();
    }

    @Provides
    static ApplicationDatabase providesApplicationDatabase(Context context) {
        return Room.databaseBuilder(context, ApplicationDatabase.class, "database").build();
    }
}
