package com.aseevei.githubuserstest.user.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.aseevei.githubuserstest.user.data.User;
import java.util.List;
import io.reactivex.Single;
import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    Single<List<User>> getAll();

    @Insert(onConflict = REPLACE)
    void insert(Iterable<User> user);

    @Query("DELETE FROM user")
    void deleteAll();
}
