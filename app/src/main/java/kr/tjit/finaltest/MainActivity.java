package kr.tjit.finaltest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import kr.tjit.finaltest.datas.User;
import kr.tjit.finaltest.utils.GlobalData;

public class MainActivity extends BaseActivity {


    private TextView idTxt;
    private Button companyListBtn;
    private de.hdodenhof.circleimageview.CircleImageView profileImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {
        companyListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CompanyListActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void setValues() {

        Glide.with(mContext).load(GlobalData.loginUser.getProfile_image()).into(profileImgView);

        String name = GlobalData.loginUser.getName();
        idTxt.setText(name);

    }

    @Override
    public void bindViews() {
        this.profileImgView = (CircleImageView) findViewById(R.id.profileImgView);
        this.companyListBtn = (Button) findViewById(R.id.companyListBtn);
        this.idTxt = (TextView) findViewById(R.id.idTxt);

    }
}
