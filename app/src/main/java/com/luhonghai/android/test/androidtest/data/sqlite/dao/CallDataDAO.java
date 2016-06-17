package com.luhonghai.android.test.androidtest.data.sqlite.dao;

import android.content.Context;

import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.android.test.androidtest.data.sqlite.SqliteDatabase;
import com.luhonghai.litedb.LiteBaseDao;
import com.luhonghai.litedb.exception.AnnotationNotFound;
import com.luhonghai.litedb.exception.InvalidAnnotationData;

/**
 * Created by luhonghai on 6/16/16.
 * LiteDB is my open source project, that is available at Maven Central
 * Quick and easy to create SQLite database on Android project with Annotation
 * See more at https://github.com/luhonghai/LiteDB
 *
 * This class have all useful method from LiteBaseDao to: insert,update, delete and query from SQLite database
 */

public class CallDataDAO extends LiteBaseDao<CallData> {

    public CallDataDAO(Context context) throws AnnotationNotFound, InvalidAnnotationData {
        super(new SqliteDatabase(context), CallData.class);
    }
}
