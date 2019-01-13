package kr.tjit.finaltest.datas;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Company implements Serializable {
    private int id;
    private String name;
    private String logo;

    public static Company getCompanyFromJson(JSONObject companyJson) {
        Company company = new Company();

        try {

            company.setId(companyJson.getInt("id"));
            company.setName(companyJson.getString("name"));
            company.setLogo(companyJson.getString("logo"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
