package com.luhonghai.android.test.androidtest.data;

import com.luhonghai.litedb.annotation.LiteColumn;
import com.luhonghai.litedb.annotation.LiteTable;

import java.util.Date;

/**
 * Created by luhonghai on 6/16/16.
 * LiteDB is my open source project, that is available at Maven Central
 * Quick and easy to create SQLite database on Android project with Annotation
 * See more at https://github.com/luhonghai/LiteDB
 *
 * This class is similar with JDO
 */
@LiteTable
public class CallData extends ResponseData {

    @LiteColumn(isPrimaryKey = true, name = "_id", isAutoincrement = true)
    private long id;

    @LiteColumn
    private String data;

    @LiteColumn
    private Date timestamp;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
