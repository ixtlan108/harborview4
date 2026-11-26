package harborview.rapanui.core.domain.stockmarket.stockoption;


import harborview.shared.dto.Month;
import harborview.shared.dto.OptionType;
import harborview.shared.functional.Either;
import harborview.stockmarket.stock.Stock;
import harborview.rapanui.kernel.error.Error;

public class StockOption {

    private Integer oid;
    private final Stock stock;
    private final StockOptionTicker ticker;
    private StockOptionPrice price;
    private final Calculator calculator;
    private LifeCycle lifeCycle;

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public enum LifeCycle {FROM_JSON, SAVED_TO_DATABASE, FROM_DATABASE }

    private StockOption(Stock stock,
                        StockOptionTicker ticker,
                        Calculator calculator,
                        LifeCycle lifeCycle) {
        this.stock = stock;
        this.ticker = ticker;
        this.calculator = calculator;
        this.lifeCycle = lifeCycle;
    }

    public static Either<Error, StockOption> create(Stock stock,
                                                    OptionType ot,
                                                    int year,
                                                    Month month,
                                                    Calculator calculator,
                                                    LifeCycle lifeCycle) {

        var ticker = StockOptionTicker.create(ot, year, month);

        if (ticker.isRight()) {
            return Either.left(ticker.getLeft());
        }
        return Either.right(new StockOption(stock, ticker.getRight(), calculator, LifeCycle.FROM_JSON));

        //return Either.left(new Error.BusinessError("Not implemented"));
    }

    //--------------------------------------------------
    //--------------- Life Cycle -----------------------
    //--------------------------------------------------
    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }
    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }

    /*
    //private StockOptionUtil stockOptionUtil;
    private LocalDate currentDate;
    private final Pattern p = Pattern.compile("\\D+(\\d\\D)\\d+");
    private Stock stock;


    public static int BUY = 1;
    public static int SELL = 2;


    //public enum OptionType { CALL, PUT, UNDEF };

    public enum LifeCycle { FROM_HTML, SAVED_TO_DATABASE, FROM_DATABASE }

    public StockOption() {
    }


    public StockOption(String ticker,
                       StockOptionType opType,
                       double x,
                       Stock stock,
                       LocalDate currentDate) {
        this.ticker = ticker;
        this.opType = opType;
        this.x = x;
        this.stock = stock;
        this.currentDate = currentDate;
    }


    //--------------------------------------------------
    //------------- Parent
    //--------------------------------------------------
    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    private Integer _stockId = null;
    public int getStockId() {
        return stock == null ? _stockId : stock.getOid();
    }
    public void setStockId(int value) {
        _stockId = value;
    }


    //--------------------------------------------------
    //------------- Id
    //--------------------------------------------------
    private int oid;
    public int getOid() {
        return oid;
    }

    public void setOid(int oid) {
        this.oid = oid;
    }

    //--------------------------------------------------
    //------------- Expiry
    //--------------------------------------------------
    private LocalDate expiry;
    public LocalDate getExpiry() {
        if (expiry == null) {
            String series = getSeries();
            expiry = StockOptionUtil.seriesAsDate(series);
        }
        return expiry;
    }
    public void setExpiry(LocalDate value) {
        expiry = value;
    }

    public Date getExpirySql() {
        return Date.valueOf(getExpiry());
    }

    public void setExpirySql(Date expirySql) {
        this.expiry = expirySql.toLocalDate();
    }

    //--------------------------------------------------
    //------------- OpType
    //--------------------------------------------------
    private StockOptionType opType;
    public StockOptionType getOpType() {
        return opType;
    }


    public void setOpType(StockOptionType value) {
            opType = value;
    }

    public String getOpTypeStr() {
        return getOpType() == StockOptionType.CALL ? "c" : "p";
    }
    public void setOpTypeStr(String value) {
        if (value.equals("c")) {
            setOpType(StockOptionType.CALL);
        }
        else {
            setOpType(StockOptionType.PUT);
        }
    }

    //--------------------------------------------------
    //--------------- Life Cycle -----------------------
    //--------------------------------------------------
    private LifeCycle lifeCycle;
    public LifeCycle getLifeCycle() {
        return lifeCycle;
    }
    public void setLifeCycle(LifeCycle lifeCycle) {
        this.lifeCycle = lifeCycle;
    }


    //--------------------------------------------------
    //------------- Series
    //--------------------------------------------------

    private String series;
    public String getSeries() {
        if (series == null) {
            Matcher m = p.matcher(getTicker());
            if (m.find()) {
                series = m.group(1);
            }
            else {
                //series = "??";
                throw new RuntimeException("Series undefined for " + ticker);
            }
        }
        return series;
    }

    public long getDays() {
        return DAYS.between(getCurrentDate(),getExpiry());
    }

    public void setSeries(String value) {
        series = value;
    }

    //--------------------------------------------------
    //------------- X
    //--------------------------------------------------
    private double x;
    public double getX() {
        return x;
    }
    public void setX(double value) {
        x = value;
    }


    //--------------------------------------------------
    //------------- Ticker
    //--------------------------------------------------
    private String ticker;
    public String getTicker() {
        return ticker;
    }



    public void setTicker(String value) {
        ticker = value;
    }

     */

}
