package com.androidexam.approvalmatrix.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.androidexam.approvalmatrix.utilities.ListConverter;

import java.io.Serializable;
import java.util.List;

@Entity
public class ApprovalMatrix implements Serializable {

    @PrimaryKey
    private int matrixId;

    @ColumnInfo
    private String matrixAlias;

    @ColumnInfo
    private String feature;

    @ColumnInfo
    private String minimumRange;

    @ColumnInfo
    private String maximumRange;

    @ColumnInfo
    private String numberOfApproval;

    public ApprovalMatrix(int matrixId, String matrixAlias, String feature, String minimumRange, String maximumRange, String numberOfApproval) {
        this.matrixId = matrixId;
        this.matrixAlias = matrixAlias;
        this.feature = feature;
        this.minimumRange = minimumRange;
        this.maximumRange = maximumRange;
        this.numberOfApproval = numberOfApproval;
    }

    public int getMatrixId() {
        return matrixId;
    }

    public void setMatrixId(int matrixId) {
        this.matrixId = matrixId;
    }

    public String getMatrixAlias() {
        return matrixAlias;
    }

    public void setMatrixAlias(String matrixAlias) {
        this.matrixAlias = matrixAlias;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getMinimumRange() {
        return minimumRange;
    }

    public void setMinimumRange(String minimumRange) {
        this.minimumRange = minimumRange;
    }

    public String getMaximumRange() {
        return maximumRange;
    }

    public void setMaximumRange(String maximumRange) {
        this.maximumRange = maximumRange;
    }

    public String getNumberOfApproval() {
        return numberOfApproval;
    }

    public void setNumberOfApproval(String numberOfApproval) {
        this.numberOfApproval = numberOfApproval;
    }
}
