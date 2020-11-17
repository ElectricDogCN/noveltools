/**
 * Copyright 2020 bejson.com
 */
package cn.electricdog.noveltools.pojo.match;

import java.util.Date;
import java.util.List;

/**
 * Auto-generated: 2020-11-17 14:51:32
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Match {

    private int MatchType;
    private int WinSideKill;
    private int LoseSideKill;
    private int UsedTime;
    private Date MatchDate;
    private List<Role> WinSide;
    private List<Role> loseSide;
    private long FindTs;
    private int FindCount;

    public void setMatchType(int MatchType) {
        this.MatchType = MatchType;
    }

    public int getMatchType() {
        return MatchType;
    }

    public void setWinSideKill(int WinSideKill) {
        this.WinSideKill = WinSideKill;
    }

    public int getWinSideKill() {
        return WinSideKill;
    }

    public void setLoseSideKill(int LoseSideKill) {
        this.LoseSideKill = LoseSideKill;
    }

    public int getLoseSideKill() {
        return LoseSideKill;
    }

    public void setUsedTime(int UsedTime) {
        this.UsedTime = UsedTime;
    }

    public int getUsedTime() {
        return UsedTime;
    }

    public void setMatchDate(Date MatchDate) {
        this.MatchDate = MatchDate;
    }

    public Date getMatchDate() {
        return MatchDate;
    }

    public List<Role> getWinSide() {
        return WinSide;
    }

    public void setWinSide(List<Role> winSide) {
        WinSide = winSide;
    }

    public List<Role> getLoseSide() {
        return loseSide;
    }

    public void setLoseSide(List<Role> loseSide) {
        this.loseSide = loseSide;
    }

    public void setFindTs(long FindTs) {
        this.FindTs = FindTs;
    }

    public long getFindTs() {
        return FindTs;
    }

    public void setFindCount(int FindCount) {
        this.FindCount = FindCount;
    }

    public int getFindCount() {
        return FindCount;
    }

}