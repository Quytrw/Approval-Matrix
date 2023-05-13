package com.androidexam.approvalmatrix.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface ApprovalMatrixDAO {

    @Query("SELECT * FROM ApprovalMatrix")
    List<ApprovalMatrix> getAllApprovalMatrix();

    @Query("SELECT * FROM ApprovalMatrix WHERE matrixId = :id")
    ApprovalMatrix getApprovalMatrixById(int id);

    @Delete
    void deleteApprovalMatrix(ApprovalMatrix approvalMatrix);

    @Insert
    void insertApprovalMatrix(ApprovalMatrix approvalMatrix);

    @Update
    void updateApprovalMatrix(ApprovalMatrix approvalMatrix);

    @Transaction
    @Query("SELECT * FROM ApprovalMatrix")
    public List<ApprovalMatrixWithApprover> getApprovalMatrixWithApprover();

    @Query("SELECT * FROM ApprovalMatrix ORDER BY matrixId DESC LIMIT 1")
    ApprovalMatrix getLastApprovalMatrix();

}
