package com.aseevei.githubuserstest.user.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.aseevei.githubuserstest.user.data.SimpleUser;

import io.reactivex.Single;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AboutUserDao {

        @Query("SELECT * FROM SimpleUser WHERE name = :username")
        Single<SimpleUser> getUser(String username);

        @Insert(onConflict = REPLACE)
        void insert(SimpleUser user);
    }

