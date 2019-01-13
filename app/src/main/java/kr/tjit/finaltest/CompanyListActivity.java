package kr.tjit.finaltest;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import kr.tjit.finaltest.Adapters.CompanyAdapter;
import kr.tjit.finaltest.datas.Company;
import kr.tjit.finaltest.utils.ConnectServer;

public class CompanyListActivity extends BaseActivity {

    List<Company> companyList = new ArrayList<Company>();
    CompanyAdapter mAdapter;

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

        mAdapter = new CompanyAdapter(mContext, companyList);
        companyListView.setAdapter(mAdapter);

        getCompanysFromServer();

    }

    void getCompanysFromServer() {
        ConnectServer.getRequestCompanys(mContext, new ConnectServer.JsonResponseHandler() {
            @Override
            public void onResponse(JSONObject json) {
                Log.d("택배회사목록", json.toString());

                try {
                    int code = json.getInt("code");

                    if (code == 200){
                        JSONObject data = json.getJSONObject("data");
                        JSONArray companys = data.getJSONArray("company");

                        for (int i=0; i<companys.length(); i++){
                            JSONObject companyJson = companys.getJSONObject(i);

                            Company companyObject = Company.getCompanyFromJson(companyJson);
                            companyList.add(companyObject);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter.notifyDataSetChanged();
                            }
                        });

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    @Override
    public void bindViews() {
        this.companyListView = (ListView) findViewById(R.id.companyListView);

    }
}
