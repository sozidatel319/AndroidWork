package com.aseevei.githubuserstest.user.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aseevei.githubuserstest.user.data.SimpleUser;
import com.aseevei.githubuserstest.user.data.User;
import io.reactivex.Single;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Single<SimpleUser> getAll();

    @Insert(onConflict = REPLACE)
    void insert(SimpleUser user);

    @Query("DELETE FROM user")
    void deleteAll();

}
