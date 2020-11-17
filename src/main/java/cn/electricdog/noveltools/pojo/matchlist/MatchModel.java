package cn.electricdog.noveltools.pojo.matchlist;

public class MatchModel {
    private String MatchID;
    private Integer MatchType;
    private Integer HeroLevel;
    private Integer Result;
    private String MatchDate;
    private String HeroName;
    private String HeroIcon;

    public String getMatchID() {
        return MatchID;
    }

    public void setMatchID(String matchID) {
        MatchID = matchID;
    }

    public Integer getMatchType() {
        return MatchType;
    }

    public void setMatchType(Integer matchType) {
        MatchType = matchType;
    }

    public Integer getHeroLevel() {
        return HeroLevel;
    }

    public void setHeroLevel(Integer heroLevel) {
        HeroLevel = heroLevel;
    }

    public Integer getResult() {
        return Result;
    }

    public void setResult(Integer result) {
        Result = result;
    }

    public String getMatchDate() {
        return MatchDate;
    }

    public void setMatchDate(String matchDate) {
        MatchDate = matchDate;
    }

    public String getHeroName() {
        return HeroName;
    }

    public void setHeroName(String heroName) {
        HeroName = heroName;
    }

    public String getHeroIcon() {
        return HeroIcon;
    }

    public void setHeroIcon(String heroIcon) {
        HeroIcon = heroIcon;
    }

    @Override
    public String toString() {
        return "MatchModel{" +
                "MatchID='" + MatchID + '\'' +
                ", MatchType=" + MatchType +
                ", HeroLevel=" + HeroLevel +
                ", Result=" + Result +
                ", MatchDate='" + MatchDate + '\'' +
                ", HeroName='" + HeroName + '\'' +
                ", HeroIcon='" + HeroIcon + '\'' +
                '}';
    }
}
