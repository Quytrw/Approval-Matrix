package com.androidexam.approvalmatrix.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ApprovalMatrixDAO {

    @Query("SELECT * FROM ApprovalMatrix")
    LiveData<List<ApprovalMatrix>> getAllMatrix();

    @Insert
    void insertMatrix(ApprovalMatrix approvalMatrix);

    @Update
    void updateMatrix(ApprovalMatrix approvalMatrix);

    @Delete
    void deleteMatrix(ApprovalMatrix approvalMatrix);
}
