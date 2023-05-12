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

    @PrimaryKey(autoGenerate = true)
    private int id;

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

    @ColumnInfo
    @TypeConverters(ListConverter.class)
    private List<List<String>> approvers;

    public ApprovalMatrix(String matrixAlias, String feature, String minimumRange, String maximumRange, String numberOfApproval, List<List<String>> approvers) {
        this.matrixAlias = matrixAlias;
        this.feature = feature;
        this.minimumRange = minimumRange;
        this.maximumRange = maximumRange;
        this.numberOfApproval = numberOfApproval;
        this.approvers = approvers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<List<String>> getApprovers() {
        return approvers;
    }

    public void setApprovers(List<List<String>> approvers) {
        this.approvers = approvers;
    }
}
