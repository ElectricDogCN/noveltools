package cn.electricdog.noveltools;

import java.util.ArrayList;
import java.util.List;

public class MatchListModel {
    String Result;
    List<MatchModel> List;

    public MatchListModel(String result, List<MatchModel> list) {
        Result = result;
        if(result!= null && list!= null&&"OK".equals(result)){
            List = list;
        }else {
            List = new ArrayList<>(0);
        }
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String result) {
        Result = result;
    }

    public java.util.List<MatchModel> getList() {
        return List;
    }

    public void setList(java.util.List<MatchModel> list) {
        if(list!= null){
            List = list;
        }else {
            List = new ArrayList<>(0);
        }
    }
}
