package cn.electricdog.noveltools.pojo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @Author: Sruifeng
 * @Email: suruifeng@wistronits.com
 * @Date: 2020/11/17 13:48
 */
public class ResultModel {
    public String username = "", jjcLastMatchTime = "", zcLastMatchTime = "", queryTime = "", queryDate = "";
    public Integer jjcWin = 0, jjcLose = 0, jjcRunaway = 0, jjcTotal = 0, zcWin = 0, zcLose = 0, zcRunaway = 0, zcTotal = 0, total = 0;
    public Integer jjcKillTotal = 0, jjcDeathTotal = 0, jjcAssistTotal = 0, jjcTowerDestroy = 0, jjcKillUnitTotal = 0, jjcRewardMoney = 0, jjcRewardExp = 0;
    public Integer zcKillTotal = 0, zcDeathTotal = 0, zcAssistTotal = 0, zcTowerDestroy = 0, zcKillUnitTotal = 0, zcRewardMoney = 0, zcRewardExp = 0;
    public Integer rewardMoney = 0, exp = 0, towerDestroy = 0, killUnitTotal = 0, assistTotal = 0, deathTotal = 0, killTotal = 0;

    public ResultModel(String username, String queryTime, String queryDate) {
        this.username = username;
        this.queryTime = queryTime;
        this.queryDate = queryDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJjcLastMatchTime() {
        return jjcLastMatchTime;
    }

    public void setJjcLastMatchTime(String jjcLastMatchTime) {
        this.jjcLastMatchTime = jjcLastMatchTime;
    }

    public String getZcLastMatchTime() {
        return zcLastMatchTime;
    }

    public void setZcLastMatchTime(String zcLastMatchTime) {
        this.zcLastMatchTime = zcLastMatchTime;
    }

    public String getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(String queryTime) {
        this.queryTime = queryTime;
    }

    public String getQueryDate() {
        return queryDate;
    }

    public void setQueryDate(String queryDate) {
        this.queryDate = queryDate;
    }

    public Integer getJjcWin() {
        return jjcWin;
    }

    public void setJjcWin(Integer jjcWin) {
        this.jjcWin = jjcWin;
    }

    public Integer getJjcLose() {
        return jjcLose;
    }

    public void setJjcLose(Integer jjcLose) {
        this.jjcLose = jjcLose;
    }

    public Integer getJjcRunaway() {
        return jjcRunaway;
    }

    public void setJjcRunaway(Integer jjcRunaway) {
        this.jjcRunaway = jjcRunaway;
    }

    public Integer getJjcTotal() {
        return jjcTotal;
    }

    public void setJjcTotal(Integer jjcTotal) {
        this.jjcTotal = jjcTotal;
    }

    public Integer getZcWin() {
        return zcWin;
    }

    public void setZcWin(Integer zcWin) {
        this.zcWin = zcWin;
    }

    public Integer getZcLose() {
        return zcLose;
    }

    public void setZcLose(Integer zcLose) {
        this.zcLose = zcLose;
    }

    public Integer getZcRunaway() {
        return zcRunaway;
    }

    public void setZcRunaway(Integer zcRunaway) {
        this.zcRunaway = zcRunaway;
    }

    public Integer getZcTotal() {
        return zcTotal;
    }

    public void setZcTotal(Integer zcTotal) {
        this.zcTotal = zcTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getJjcKillTotal() {
        return jjcKillTotal;
    }

    public void setJjcKillTotal(Integer jjcKillTotal) {
        this.jjcKillTotal = jjcKillTotal;
    }

    public Integer getJjcDeathTotal() {
        return jjcDeathTotal;
    }

    public void setJjcDeathTotal(Integer jjcDeathTotal) {
        this.jjcDeathTotal = jjcDeathTotal;
    }

    public Integer getJjcAssistTotal() {
        return jjcAssistTotal;
    }

    public void setJjcAssistTotal(Integer jjcAssistTotal) {
        this.jjcAssistTotal = jjcAssistTotal;
    }

    public Integer getJjcTowerDestroy() {
        return jjcTowerDestroy;
    }

    public void setJjcTowerDestroy(Integer jjcTowerDestroy) {
        this.jjcTowerDestroy = jjcTowerDestroy;
    }

    public Integer getJjcKillUnitTotal() {
        return jjcKillUnitTotal;
    }

    public void setJjcKillUnitTotal(Integer jjcKillUnitTotal) {
        this.jjcKillUnitTotal = jjcKillUnitTotal;
    }

    public Integer getJjcRewardMoney() {
        return jjcRewardMoney;
    }

    public void setJjcRewardMoney(Integer jjcRewardMoney) {
        this.jjcRewardMoney = jjcRewardMoney;
    }

    public Integer getJjcRewardExp() {
        return jjcRewardExp;
    }

    public void setJjcRewardExp(Integer jjcRewardExp) {
        this.jjcRewardExp = jjcRewardExp;
    }

    public Integer getZcKillTotal() {
        return zcKillTotal;
    }

    public void setZcKillTotal(Integer zcKillTotal) {
        this.zcKillTotal = zcKillTotal;
    }

    public Integer getZcDeathTotal() {
        return zcDeathTotal;
    }

    public void setZcDeathTotal(Integer zcDeathTotal) {
        this.zcDeathTotal = zcDeathTotal;
    }

    public Integer getZcAssistTotal() {
        return zcAssistTotal;
    }

    public void setZcAssistTotal(Integer zcAssistTotal) {
        this.zcAssistTotal = zcAssistTotal;
    }

    public Integer getZcTowerDestroy() {
        return zcTowerDestroy;
    }

    public void setZcTowerDestroy(Integer zcTowerDestroy) {
        this.zcTowerDestroy = zcTowerDestroy;
    }

    public Integer getZcKillUnitTotal() {
        return zcKillUnitTotal;
    }

    public void setZcKillUnitTotal(Integer zcKillUnitTotal) {
        this.zcKillUnitTotal = zcKillUnitTotal;
    }

    public Integer getZcRewardMoney() {
        return zcRewardMoney;
    }

    public void setZcRewardMoney(Integer zcRewardMoney) {
        this.zcRewardMoney = zcRewardMoney;
    }

    public Integer getZcRewardExp() {
        return zcRewardExp;
    }

    public void setZcRewardExp(Integer zcRewardExp) {
        this.zcRewardExp = zcRewardExp;
    }

    public ResultModel refresh() {
        rewardMoney = jjcRewardMoney + zcRewardMoney;
        exp = jjcRewardExp + zcRewardExp;
        towerDestroy = jjcTowerDestroy + zcTowerDestroy;
        killUnitTotal = jjcKillUnitTotal + zcKillUnitTotal;
        assistTotal = jjcAssistTotal + zcAssistTotal;
        deathTotal = jjcDeathTotal + zcDeathTotal;
        killTotal = jjcKillTotal + zcKillTotal;

        jjcTotal = jjcWin + jjcLose + jjcRunaway;
        zcTotal = zcWin + zcLose + zcRunaway;
        total = jjcTotal + zcTotal;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this, SerializerFeature.WriteMapNullValue);
    }
}
