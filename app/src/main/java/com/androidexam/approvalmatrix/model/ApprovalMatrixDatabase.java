package com.androidexam.approvalmatrix.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.androidexam.approvalmatrix.utilities.ListConverter;

@Database(entities = {ApprovalMatrix.class}, version = 1)
@TypeConverters(ListConverter.class)
public abstract class ApprovalMatrixDatabase extends RoomDatabase {

    public abstract ApprovalMatrixDAO matrixDAO();

    private static ApprovalMatrixDatabase instance;

    public static ApprovalMatrixDatabase getInstance(final Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context, ApprovalMatrixDatabase.class, "ApprovalMatrix")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
