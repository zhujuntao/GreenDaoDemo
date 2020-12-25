package com.example.greendaodemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.greendaodemo.create.DaoManager;
import com.example.greendaodemo.create.DaoSession;
import com.example.greendaodemo.create.StudentBeanTableDao;
import com.example.greendaodemo.table.StudentBeanTable;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    /*
     * https://www.cnblogs.com/qiangge-python/p/12308220.html
     *
     * https://www.jianshu.com/p/967d402d411d
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //增加
        StudentBeanTable studentBean = new StudentBeanTable();
        studentBean.setSId("0");
        studentBean.setName("张三");
        studentBean.setAge(18);
        studentBean.setGender("男");
        StudentBeanTable studentBean1 = new StudentBeanTable();
        studentBean1.setSId("1");
        studentBean1.setName("李四");
        studentBean1.setAge(20);
        studentBean1.setGender("女");
        inserOrReplace(studentBean);
        inserOrReplace(studentBean1);

        //查询
        quaeryData();

        //修改
        updateData();

    }

    /*
    * 1.插入
    *  insert(User entity)： 插入一条记录, 当指定主键在表中存在时会发生异常
    *  insertOrReplace(User entity) ： 当指定主键在表中存在时会覆盖数据,有该数据时则更新，推荐同步数据库时使用该方法
    *  save(User entity):　save 类似于insertOrReplace，区别在于save会判断传入对象的key，有key的对象执行更新，无key的执行插入。当对象有key但并不在数据库时会执行失败.适用于保存本地列表。
    *
        insertInTx(T... entities)：使用事务在数据库中插入给定的实体。
        insertInTx(Iterable<T> entities)：使用事务操作，将给定的实体集合插入数据库。
        insertInTx(Iterable<T> entities, boolean setPrimaryKey)：使用事务操作，将给定的实体集合插入数据库，并设置是否设定主键 。

        insertOrReplaceInTx(T... entities)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖
        insertOrReplaceInTx(Iterable<T> entities)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖 。
        insertOrReplaceInTx(Iterable<T> entities, boolean setPrimaryKey)：使用事务操作，将给定的实体插入数据库，若此实体类存在，则覆盖，并设置是否设定主键 。
        insertWithoutSettingPk(T entity)：将给定的实体插入数据库,但不设定主键。

        // 新增数据插入相关API
        save(T entity)：将给定的实体插入数据库
        saveInTx(Iterable<T> entities)：将给定的实体集合插入数据库
        saveInTx(T... entities)：使用事务操作，将给定的实体插入数据库
    * */
    public void inserOrReplace(StudentBeanTable student) {
        MyApplication.getDaoSession().insertOrReplace(student);
    }


  /*
    //查询全部
    List<User> list = mUserDao.queryBuilder().list();

    //查询 name等于xyh8的数据
    List<User> list= mUserDao.queryBuilder().where(UserDao.Properties.Name.eq("xyh8")).list();

    //查询 name不等于xyh8的数据
    List<User> list= mUserDao.queryBuilder().where(UserDao.Properties.Name.notEq("xyh8")).list();

    //like  模糊查询
//查询 name以xyh3开头的数据
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Name.like("xyh3%")).list();

    //between 区间查询 年龄在20到30之间
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Age.between(20,30)).list();

    //gt: greater than 半开区间查询，年龄大于18
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Age.gt(18)).list();

    //ge: greater equal 半封闭区间查询，年龄大于或者等于18
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Age.ge(18)).list();

    //lt: less than 半开区间查询，年龄小于18
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Age.lt(18)).list();

    //le: less equal 半封闭区间查询，年龄小于或者等于18
    List<User> list = mUserDao.queryBuilder().where(UserDao.Properties.Age.le(18)).list();

     //排序

    //名字以xyh8开头，年龄升序排序
    List<User> list = mUserDao.queryBuilder()
            .where(UserDao.Properties.Name.like("xyh8%"))
            .orderAsc(UserDao.Properties.Age)
            .list();

    //名字以xyh8开头，年龄降序排序
    List<User> list = mUserDao.queryBuilder()
            .where(UserDao.Properties.Name.like("xyh8%"))
            .orderDesc(UserDao.Properties.Age)
            .list();
*/

    /* DaoSession
　　  管理特定模式的所有可用Dao对象，可以使用其中一个getter方法获取，
     DaoSession还为实体提供了一些通用的持久性方法如插入、加载、更新、刷新、删除。最后Daosession对象也跟踪一个身份范围
    */
    public void quaeryData() {
       /* List<StudentBean> students = studentDao.loadAll();
        StudentBean students2 = studentDao.load(1L);
        StudentBean students3 = studentDao.loadByRowId(0L);*/
        StudentBeanTableDao studentBeanTableDao = MyApplication.getDaoSession().getStudentBeanTableDao();
        //List<StudentBeanTable> students = studentBeanTableDao.loadAll();
        List<StudentBeanTable> students = studentBeanTableDao.queryBuilder().where(StudentBeanTableDao.Properties.Name.eq("张三")).list();
//        List<StudentBeanTable> students = (List<StudentBeanTable>) studentBeanTableDao.queryBuilder().where(StudentBeanTableDao.Properties.Name.eq("张三")).build().unique();
        Log.d("===quaery===", "=====students====" + students.size());
        Log.d("===quaery===", "=====students====" + students.get(0).getName());
        Log.d("====quaery==", "=====students====" + students.get(0).getAge());
        Log.d("==quaery====", "=====students====" + students.get(0).getGender());

    }

    /*
    * 更新
    *
    * update(T entity) ：更新给定的实体
      updateInTx(Iterable<T> entities) ：使用事务操作，更新给定的实体
      updateInTx(T... entities)：使用事务操作，更新给定的实体
    * */
    private void updateData(){
        StudentBeanTableDao studentBeanTableDao = MyApplication.getDaoSession().getStudentBeanTableDao();
        List<StudentBeanTable> students = studentBeanTableDao.queryBuilder().where(StudentBeanTableDao.Properties.Name.eq("张三")).list();
//        studentDao.update(student);
//        studentDao.updateInTx(student);
        StudentBeanTable studentBeanTable=students.get(0);
        studentBeanTable.setAge(20);
        studentBeanTable.setName("张三三");
        studentBeanTableDao.update(studentBeanTable);
//        List<StudentBeanTable> studentsUp = studentBeanTableDao.queryBuilder().where(StudentBeanTableDao.Properties.Name.eq("张三")).list();
        List<StudentBeanTable> studentsUp = studentBeanTableDao.loadAll();
        Log.d("==update=quaery===", "=====students====" + studentsUp.size());
        Log.d("=update==quaery===", "=====students====" + studentsUp.get(0).getName());
        Log.d("=update===quaery==", "=====students====" + studentsUp.get(0).getAge());
        Log.d("=update=quaery====", "=====students====" + studentsUp.get(0).getGender());

    }
  /*  //删除全部
    mUserDao.deleteAll();

    delete(T entity)：从数据库中删除给定的实体

    deleteByKey(K key)：从数据库中删除给定Key所对应的实体

    deleteInTx(T... entities)：使用事务操作删除数据库中给定的实体

    deleteInTx(<T> entities)：使用事务操作删除数据库中给定实体集合中的实体

    deleteByKeyInTx(K... keys)：使用事务操作删除数据库中给定的所有key所对应的实体

    deleteByKeyInTx(Iterable<K> keys)：使用事务操作删除数据库中给定的所有key所对应的实体*/
    private  void   deleteData(){
        //StudentBeanTableDao studentBeanTableDao = MyApplication.getDaoSession().getStudentBeanTableDao();
        StudentBeanTableDao studentBeanTableDao= DaoManager.getInstance().init(this).getDaoSession().getStudentBeanTableDao();
        studentBeanTableDao.deleteAll();
    }


}