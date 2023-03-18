package com.example.expenses;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RunWith(AndroidJUnit4.class)
public class AccountsDaoTest {

    private AccountsDao dao;
    private DatabaseManager db;

    private Accounts accounts;

    @Before
    public void createDb(){
        Context context = ApplicationProvider.getApplicationContext();
        db = DatabaseManager.getTestDatabase(context);
        dao = db.getAccountsDao();

        accounts = createAccount("TestAccount", "Test owner", new Date(), true, 110.0);
    }

    @After
    public void closeDb() throws IOException {
        db.close();
    }

    @Test
    public void testInsert() {
        dao.insert(accounts);

        Accounts accounts1 = dao.getAccountByName("TestAccount");
        Assertions.assertThat(accounts1).as("Account should be retrieved").isNotNull();
        Assertions.assertThat(accounts1.getOwnerName()).as("Owner should match").isEqualToIgnoringCase("Test owner");
    }

    @Test
    public void testInsertList() throws ExecutionException, InterruptedException {
        final List<Accounts> list = new ArrayList<>();

        list.add(accounts);
        list.add(createAccount("TestAccount2", "Test owner 2", new Date(), true, 200.0));

        DatabaseManager.databaseWriteExecutor.submit(()->{
            dao.insert(list);
        }).get();

        List<Accounts> list1 = dao.getAllAccounts();
        Assertions.assertThat(list1).as("Should have 2 accounts").hasSize(2);
    }

    @Test
    public void testUpdate(){
        dao.insert(accounts);

        accounts = dao.getAccountByName("TestAccount");
        Assertions.assertThat(accounts.getCurrentBalance()).as("Owner should match").isEqualTo(110.0);

        accounts.setCurrentBalance(500.0);
        dao.update(accounts);

        accounts = dao.getAccountByName(accounts.getAccountName());
        Assertions.assertThat(accounts.getCurrentBalance()).as("Balance should update").isEqualTo(500.0);
    }

    @Test
    public void testDelete(){
        dao.insert(accounts);

        accounts = dao.getAccountByName("TestAccount");
        Assertions.assertThat(accounts.getOwnerName()).as("Owner should match").isEqualToIgnoringCase("Test owner");

        dao.delete(accounts);

        accounts = dao.getAccountByName("TestAccount");
        Assertions.assertThat(accounts).as("Value not found in Db").isNull();
    }

    private Accounts createAccount(String name, String owner, Date date, boolean active, double balance) {
        return new AccountsBuilder().withName(name)
                .withOwner(owner)
                .withCreatedOn(date)
                .isActive(active)
                .withBalance(balance).build();
    }
}
