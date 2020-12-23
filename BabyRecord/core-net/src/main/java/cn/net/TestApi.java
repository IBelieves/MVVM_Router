package cn.net;

import cn.drivingeasy.net.RetrofitFileHelper;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.reactivex.annotations.NonNull;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestApi {
    static TestApi testApi;

    public static TestApi getInstance() {
        if (testApi == null)
            testApi = new TestApi();
        return testApi;
    }

    public void login(String name, String psw) {
        // /**
        //  * login_name
        //  * pwd
        //  */
        // RetrofitHelper.apiRun(RetrofitHelper.getInstance().getUserApi().login(new LoginBean(name, ,psw)), loginBean -> {
        // }, error -> {
        // });

    }

    public void upload(String path) {
        //构建要上传的文件
        File file = new File(path);
//        RequestBody requestFile =
//                RequestBody.create(MediaType.parse("application/otcet-stream"), file);
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        MultipartBody.Part body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile);


        RequestBody path_body =
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), path);


        RetrofitFileHelper.Companion.getInstance().Companion.apiRun(RetrofitFileHelper.Companion.getInstance().
                getFileApi().upLoad(path_body, body), rsp -> {
        }, error -> {
        });
    }

    public void downLoad(String url, final String path) {
        Call<ResponseBody> call = RetrofitFileHelper.Companion.getInstance().getFileApi().download(url);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull final Response<ResponseBody> response) {
                try {

                    //将Response写入到从磁盘中，详见下面分析
                    //注意，这个方法是运行在子线程中的
                    //从response获取输入流以及总大小
                    InputStream inputStream = response.body().byteStream();
                    if (inputStream != null) {
                        writeFileFromIS(new File(path), inputStream, response.body().contentLength());
                        return;
                    }
                    if (response.body().toString().contains("code:-1")) {
//                        listener.onError(response.message());
                        return;
                    }
                    if (response.body().toString().contains("status:404") || response.body().toString().contains("status:500")) {
//                        listener.onError(response.message());
                        return;
                    }
                    if (response.body().toString().contains("code:501") || response.body().toString().contains("code:502")) {
//                        ToastUtil.getInstance().showLogOutDialog(context);
                        return;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
//                    listener.onError("网络错误");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable throwable) {
//                if (pd != null) pd.dismiss();
//                listener.onError("网络错误");
            }
        });
    }

    private static int sBufferSize = 8192;

    //将输入流写入文件
    private void writeFileFromIS(File file, InputStream is, long totalLength) {


        //创建文件
        if (!file.exists()) {
            if (!file.getParentFile().exists())
                file.getParentFile().mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
//                downloadListener.onFail("createNewFile IOException");
            }
        }

        OutputStream os = null;
        long currentLength = 0;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            byte data[] = new byte[sBufferSize];
            int len;
            while ((len = is.read(data, 0, sBufferSize)) != -1) {
                os.write(data, 0, len);
                currentLength += len;
                //计算当前下载进度
//                downloadListener.onProgress((int) (100 * currentLength / totalLength));
            }
            //下载完成，并返回保存的文件路径
//            listener.onWorkDownLoadSuccess(file.getPath());

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
