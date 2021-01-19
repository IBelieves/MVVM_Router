package cn.hualand.entity;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

import java.util.ArrayList;

import cn.hualand.util.StringListConverter;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class RecordEntity {
    @Unique
    @Id(autoincrement = true)
    public Long id;
    public Long create_time;
    public String img_url;
    public String user_name;
    public String user_pic;
    public String content;
    @Convert(converter = StringListConverter.class,columnType = String.class)
    public ArrayList<String> msg_imgs;
    @Generated(hash = 409083042)
    public RecordEntity(Long id, Long create_time, String img_url, String user_name,
            String user_pic, String content, ArrayList<String> msg_imgs) {
        this.id = id;
        this.create_time = create_time;
        this.img_url = img_url;
        this.user_name = user_name;
        this.user_pic = user_pic;
        this.content = content;
        this.msg_imgs = msg_imgs;
    }
    @Generated(hash = 867663846)
    public RecordEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImg_url() {
        return this.img_url;
    }
    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
    public String getUser_name() {
        return this.user_name;
    }
    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
    public String getUser_pic() {
        return this.user_pic;
    }
    public void setUser_pic(String user_pic) {
        this.user_pic = user_pic;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public ArrayList<String> getMsg_imgs() {
        return this.msg_imgs;
    }
    public void setMsg_imgs(ArrayList<String> msg_imgs) {
        this.msg_imgs = msg_imgs;
    }
    public Long getCreate_time() {
        return this.create_time;
    }
    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

}
