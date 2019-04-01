package com.example.lpc.receipt.NewRecord;

import android.widget.TextView;

public class SearchType_Item_Model {

    public String searchtype_name, searchtype_content;

    public SearchType_Item_Model(String searchtype_name, String searchtype_content) {
        this.searchtype_name = searchtype_name;
        this.searchtype_content = searchtype_content;
    }

    public String getSearchtype_name() {
        return searchtype_name;
    }

    public void setSearchtype_name(String searchtype_name) {
        this.searchtype_name = searchtype_name;
    } 

    public String getSearchtype_content() {
        return searchtype_content;
    }

    public void setSearchtype_content(String searchtype_content) {
        this.searchtype_content = searchtype_content;
    }
}
