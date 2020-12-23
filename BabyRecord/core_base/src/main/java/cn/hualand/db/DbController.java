package cn.hualand.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.github.yuweiguocn.library.greendao.MigrationHelper;


import org.greenrobot.greendao.database.Database;

import java.util.ArrayList;
import java.util.List;

import cn.hualand.dao.DaoMaster;
import cn.hualand.dao.DaoSession;
import cn.hualand.dao.UserInfoEntityDao;
import cn.hualand.entity.UserInfoEntity;

//数据库操作类
public class DbController {
    /**
     * Helper
     */
    private DaoMaster.DevOpenHelper mHelper;//获取Helper对象
    /**
     * 数据库
     */
    private SQLiteDatabase db;
    /**
     * DaoMaster
     */
    private DaoMaster mDaoMaster;
    /**
     * DaoSession
     */
    private DaoSession mDaoSession;
    /**
     * 上下文
     */
    private Context context;


    private static DbController mDbController;
    private UserInfoEntityDao userInfoEntityDao;


    /**
     * 获取单例
     */
    public static DbController getInstance(Context context) {
        if (mDbController == null) {
            synchronized (DbController.class) {
                if (mDbController == null) {
                    mDbController = new DbController(context);
                }
            }
        }
        return mDbController;
    }

    /**
     * 初始化
     * ps:每次新增数据库 记得在 CustomOpenHelper 添加对应实体Dao
     *
     * @param context
     */
    public DbController(Context context) {
        this.context = context;
        //数据库名字
        mHelper = new CustomOpenHelper(context, "easyvm.db");
        //加密密码
        mDaoMaster = new DaoMaster(mHelper.getEncryptedWritableDb("match"));

        mDaoSession = mDaoMaster.newSession();
        userInfoEntityDao = mDaoSession.getUserInfoEntityDao();

    }

    //更新替换多个
    public void userUpdate(List<UserInfoEntity> data) {

        userInfoEntityDao.insertOrReplaceInTx(data);
    }

    //查询某个
    public UserInfoEntity userSearch(long id) {
        List<UserInfoEntity> list = userInfoEntityDao.queryBuilder().where(UserInfoEntityDao.Properties.Id.eq(id)).list();

        return list == null || list.size() == 0 ? new UserInfoEntity() : list.get(0);
    }

    //获取全部
    public List<UserInfoEntity> userAll() {
        List<UserInfoEntity> list = userInfoEntityDao.queryBuilder().list();

        return list == null ? new ArrayList() : list;
    }

    //更新单个
    public void userUpdates(UserInfoEntity data) {

        userInfoEntityDao.insertOrReplace(data);
    }

    //删除单个
    public void uesrDelete(UserInfoEntity data) {
        userInfoEntityDao.delete(data);

    }

    //每次添加一个数据库，这里要记得添加删除，不然用户退出，数据复用???????
    public void deleteAllData() {
        userInfoEntityDao.deleteAll();


    }

    /**
     * 升级策略，升级时保留原数据
     */
    private class CustomOpenHelper extends DaoMaster.DevOpenHelper {

        public CustomOpenHelper(Context context, String name) {
            super(context, name);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            Log.e("greenDAO", "Upgrading schema from version " + oldVersion + " to " + newVersion + " by dropping all tables");
            MigrationHelper.migrate(db, new MigrationHelper.ReCreateAllTableListener() {
                @Override
                public void onCreateAllTables(Database db, boolean ifNotExists) {
                    DaoMaster.createAllTables(db, ifNotExists);
                }

                @Override
                public void onDropAllTables(Database db, boolean ifExists) {
                    DaoMaster.dropAllTables(db, ifExists);
                }
                //每增加一个数据库，这里记得加上
            }, UserInfoEntityDao.class);
        }


    }


}