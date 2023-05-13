package com.androidexam.approvalmatrix.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.List;

@Entity
public class Approver implements Serializable {

    @PrimaryKey
    private int approverId;

    @ColumnInfo
    private String name;

    public Approver(int approverId, String name) {
        this.approverId = approverId;
        this.name = name;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
