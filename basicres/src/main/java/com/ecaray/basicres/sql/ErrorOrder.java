package com.ecaray.basicres.sql;

import org.litepal.crud.DataSupport;

/**
 * 类描述: 错误订单
 * @author : Eric_Huang
 * 创建时间: 2016/12/19 16:41
 */
public class ErrorOrder extends DataSupport {
    /*************************是否含有刷卡记录********************/

    /**
     * 标志
     */
    private boolean hasCardInfo;

    /*************************订单相关参数        ****************/
    /**
     * 出错订单id
     */
    private String ErrorOrderId;

    /**
     * 订单类型
     */
    private int Type;

    /**
     * 泊位号
     */
    private String BerthCode;

    /**
     * 支付方式
     */
    private int PayType;

    /**
     * 结束订单停车
     */
    private long EndParkTime;

    /*************************西安 长安通相关参数  ***************/
//    * pda需要处理的类型
//    *  本地流水号(10位) 0000012593  PSAM设备号(12位) 710001009225 PSAM卡号(16位)  0100000000009225
//            * PSAM卡流水号(9位) 000044819
//            * 城市代码(4位) 7100  卡内号(16位) 7100(城市代码) 010002836027  卡CSN(8位) 8647FE37  卡计数器(6位)000348   卡型，3-M1卡 1-CPU卡(1位)1
//            * 主卡类型(2位) 01 子卡类型(2位)00
//            * 消费前卡余额（分）(10位) 0000010500
//            * 交易金额（分） (10位) 0000000540
//            * 优惠卡类型(2位) 01
//            * 应收金额(10位) 0000000540
//            * 交易发生日期(8位) 20170901 YYYYMMDD
//    * 交易发生时间(6位) 140122  Hhmmss
//    * 交易认证码(8位) D749919A
//    * 卡内版本号(2位) 00
//    8698(发卡方标识) 0000(地区代码) 0000(行业代码) fffe(RFU) 01(应用类型标识) 00(应用版本) 0000	000001000589a16d(应用序列号) 20130122 20240424 01（卡类型） 00（押金）  9000

    /**
     * 本地流水号(10位) 0000012593
     */
    private String LocalSeq;

    /**
     * PSAM设备号(12位) 710001009225
     */
    private String TerminalId;

    /**
     * PSAM卡号(16位)  0100000000009225
     */
    private String PsamCardNo;

    /**
     * PSAM卡流水号(9位) 000044819
     */
    private String PsamSerialNum;

    /**
     * 城市代码(4位) 7100
     */
    private String CityCode;

    /**
     * 卡内号(16位)-------------应用序列号后8个字节
     */
    private String CardNumber;

    /**
     * 卡CSN(8位) 8647FE37
     */
    private String CardCsn;

    /**
     * 卡计数器(6位)000348
     */
    private String CardCount;

    /**
     * 卡型，3-M1卡 1-CPU卡(1位)1--选中
     */
    private String CardType;

    /**
     * 主卡类型(2位) 01
     */
    private String MainType;

    /**
     * 子卡类型(2位)00
     */
    private String ChildType;

    /**
     * 消费前卡余额（分）(10位) 0000010500
     */
    private String Balance;

    /**
     * 交易金额（分） (10位) 0000000540----------------------应收金额(10位) 0000000540 同用一个字段
     */
    private String PayMoney;

    /**
     * 优惠卡类型：写死01；即卡型cpu卡前补零
     */
    private String DiscountType;

    /**
     * 年月日--交易发生日期(8位) 20170901 YYYYMMDD
     */
    private String YearMonthDay;

    /**
     * 日分秒--交易发生时间(6位) 140122  Hhmmss
     */
    private String HourMinSec;

    /**
     * 交易认证码(8位) D749919A
     */
    private String Tac;

    /**
     * 卡内版本号(2位) 00
     */
    private String ApplicationVer;

    public boolean isHasCardInfo() {
        return hasCardInfo;
    }

    public void setHasCardInfo(boolean hasCardInfo) {
        this.hasCardInfo = hasCardInfo;
    }

    public String getErrorOrderId() {
        return ErrorOrderId;
    }

    public void setErrorOrderId(String errorOrderId) {
        ErrorOrderId = errorOrderId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public String getBerthCode() {
        return BerthCode;
    }

    public void setBerthCode(String berthCode) {
        BerthCode = berthCode;
    }

    public long getEndParkTime() {
        return EndParkTime;
    }

    public void setEndParkTime(long endParkTime) {
        EndParkTime = endParkTime;
    }

    public int getPayType() {
        return PayType;
    }

    public void setPayType(int payType) {
        PayType = payType;
    }


    public String getLocalSeq() {
        return LocalSeq;
    }

    public void setLocalSeq(String localSeq) {
        LocalSeq = localSeq;
    }

    public String getTerminalId() {
        return TerminalId;
    }

    public void setTerminalId(String terminalId) {
        TerminalId = terminalId;
    }

    public String getPsamCardNo() {
        return PsamCardNo;
    }

    public void setPsamCardNo(String psamCardNo) {
        PsamCardNo = psamCardNo;
    }

    public String getPsamSerialNum() {
        return PsamSerialNum;
    }

    public void setPsamSerialNum(String psamSerialNum) {
        PsamSerialNum = psamSerialNum;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getCardNumber() {
        return CardNumber;
    }

    public void setCardNumber(String cardNumber) {
        CardNumber = cardNumber;
    }

    public String getCardCsn() {
        return CardCsn;
    }

    public void setCardCsn(String cardCsn) {
        CardCsn = cardCsn;
    }

    public String getCardCount() {
        return CardCount;
    }

    public void setCardCount(String cardCount) {
        CardCount = cardCount;
    }

    public String getCardType() {
        return CardType;
    }

    public void setCardType(String cardType) {
        CardType = cardType;
    }

    public String getMainType() {
        return MainType;
    }

    public void setMainType(String mainType) {
        MainType = mainType;
    }

    public String getChildType() {
        return ChildType;
    }

    public void setChildType(String childType) {
        ChildType = childType;
    }

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getPayMoney() {
        return PayMoney;
    }

    public void setPayMoney(String payMoney) {
        PayMoney = payMoney;
    }

    public String getDiscountType() {
        return DiscountType;
    }

    public void setDiscountType(String discountType) {
        DiscountType = discountType;
    }

    public String getYearMonthDay() {
        return YearMonthDay;
    }

    public void setYearMonthDay(String yearMonthDay) {
        YearMonthDay = yearMonthDay;
    }

    public String getHourMinSec() {
        return HourMinSec;
    }

    public void setHourMinSec(String hourMinSec) {
        HourMinSec = hourMinSec;
    }

    public String getTac() {
        return Tac;
    }

    public void setTac(String tac) {
        Tac = tac;
    }

    public String getApplicationVer() {
        return ApplicationVer;
    }

    public void setApplicationVer(String applicationVer) {
        ApplicationVer = applicationVer;
    }
}
