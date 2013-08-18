package com.cloud.test;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import com.cloud.util.DialogUtil;
import com.cloud.util.HttpUtil;
import org.json.JSONArray;

public class MainActivity extends Activity {

    private ListView alist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_item);

        alist = (ListView) findViewById(R.id.aList);
        String url = HttpUtil.BASE_URL + "AArticleServlet?typeid=100&method=query";

        try
        {
            // 向指定URL发送请求，并把服务器响应转换成JSONArray对象
            System.out.println("**********************************"+url);
            JSONArray jsonArray = new JSONArray(HttpUtil.getRequest(url));
            System.out.println(jsonArray.toString()+"***********************************");
            // 将JSONArray包装成Adapter
            JSONArrayAdapter adapter = new JSONArrayAdapter(this
                    , jsonArray, "title", true);
            alist.setAdapter(adapter);
        }
        catch (Exception e)
        {
            DialogUtil.showDialog(this, "no response", false);
            e.printStackTrace();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
