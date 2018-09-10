package com.openxu.net;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.openxu.ds.R;
import com.openxu.oxlib.adapter.CommandRecyclerAdapter;
import com.openxu.oxlib.adapter.ViewHolder;
import com.openxu.oxlib.base.BaseActivity;
import com.openxu.oxlib.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

/**
 * autour : openXu
 * date : 2018/7/11 17:00
 * className : NetDemoActivity
 * version : 1.0
 * description :
 */
public class NetDemoActivity extends BaseActivity {

    RecyclerView recyclerView;
    TextView tv_result;
    ScrollView scrollView;
    private List<String> itemList;


    @Override
    protected int getLayoutID() {
        return R.layout.activity_test;
    }
    @Override
    protected void initView() {
        titleLayout.setTextcenter("okhttp").show();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        scrollView = findViewById(R.id.scrollView);
        tv_result = findViewById(R.id.tv_result);
        itemList = new ArrayList<>();
        itemList.add("get同步");
        itemList.add("get异步");
        itemList.add("post同步");
        itemList.add("post异步");

        CommandRecyclerAdapter adapter = new CommandRecyclerAdapter<String>(this,
                R.layout.item_recycler, itemList){
            @Override
            public void convert(ViewHolder holder, String str) {
                holder.setText(R.id.tv_name, str);
            }

            @Override
            public void onItemClick(String data, int position) {
                switch (position){
                    case 0:
                        getDataSyn();
                        break;
                    case 1:
                        getDataAsyn();
                        break;
                    case 2:
                        postDataWithParams();
                        break;
                    case 3:
                        postDataWithParams();
                        break;
                    case 4:
                        getDataAsyn();
                        break;
                    case 5:
                        getDataAsyn();
                        break;
                    case 6:
                        getDataAsyn();
                        break;

                }
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.setData(itemList);
    }
    @Override
    protected void initData() {
    }

    /**切换主线程展示结果*/
    private void appendResult(String result){
        runOnUiThread(() -> {
            tv_result.setText(tv_result.getText().toString()+"\n"+result+"\n");
            //设置默认滚动到底部
            scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_DOWN));
        });
    }

    /**1、同步get请求*/
    private void getDataSyn(){
        new Thread("子线程咯"){
            @Override
            public void run() {
                super.run();
                OkHttpClient client = new OkHttpClient();
                //创建一个Request对象，该对象中包含本次请求的一些重要数据（url、参数、请求方式等）
                Request request = new Request.Builder()
                        .url(getUrl(false))
                        .build();
                //创建一个请求对象RealCall
                Call call = client.newCall(request);
                LogUtil.w(TAG, "请求");
                try {
                    //调用同步请求方法execute()后直接返回数据Response对象,
                    //该对象包含http协议返回信息以及服务器返回的数据body
                    Response response = call.execute();
                    //response.body().string()只能调用一次，第二次将返回null，输入流只能读取一次
                    appendResult("线程"+Thread.currentThread()+"\n"+
                            response.body().string());
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }.start();
    }
    /**2、异步get请求*/
    private void getDataAsyn(){
        OkHttpClient mOkHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(getUrl(false))
                .build();
        Call call = mOkHttpClient.newCall(request);
        //异步请求enqueue(),通过回调获取到返回的数据，注意：回调中仍然是子线程
        call.enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                appendResult("线程" + Thread.currentThread() + "\n" +
                        response.body().string());
            }
        });
    }

    private String getUrl(boolean isPost){
        return isPost?"http://route.showapi.com/25-3":
                "http://route.showapi.com/25-3?showapi_appid=72441&" +
                        "showapi_sign=965f10eb8a454397a855784bba0abfbb&id=640402199301311369";
    }

    /**3、post方式提交*/
    private void postDataWithParams() {
        OkHttpClient client = new OkHttpClient();
        //①、表单FormBody
        FormBody.Builder formBody = new FormBody.Builder();//创建表单数据体
        formBody.add("showapi_appid","72441");//传递键值对参数
        formBody.add("showapi_sign","965f10eb8a454397a855784bba0abfbb");
        formBody.add("id","640402199301311369");
        //②、JSON字符串形式
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");//数据类型为json格式，
        String jsonStr = "{\"showapi_appid\":\"72441\",\"showapi_sign\":\"965f10eb8a454397a855784bba0abfbb\"}";//json数据.
        RequestBody body1 = RequestBody.create(JSON, jsonStr);
        //③、文件
        MediaType fileType = MediaType.parse("File/*");
        File file = new File("path");
        RequestBody body2 = RequestBody.create(fileType , file );
        //④、自定义
        RequestBody body3 = new RequestBody() {
            @Override
            public MediaType contentType() {
                return null;
            }
            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                FileInputStream fio= new FileInputStream(new File("path"));
                byte[] buffer = new byte[1024*8];
                if(fio.read(buffer) != -1){
                    sink.write(buffer);
                }
            }
        };
        //⑤、多重形式MultipartBody
        MultipartBody body4 =new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("showapi_appid","72441")//添加键值对参数
                .addFormDataPart("showapi_sign","965f10eb8a454397a855784bba0abfbb")
                .addFormDataPart("id","640402199301311369")
                .addFormDataPart("file",file.getName(),
                        RequestBody.create(MediaType.parse("file/*"), file))//添加文件
                .build();

        Request request = new Request.Builder()
                .url(getUrl(true))
                .post(formBody.build())//设置post数据体
                .build();
        client.newCall(request).enqueue(new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                appendResult("线程" + Thread.currentThread() + "\n" +
                        response.body().string());
            }
        });
    }


    private void getByZhy(){
        String url = "http://www.csdn.net/";
       /* OkHttpUtils
                .get()
                .url(url)
                .addParams("username", "hyman")
                .addParams("password", "123")
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                    }
                });*/
    }


}
