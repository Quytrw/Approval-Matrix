package com.androidexam.approvalmatrix.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ApprovalMatrixWithApprover {

    @Embedded public ApprovalMatrix approvalMatrix;
    @Relation(
            parentColumn = "matrixId",
            entityColumn = "approverId",
            associateBy = @Junction(ApprovalMatrixApproverCrossRef.class)
    )
    public List<Approver> approverList;
}
