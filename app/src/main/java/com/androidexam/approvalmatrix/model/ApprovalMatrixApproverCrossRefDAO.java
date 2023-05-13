package com.androidexam.approvalmatrix.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ApprovalMatrixApproverCrossRefDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertApprovalMatrixApproverCrossRef(ApprovalMatrixApproverCrossRef crossRef);

    @Query("SELECT * FROM ApprovalMatrixApproverCrossRef")
    List<ApprovalMatrixApproverCrossRef> getAllApprovalMatrixApproverCrossRefs();

    @Query("SELECT * FROM ApprovalMatrixApproverCrossRef WHERE matrixId = :matrixId")
    List<ApprovalMatrixApproverCrossRef> getApprovalMatrixApproverCrossRefListByMatrixId(int matrixId);

    @Query("SELECT * FROM ApprovalMatrixApproverCrossRef WHERE matrixId = :matrixId AND approverId = :approverId")
    ApprovalMatrixApproverCrossRef getApprovalMatrixApproverCrossRefByIds(int matrixId, int approverId);

    @Delete
    void deleteApprovalMatrixApproverCrossRef(ApprovalMatrixApproverCrossRef crossRef);

    @Query("DELETE FROM ApprovalMatrixApproverCrossRef WHERE matrixId = :matrixId")
    void deleteAllApprovalMatrixApproverCrossRefsByMatrixId(int matrixId);

}
