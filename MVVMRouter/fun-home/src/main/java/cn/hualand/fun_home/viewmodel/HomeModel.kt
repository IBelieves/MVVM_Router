package cn.hualand.ui.main.viewmodel

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import cn.hualand.db.DbController
import cn.hualand.entity.RecordEntity
import cn.net.lifecycle.BaseViewModel
import cn.net.util.ActivityUtil

/**
 *11/23/20
 *author:hua-land
 *email:huazhengland@gmail.com
 **/

class HomeModel : BaseViewModel() {
    var name = MutableLiveData<String>()
    var student = MutableLiveData<ArrayList<RecordEntity>>()


    init {
       loadNewData()
    }
    fun loadNewData(){
        //通知订阅方改变了
        for (i in 0..10) {
            var recordEntity = RecordEntity()
            if ((i + 1) % 2 == 0)
                recordEntity.content = "${i + 1}:时光如此美好，你总不能睡着吧？"
            else
                recordEntity.content = "${i + 1}:愿一切美好如期而至~~"
            recordEntity.create_time=System.currentTimeMillis()
            recordEntity.user_pic =
                "https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg"
            recordEntity.user_name = "张钧甯"
            recordEntity.msg_imgs = ArrayList<String>()
            when (i) {
                1 -> {
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                }
                2 -> {
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572582547,3354752277&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                }
                3 -> {
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1203341527,84904295&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572582547,3354752277&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                }
                4 -> {
                    recordEntity.msg_imgs.add("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3474142707,42605441&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1203341527,84904295&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572582547,3354752277&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                }
                5 -> {
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572582547,3354752277&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3474142707,42605441&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1203341527,84904295&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2572582547,3354752277&fm=26&gp=0.jpg")
                    recordEntity.msg_imgs.add("https://dss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=177875800,1358968428&fm=26&gp=0.jpg")
                }

            }
            DbController.getInstance(ActivityUtil.getInstance().app).recordUpdates(recordEntity)
        }
        student.value =  DbController.getInstance(ActivityUtil.getInstance().app).recordLastNew()
    }

}