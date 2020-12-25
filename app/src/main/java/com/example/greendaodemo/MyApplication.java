package com.example.greendaodemo;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendaodemo.create.DaoMaster;
import com.example.greendaodemo.create.DaoSession;

public class MyApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initGreenDao();
    }

    private void initGreenDao() {
        //先通过DaoMaster的DevOpenHelper方法来创建一个数据库
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(this, "student.db",null);
        //获得一个db
        SQLiteDatabase db = openHelper.getWritableDatabase();
        //新建一个DaoMaster，获得master
        DaoMaster daoMaster = new DaoMaster(db);
        //通过master new一个Daosession
        daoSession = daoMaster.newSession();


    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
