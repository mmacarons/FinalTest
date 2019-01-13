package kr.tjit.finaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.tjit.finaltest.datas.Company;
import kr.tjit.finaltest.utils.ConnectServer;

public class CompanyListActivity extends BaseActivity {

    List<Company> companyList = new ArrayList<Company>();


    private android.widget.ListView companyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_list);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

    }

    @Override
    public void setValues() {

        getCompanysFromServer();

    }

    void getCompanysFromServer() {
        ConnectServer.getRequestCompanys(mContext, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("택배회사목록", json.toString());
            }
        });
    }

    @Override
    public void bindViews() {
        this.companyListView = (ListView) findViewById(R.id.companyListView);

    }
}
