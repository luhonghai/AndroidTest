package com.luhonghai.android.test.androidtest.data.sqlite;

import android.content.Context;

import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.litedb.LiteDatabaseHelper;
import com.luhonghai.litedb.annotation.LiteDatabase;
import com.luhonghai.litedb.exception.AnnotationNotFound;
import com.luhonghai.litedb.exception.InvalidAnnotationData;

/**
 * Created by luhonghai on 6/16/16.
 * LiteDB is my open source project, that is available at Maven Central
 * Quick and easy to create SQLite database on Android project with Annotation
 * See more at https://github.com/luhonghai/LiteDB
 *
 * This class contains information about SQLite database: name, tables and version
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
