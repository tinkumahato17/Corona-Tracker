package in.santiniketan.corona;

public class CoronaItem {

    private String State;
    private String Death;
    private String Active;
    private String Recovered;
    private String Confirmed;
    private String LastUpdated;
    private String TodayDeath;
    private String TodayRecovered;
    private String TodayActive;


    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getDeath() {
        return Death;
    }

    public void setDeath(String death) {
        Death = death;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getRecovered() {
        return Recovered;
    }

    public void setRecovered(String recovered) {
        Recovered = recovered;
    }

    public String getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(String confirmed) {
        Confirmed = confirmed;
    }

    public String getLastUpdated() {
        return LastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        LastUpdated = lastUpdated;
    }

    public String getTodayDeath() {
        return TodayDeath;
    }

    public void setTodayDeath(String todayDeath) {
        TodayDeath = todayDeath;
    }

    public String getTodayRecovered() {
        return TodayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        TodayRecovered = todayRecovered;
    }

    public String getTodayActive() {
        return TodayActive;
    }

    public void setTodayActive(String todayActive) {
        TodayActive = todayActive;
    }

    public CoronaItem(String state, String death, String active, String recovered, String confirmed, String lastUpdated, String todayDeath, String todayRecovered, String todayActive) {
        State = state;
        Death = death;
        Active = active;
        Recovered = recovered;
        Confirmed = confirmed;
        LastUpdated = lastUpdated;
        TodayDeath = todayDeath;
        TodayRecovered = todayRecovered;
        TodayActive = todayActive;
    }
}
