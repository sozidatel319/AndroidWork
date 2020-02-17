package com.aseevei.githubuserstest.di;

import android.content.Context;
import androidx.room.Room;
import com.aseevei.githubuserstest.AboutUserDatabase;
import com.aseevei.githubuserstest.user.database.AboutUserDao;
import dagger.Module;
import dagger.Provides;

@Module
public class AboutUserDatabaseModule {


    @Provides
    static AboutUserDao providesAboutUserDao(AboutUserDatabase database){
        return database.aboutUserDao();
    }

    @Provides
    static AboutUserDatabase providesApplicationDatabase(Context context) {
        return Room.databaseBuilder(context, AboutUserDatabase.class, "database").build();
    }
}
