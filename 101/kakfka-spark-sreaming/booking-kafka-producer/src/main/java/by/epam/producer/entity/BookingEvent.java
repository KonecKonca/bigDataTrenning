package by.epam.producer.entity;

public class BookingEvent {

    public static final String COMMA = ", ";

    private String dateTime;
    private int siteName;
    private int posaContinent;
    private int userLocationCountry;
    private int userLocationRegion;
    private int userLocationCity;
    private double origDestinationDistance;
    private int userId;
    private int isMobile;
    private int isPackage;
    private int channel;
    private String srchCi;
    private String srchCo;
    private int srchAdultsCnt;
    private int srchChildrenCnt;
    private int srchRmCnt;
    private int srchDestinationId;
    private int srchDestinationTypeId;
    private int isBooking;
    private long cnt;
    private int hotelContinent;
    private int hotelCountry;
    private int hotelMarket;
    private int hotelCluster;

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSiteName() {
        return siteName;
    }

    public void setSiteName(int siteName) {
        this.siteName = siteName;
    }

    public int getPosaContinent() {
        return posaContinent;
    }

    public void setPosaContinent(int posaContinent) {
        this.posaContinent = posaContinent;
    }

    public int getUserLocationCountry() {
        return userLocationCountry;
    }

    public void setUserLocationCountry(int userLocationCountry) {
        this.userLocationCountry = userLocationCountry;
    }

    public int getUserLocationRegion() {
        return userLocationRegion;
    }

    public void setUserLocationRegion(int userLocationRegion) {
        this.userLocationRegion = userLocationRegion;
    }

    public int getUserLocationCity() {
        return userLocationCity;
    }

    public void setUserLocationCity(int userLocationCity) {
        this.userLocationCity = userLocationCity;
    }

    public double getOrigDestinationDistance() {
        return origDestinationDistance;
    }

    public void setOrigDestinationDistance(double origDestinationDistance) {
        this.origDestinationDistance = origDestinationDistance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(int isMobile) {
        this.isMobile = isMobile;
    }

    public int getIsPackage() {
        return isPackage;
    }

    public void setIsPackage(int isPackage) {
        this.isPackage = isPackage;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public String getSrchCi() {
        return srchCi;
    }

    public void setSrchCi(String srhcCi) {
        this.srchCi = srhcCi;
    }

    public String getSrchCo() {
        return srchCo;
    }

    public void setSrchCo(String srchCo) {
        this.srchCo = srchCo;
    }

    public int getSrchAdultsCnt() {
        return srchAdultsCnt;
    }

    public void setSrchAdultsCnt(int srchAdultsCnt) {
        this.srchAdultsCnt = srchAdultsCnt;
    }

    public int getSrchChildrenCnt() {
        return srchChildrenCnt;
    }

    public void setSrchChildrenCnt(int srchChildrenCnt) {
        this.srchChildrenCnt = srchChildrenCnt;
    }

    public int getSrchRmCnt() {
        return srchRmCnt;
    }

    public void setSrchRmCnt(int srchRmCnt) {
        this.srchRmCnt = srchRmCnt;
    }

    public int getSrchDestinationId() {
        return srchDestinationId;
    }

    public void setSrchDestinationId(int srchDestinationId) {
        this.srchDestinationId = srchDestinationId;
    }

    public int getSrchDestinationTypeId() {
        return srchDestinationTypeId;
    }

    public void setSrchDestinationTypeId(int srchDestinationTypeId) {
        this.srchDestinationTypeId = srchDestinationTypeId;
    }

    public int getIsBooking() {
        return isBooking;
    }

    public void setIsBooking(int isBooking) {
        this.isBooking = isBooking;
    }

    public long getCnt() {
        return cnt;
    }

    public void setCnt(long cnt) {
        this.cnt = cnt;
    }

    public int getHotelContinent() {
        return hotelContinent;
    }

    public void setHotelContinent(int hotelContinent) {
        this.hotelContinent = hotelContinent;
    }

    public int getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(int hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public int getHotelMarket() {
        return hotelMarket;
    }

    public void setHotelMarket(int hotelMarket) {
        this.hotelMarket = hotelMarket;
    }

    public int getHotelCluster() {
        return hotelCluster;
    }

    public void setHotelCluster(int hotelCluster) {
        this.hotelCluster = hotelCluster;
    }

    @Override
    public String toString() {
        return "BookingEvent{" +
                "dateTime='" + dateTime + '\'' +
                ", siteName=" + siteName +
                ", posaContinent=" + posaContinent +
                ", userLocationCountry=" + userLocationCountry +
                ", userLocationRegion=" + userLocationRegion +
                ", userLocationCity=" + userLocationCity +
                ", origDestinationDistance=" + origDestinationDistance +
                ", userId=" + userId +
                ", isMobile=" + isMobile +
                ", isPackage=" + isPackage +
                ", channel=" + channel +
                ", srchCi='" + srchCi + '\'' +
                ", srchCo='" + srchCo + '\'' +
                ", srchAdultsCnt=" + srchAdultsCnt +
                ", srchChildrenCnt=" + srchChildrenCnt +
                ", srchRmCnt=" + srchRmCnt +
                ", srchDestinationId=" + srchDestinationId +
                ", srchDestinationTypeId=" + srchDestinationTypeId +
                ", isBooking=" + isBooking +
                ", cnt=" + cnt +
                ", hotelContinent=" + hotelContinent +
                ", hotelCountry=" + hotelCountry +
                ", hotelMarket=" + hotelMarket +
                ", hotelCluster=" + hotelCluster +
                '}';
    }
}
