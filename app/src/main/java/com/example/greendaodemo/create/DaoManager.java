package com.example.greendaodemo.create;

import android.content.Context;

import com.example.greendaodemo.BuildConfig;

public class DaoManager {
    private Context mContext;
    //创建数据库的名字
    private static final String DB_NAME = "MyGreenDb.db";
    //多线程中要被共享的使用volatile关键字修饰  GreenDao管理类
    private volatile static DaoManager mInstance;
    //它里边实际上是保存数据库的对象
    private static DaoMaster mDaoMaster;

    //创建数据库的工具
    private static DaoMaster.DevOpenHelper mHelper;

    //管理gen里生成的所有的Dao对象里边带有基本的增删改查的方法
    private static DaoSession mDaoSession;

    private DaoManager() {
    }

    /**
     * 单例模式获得操作数据库对象
     *
     * @return
     */
    public static DaoManager getInstance() {
        if (mInstance == null) {
            synchronized (DaoManager.class) {
                if (mInstance == null) {
                    mInstance = new DaoManager();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化上下文创建数据库的时候使用
     * 这是一个普通的单例模式，当创建这个单例的时候，由于需要传入一个Context，所以这个Context的生命周期的长短至关重要：
     *
     * 1、如果此时传入的是 Application 的 Context，因为 Application 的生命周期就是整个应用的生命周期，所以这将没有任何问题。
     *
     * 2、如果此时传入的是 Activity 的 Context，当这个 Context 所对应的 Activity 退出时，由于该 Context 的引用被单例对象所持有，其生命周期等于整个应用程序的生命周期，所以当前 Activity 退出时它的内存并不会被回收，这就造成泄漏了。
     *  https://www.jianshu.com/p/455c97c2f9c0
     */
    public DaoManager init(Context context) {
        //// 使用Application 的context
        this.mContext = context.getApplicationContext();
        return this;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            mHelper = new DaoMaster.DevOpenHelper(mContext, DB_NAME, null);
            mDaoMaster = new DaoMaster(mHelper.getWritableDb());
        }
        return mDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster();
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (mHelper != null) {
            mHelper.close();
            mHelper = null;
        }
    }

    public void closeDaoSession() {
        if (mDaoSession != null) {
            mDaoSession.clear();
            mDaoSession = null;
        }
    }


}
