package com.luhonghai.android.test.androidtest.data.sqlite;

import android.content.Context;

import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.litedb.LiteDatabaseHelper;
import com.luhonghai.litedb.annotation.LiteDatabase;
import com.luhonghai.litedb.exception.AnnotationNotFound;
import com.luhonghai.litedb.exception.InvalidAnnotationData;

/**
 * Created by luhonghai on 6/16/16.
 */
@LiteDatabase(tables = {CallData.class})
public class SqliteDatabase extends LiteDatabaseHelper {

    public SqliteDatabase(Context context) throws AnnotationNotFound, InvalidAnnotationData {
        super(context);
    }

    public SqliteDatabase(Context context, DatabaseListener databaseListener) throws AnnotationNotFound, InvalidAnnotationData {
        super(context, databaseListener);
    }
}
