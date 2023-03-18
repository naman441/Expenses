package com.example.expenses;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AccountsDao extends BaseDao<Accounts>{

    @Query("select * from accounts where accountName = :name")
    Accounts getAccountByName(String name);

    @Query("select * from accounts")
    List<Accounts> getAllAccounts();

}
