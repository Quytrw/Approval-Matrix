package com.androidexam.approvalmatrix.model;

import java.util.List;

public abstract class ApprovalMatrixDAOImpl implements ApprovalMatrixDAO{

    private ApprovalMatrixDAO approvalMatrixDAO;
    private ApprovalMatrixApproverCrossRefDAO crossRefDAO;

    public ApprovalMatrixDAOImpl(ApprovalMatrixDAO approvalMatrixDAO, ApprovalMatrixApproverCrossRefDAO crossRefDAO) {
        this.approvalMatrixDAO = approvalMatrixDAO;
        this.crossRefDAO = crossRefDAO;
    }

    public List<ApprovalMatrix> getAllApprovalMatrix() {
        return approvalMatrixDAO.getAllApprovalMatrix();
    }

    public ApprovalMatrix getApprovalMatrixById(int id) {
        return approvalMatrixDAO.getApprovalMatrixById(id);
    }

    public void deleteApprovalMatrix(ApprovalMatrix approvalMatrix) {
        crossRefDAO.deleteAllApprovalMatrixApproverCrossRefsByMatrixId(approvalMatrix.getMatrixId());
        approvalMatrixDAO.deleteApprovalMatrix(approvalMatrix);
    }

    public void insertApprovalMatrix(ApprovalMatrix approvalMatrix, List<ApprovalMatrixApproverCrossRef> crossRefs) {
        approvalMatrixDAO.insertApprovalMatrix(approvalMatrix);
        for (ApprovalMatrixApproverCrossRef crossRef : crossRefs) {
            crossRef.setMatrixId(approvalMatrix.getMatrixId());
            crossRefDAO.insertApprovalMatrixApproverCrossRef(crossRef);
        }
    }

    public void updateApprovalMatrix(ApprovalMatrix approvalMatrix, List<ApprovalMatrixApproverCrossRef> crossRefs) {
        approvalMatrixDAO.updateApprovalMatrix(approvalMatrix);
        crossRefDAO.deleteAllApprovalMatrixApproverCrossRefsByMatrixId(approvalMatrix.getMatrixId());
        for (ApprovalMatrixApproverCrossRef crossRef : crossRefs) {
            crossRef.setMatrixId(approvalMatrix.getMatrixId());
            crossRefDAO.insertApprovalMatrixApproverCrossRef(crossRef);
        }
    }

    public List<ApprovalMatrixWithApprover> getApprovalMatrixWithApprover() {
        return approvalMatrixDAO.getApprovalMatrixWithApprover();
    }
}
