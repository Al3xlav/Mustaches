package com.example.mustaches.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mustaches.EntityClass.UserModel;

import java.util.List;

@Dao
public interface Daoclass {

    @Insert
    void insertAllData(UserModel model);

    //Select All Data
    @Query("select * from  user")
    List<UserModel> getAllData();

    //DELETE DATA
    @Query("delete from user where `key`= :key")
    void deleteData(int key);

    //Update Data

    @Query("update user SET video= :video ,duration =:duration, tag =:tag where `key`= :key")
    void updateData(String video, String duration, String tag, int key);


}
