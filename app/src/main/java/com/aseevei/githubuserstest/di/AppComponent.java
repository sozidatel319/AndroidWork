package com.aseevei.githubuserstest.di;

import android.content.Context;

import com.aseevei.githubuserstest.user.data.UserRepository;
import com.aseevei.githubuserstest.user.di.UserModule;
import dagger.BindsInstance;
import dagger.Component;

@PerApplication
@Component(modules = { NetworkModule.class, UserModule.class, DatabaseModule.class, AboutUserDatabaseModule.class})
public interface AppComponent {

    UserRepository getUserRepository();

    @Component.Builder
    interface Builder{

        AppComponent build();

        @BindsInstance
        Builder context(Context context);
    }
}
