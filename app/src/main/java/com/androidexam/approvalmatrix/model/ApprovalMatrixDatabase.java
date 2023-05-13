package com.androidexam.approvalmatrix.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.androidexam.approvalmatrix.utilities.ListConverter;

@Database(entities = {ApprovalMatrix.class, Approver.class, ApprovalMatrixApproverCrossRef.class}, version = 1)
public abstract class ApprovalMatrixDatabase extends RoomDatabase {

    public abstract ApprovalMatrixDAO matrixDAO();
    public abstract ApproverDAO approverDAO();
    public abstract ApprovalMatrixApproverCrossRefDAO approvalMatrixApproverCrossRefDAO();

    private static ApprovalMatrixDatabase instance;

    public static ApprovalMatrixDatabase getInstance(final Context context)
    {
        if (instance == null)
        {
            synchronized (ApprovalMatrixDatabase.class)
            {
                if (instance == null)
                {
                    instance = Room.databaseBuilder(context, ApprovalMatrixDatabase.class, "app_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return instance;
    }
}
