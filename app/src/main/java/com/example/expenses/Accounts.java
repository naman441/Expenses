package com.example.expenses;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(indices = {@Index(value = {"accountName"}, unique = true)})
public class Accounts {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @NotNull
    private String accountName;

    @NotNull
    private String ownerName;

    @NotNull
    private Date createdOn;

    private boolean active;

    private double currentBalance;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotNull
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(@NotNull String accountName) {
        this.accountName = accountName;
    }

    @NotNull
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(@NotNull String ownerName) {
        this.ownerName = ownerName;
    }

    @NotNull
    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(@NotNull Date createdOn) {
        this.createdOn = createdOn;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
