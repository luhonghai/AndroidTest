package com.luhonghai.android.test.androidtest.data.sqlite.dao;

import android.content.Context;

import com.luhonghai.android.test.androidtest.data.CallData;
import com.luhonghai.android.test.androidtest.data.sqlite.SqliteDatabase;
import com.luhonghai.litedb.LiteBaseDao;
import com.luhonghai.litedb.exception.AnnotationNotFound;
import com.luhonghai.litedb.exception.InvalidAnnotationData;

/**
 * Created by luhonghai on 6/16/16.
 */

public class CallDataDAO extends LiteBaseDao<CallData> {

    public CallDataDAO(Context context) throws AnnotationNotFound, InvalidAnnotationData {
        super(new SqliteDatabase(context), CallData.class);
    }
}
