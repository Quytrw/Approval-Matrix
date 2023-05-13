package com.androidexam.approvalmatrix.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"matrixId", "approverId"})
public class ApprovalMatrixApproverCrossRef {

    private int matrixId;

    private int approverId;

    private int orderIndex;

    public ApprovalMatrixApproverCrossRef(int matrixId, int approverId, int orderIndex) {
        this.matrixId = matrixId;
        this.approverId = approverId;
        this.orderIndex = orderIndex;
    }

    public int getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(int matrixId) {
        this.matrixId = matrixId;
    }

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
