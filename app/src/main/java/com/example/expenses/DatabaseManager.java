package com.example.expenses;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SupportFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * Database manager class to handle Room database functioning.
 * Implements Singleton behaviour to provide INSTANCE of db
 *
 */
@Database(entities = {Accounts.class}, version = 1)
@TypeConverters({DateConverter.class,  BooleanConverter.class})
public abstract class DatabaseManager extends RoomDatabase {

    private static volatile DatabaseManager INSTANCE;
    private static final int NUMBER_OF_THREADS = 2;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static final String data = "passPhrase";

    public abstract AccountsDao getAccountsDao();

    public static DatabaseManager getManagerDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (DatabaseManager.class){
                if(INSTANCE == null){
                    final byte[] passphrase = SQLiteDatabase.getBytes(data.toCharArray());
                    final SupportFactory factory = new SupportFactory(passphrase);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseManager.class, "emanager_db").openHelperFactory(factory).build();
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static DatabaseManager getTestDatabase(final Context context) {
        final byte[] passphrase = SQLiteDatabase.getBytes(data.toCharArray());
        final SupportFactory factory = new SupportFactory(passphrase);
        return Room.inMemoryDatabaseBuilder(context, DatabaseManager.class).openHelperFactory(factory).build();
    }
}
