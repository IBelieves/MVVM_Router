package cn.hualand.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class UserInfoEntity {
    @Unique //唯一属性
    public  Long id;
    //数据库忽略生成字段
    @Transient
    public  String alise;
    @Id(autoincrement = true)
    public  Long _id;
    @Generated(hash = 157046415)
    public UserInfoEntity(Long id, Long _id) {
        this.id = id;
        this._id = _id;
    }
    @Generated(hash = 2042969639)
    public UserInfoEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }

}
