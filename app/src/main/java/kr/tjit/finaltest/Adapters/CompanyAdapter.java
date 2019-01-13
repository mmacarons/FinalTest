package kr.tjit.finaltest.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import kr.tjit.finaltest.R;
import kr.tjit.finaltest.datas.Company;

public class CompanyAdapter extends ArrayAdapter<Company> {

    Context mContext;
    List<Company> mList;
    LayoutInflater inf;

    public CompanyAdapter(Context context, List<Company> list) {
        super(context, R.layout.company_list_item, list);

        mContext = context;
        mList = list;
        inf = LayoutInflater.from(mContext);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            row = inf.inflate(R.layout.company_list_item, null);
        }

        ImageView companyImgView = row.findViewById(R.id.companyImgView);
        TextView companyNameTxt = row.findViewById(R.id.companyNameTxt);
        TextView companyIdTxt = row.findViewById(R.id.companyIdTxt);

        Company data = mList.get(position);

        Glide.with(mContext).load(data.getLogo()).into(companyImgView);
        companyNameTxt.setText(data.getName());
        companyIdTxt.setText(String.valueOf(data.getId()));

        return row;

    }
}
