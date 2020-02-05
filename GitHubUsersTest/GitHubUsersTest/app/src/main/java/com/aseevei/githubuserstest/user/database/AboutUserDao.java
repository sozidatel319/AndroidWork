package com.aseevei.githubuserstest.user.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.aseevei.githubuserstest.user.data.SimpleUser;
import io.reactivex.Single;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface AboutUserDao {

        @Query("SELECT * FROM user")
        Single<SimpleUser> getAll();

        @Insert(onConflict = REPLACE)
        void insert(SimpleUser user);

        @Query("DELETE FROM user")
        void deleteAll();

        @Update
        void update(SimpleUser user);

    }

