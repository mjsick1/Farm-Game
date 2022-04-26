public class SeasonConditionsConstructor {
    private String seasonName;
    private String tractor;
    private String crop;

    public SeasonConditionsConstructor(String seasonName, String tractor, String crop) {
        this.seasonName = seasonName;
        this.tractor = tractor;
        this.crop = crop;
    }
    public void setSeasonName(String seasonName){
        this.seasonName = seasonName;
    }
    public void setTractor(String tractor){
        this.tractor = tractor;
    }
    public void setCrop(String crop){
        this.crop = crop;
    }
    public String getSeasonName(){
        return seasonName;
    }
    public String getTractor(){
        return tractor;
    }
    public String getCrop(){
        return crop;
    }
}