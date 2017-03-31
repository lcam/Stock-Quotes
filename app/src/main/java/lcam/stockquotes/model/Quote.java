package lcam.stockquotes.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Symbol")
    @Expose
    private String symbol;
    @SerializedName("LastPrice")
    @Expose
    private Double lastPrice;
    @SerializedName("Change")
    @Expose
    private Double change;
    @SerializedName("ChangePercent")
    @Expose
    private Double changePercent;
    @SerializedName("Timestamp")
    @Expose
    private String timestamp;
    @SerializedName("MSDate")
    @Expose
    private Double mSDate;
    @SerializedName("MarketCap")
    @Expose
    private Double marketCap;
    @SerializedName("Volume")
    @Expose
    private Integer volume;
    @SerializedName("ChangeYTD")
    @Expose
    private Double changeYTD;
    @SerializedName("ChangePercentYTD")
    @Expose
    private Double changePercentYTD;
    @SerializedName("High")
    @Expose
    private Double high;
    @SerializedName("Low")
    @Expose
    private Double low;
    @SerializedName("Open")
    @Expose
    private Double open;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(Double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public Double getChange() {
        return change;
    }

    public void setChange(Double change) {
        this.change = change;
    }

    public Double getChangePercent() {
        return changePercent;
    }

    public void setChangePercent(Double changePercent) {
        this.changePercent = changePercent;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getMSDate() {
        return mSDate;
    }

    public void setMSDate(Double mSDate) {
        this.mSDate = mSDate;
    }

    public Double getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Double marketCap) {
        this.marketCap = marketCap;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public Double getChangeYTD() {
        return changeYTD;
    }

    public void setChangeYTD(Double changeYTD) {
        this.changeYTD = changeYTD;
    }

    public Double getChangePercentYTD() {
        return changePercentYTD;
    }

    public void setChangePercentYTD(Double changePercentYTD) {
        this.changePercentYTD = changePercentYTD;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

}
