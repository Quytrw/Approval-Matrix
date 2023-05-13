package com.androidexam.approvalmatrix.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ApproverDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertApprover(Approver approver);

    @Query("SELECT * FROM Approver")
    List<Approver> getAllApprovers();

    @Query("SELECT * FROM Approver WHERE approverId = :approverId")
    Approver getApproverById(int approverId);

    @Delete
    void deleteApprover(Approver approver);

}
