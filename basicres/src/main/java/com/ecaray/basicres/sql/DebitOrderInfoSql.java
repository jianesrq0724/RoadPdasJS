package com.ecaray.basicres.sql;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.util.List;

/**
 * 类描述:
 * 创建人: Eric_Huang
 * 创建时间: 2017/2/28 16:30
 */
public class DebitOrderInfoSql extends DataSupport{

    private static final String TAG = "打印数据";

    public String mCityCode;
    public String mCardMType;
    public String mCardSType;
    public String mPayMoney;
    public String mCardDeposit;
    public String mOrigAmt;
    public String mBalanceLeftMoney;
    public String TAC;
    public String mKeyVersion;
    public String mAuthSeq;
    public String mIssueSerial;
    public String mOrderId;
    public String mPosSep;
    public String mTranNumber;
    public String mTxnDate;
    public String mTxnTime;
    public String mOrderType;
    public int mApplyDuration;
    public String mExtraPay;

    /**
     * 退费参数
     */
    public String mActionType;
    public String mCardSellDate;        //启动日期
    public String mCardValDate;
    public String mSettDate;
    public String mRefundId;

    public DebitOrderInfoSql(){

    }

    public DebitOrderInfoSql(String actionType, String cityCode, String cardMType, String cardSType,
                             String issuerSerial, String cardSellDate, String cardValDate, String tranNumber,
                             String befBalance, String deposit, String txnDate, String txnTime,
                             String posSequence, String authSeq, String orderId, String settDate, String refundId){
        mActionType = actionType;
        mCityCode = cityCode;
        mCardMType = cardMType;
        mCardSType = cardSType;
        mIssueSerial = issuerSerial;
        mCardSellDate = cardSellDate;
        mCardValDate = cardValDate;
        mTranNumber = tranNumber;
        mBalanceLeftMoney = befBalance;
        mCardDeposit = deposit;
        mTxnDate = txnDate;
        mTxnTime = txnTime;
        mPosSep = posSequence;
        mAuthSeq = authSeq;
        mOrderId = orderId;
        mSettDate = settDate;
        mRefundId = refundId;
    }

    public DebitOrderInfoSql(String actionType, String cityCode, String cardMType, String cardSType,
                             String issuerSerial, String cardSellDate, String cardValDate, String tranNumber,
                             String befBalance, String deposit, String txnDate, String txnTime,
                             String posSequence, String authSeq, String orderId, String settDate){
        mActionType = actionType;
        mCityCode = cityCode;
        mCardMType = cardMType;
        mCardSType = cardSType;
        mIssueSerial = issuerSerial;
        mCardSellDate = cardSellDate;
        mCardValDate = cardValDate;
        mTranNumber = tranNumber;
        mBalanceLeftMoney = befBalance;
        mCardDeposit = deposit;
        mTxnDate = txnDate;
        mTxnTime = txnTime;
        mPosSep = posSequence;
        mAuthSeq = authSeq;
        mOrderId = orderId;
        mSettDate = settDate;
    }

    public DebitOrderInfoSql(String cityCode, String cardMType, String cardSType, String payMoney,
                             String cardDeposit, String origAmt, String balanceLeftMoney, String TAC, String keyVersion,
                             String authSeq, String issueSerial, String orderId, String posSep,
                             String tranNumber, String txnDate, String txnTime, String orderType,
                             int applyDuration, String extraPay) {
        mCityCode = cityCode;
        mCardMType = cardMType;
        mCardSType = cardSType;
        mPayMoney = payMoney;
        mCardDeposit = cardDeposit;
        mOrigAmt = origAmt;
        mBalanceLeftMoney = balanceLeftMoney;
        this.TAC = TAC;
        mKeyVersion = keyVersion;
        mAuthSeq = authSeq;
        mIssueSerial = issueSerial;
        mOrderId = orderId;
        mPosSep = posSep;
        mTranNumber = tranNumber;
        mTxnDate = txnDate;
        mTxnTime = txnTime;
        mOrderType = orderType;
        mApplyDuration = applyDuration;
        mExtraPay = extraPay;
    }



    public String getCityCode() {
        return mCityCode;
    }

    public void setCityCode(String cityCode) {
        mCityCode = cityCode;
    }

    public String getCardMType() {
        return mCardMType;
    }

    public void setCardMType(String cardMType) {
        mCardMType = cardMType;
    }

    public String getCardSType() {
        return mCardSType;
    }

    public void setCardSType(String cardSType) {
        mCardSType = cardSType;
    }

    public String getPayMoney() {
        return mPayMoney;
    }

    public void setPayMoney(String payMoney) {
        mPayMoney = payMoney;
    }

    public String getCardDeposit() {
        return mCardDeposit;
    }

    public void setCardDeposit(String cardDeposit) {
        mCardDeposit = cardDeposit;
    }

    public String getOrigAmt() {
        return mOrigAmt;
    }

    public void setOrigAmt(String origAmt) {
        mOrigAmt = origAmt;
    }

    public String getBalanceLeftMoney() {
        return mBalanceLeftMoney;
    }

    public void setBalanceLeftMoney(String balanceLeftMoney) {
        mBalanceLeftMoney = balanceLeftMoney;
    }

    public String getTAC() {
        return TAC;
    }

    public void setTAC(String TAC) {
        this.TAC = TAC;
    }

    public String getKeyVersion() {
        return mKeyVersion;
    }

    public void setKeyVersion(String keyVersion) {
        mKeyVersion = keyVersion;
    }

    public String getAuthSeq() {
        return mAuthSeq;
    }

    public void setAuthSeq(String authSeq) {
        mAuthSeq = authSeq;
    }

    public String getIssueSerial() {
        return mIssueSerial;
    }

    public void setIssueSerial(String issueSerial) {
        mIssueSerial = issueSerial;
    }

    public String getOrderId() {
        return mOrderId;
    }

    public void setOrderId(String orderId) {
        mOrderId = orderId;
    }

    public String getPosSep() {
        return mPosSep;
    }

    public void setPosSep(String posSep) {
        mPosSep = posSep;
    }

    public String getTranNumber() {
        return mTranNumber;
    }

    public void setTranNumber(String tranNumber) {
        mTranNumber = tranNumber;
    }

    public String getTxnDate() {
        return mTxnDate;
    }

    public void setTxnDate(String txnDate) {
        mTxnDate = txnDate;
    }

    public String getTxnTime() {
        return mTxnTime;
    }

    public void setTxnTime(String txnTime) {
        mTxnTime = txnTime;
    }

    public String getOrderType() {
        return mOrderType;
    }

    public void setOrderType(String orderType) {
        mOrderType = orderType;
    }

    public int getApplyDuration() {
        return mApplyDuration;
    }

    public void setApplyDuration(int applyDuration) {
        mApplyDuration = applyDuration;
    }

    public String getExtraPay() {
        return mExtraPay;
    }

    public void setExtraPay(String extraPay) {
        mExtraPay = extraPay;
    }

    /**
     * 添加数据
     */
    public static void add2Sql(DebitOrderInfoSql debitOrderInfoSql){

        DebitOrderInfoSql lDebitOrderInfoSql = new DebitOrderInfoSql();
        lDebitOrderInfoSql.setCityCode(debitOrderInfoSql.mCityCode);
        lDebitOrderInfoSql.setCardMType(debitOrderInfoSql.mCardMType);
        lDebitOrderInfoSql.setCardSType(debitOrderInfoSql.mCardSType);
        lDebitOrderInfoSql.setPayMoney(debitOrderInfoSql.mPayMoney);
        lDebitOrderInfoSql.setCardDeposit(debitOrderInfoSql.mCardDeposit);
        lDebitOrderInfoSql.setOrigAmt(debitOrderInfoSql.mOrigAmt);
        lDebitOrderInfoSql.setBalanceLeftMoney(debitOrderInfoSql.mBalanceLeftMoney);
        lDebitOrderInfoSql.setTAC(debitOrderInfoSql.TAC);
        lDebitOrderInfoSql.setKeyVersion(debitOrderInfoSql.mKeyVersion);
        lDebitOrderInfoSql.setAuthSeq(debitOrderInfoSql.mAuthSeq);
        lDebitOrderInfoSql.setIssueSerial(debitOrderInfoSql.mIssueSerial);
        lDebitOrderInfoSql.setOrderId(debitOrderInfoSql.mOrderId);
        lDebitOrderInfoSql.setPosSep(debitOrderInfoSql.mPosSep);
        lDebitOrderInfoSql.setTranNumber(debitOrderInfoSql.mTranNumber);
        lDebitOrderInfoSql.setTxnDate(debitOrderInfoSql.mTxnDate);
        lDebitOrderInfoSql.setTxnTime(debitOrderInfoSql.mTxnTime);
        lDebitOrderInfoSql.setOrderType(debitOrderInfoSql.mOrderType);
        lDebitOrderInfoSql.setApplyDuration(debitOrderInfoSql.mApplyDuration);
        lDebitOrderInfoSql.setExtraPay(debitOrderInfoSql.mExtraPay);
        lDebitOrderInfoSql.save();
    }

    public static void add2Sql4Refund(DebitOrderInfoSql debitOrderInfoSql){
        DebitOrderInfoSql lDebitOrderInfoSql = new DebitOrderInfoSql();
        lDebitOrderInfoSql.mActionType = debitOrderInfoSql.mActionType;
        lDebitOrderInfoSql.mCityCode = debitOrderInfoSql.mCityCode;
        lDebitOrderInfoSql.mCardMType = debitOrderInfoSql.mCardMType;
        lDebitOrderInfoSql.mCardSType = debitOrderInfoSql.mCardSType;
        lDebitOrderInfoSql.mIssueSerial = debitOrderInfoSql.mIssueSerial;
        lDebitOrderInfoSql.mCardSellDate = debitOrderInfoSql.mCardSellDate;
        lDebitOrderInfoSql.mTranNumber = debitOrderInfoSql.mTranNumber;
        lDebitOrderInfoSql.mBalanceLeftMoney = debitOrderInfoSql.mBalanceLeftMoney;
        lDebitOrderInfoSql.mCardDeposit = debitOrderInfoSql.mCardDeposit;
        lDebitOrderInfoSql.mTxnDate = debitOrderInfoSql.mTxnDate;
        lDebitOrderInfoSql.mTxnTime = debitOrderInfoSql.mTxnTime;
        lDebitOrderInfoSql.mPosSep = debitOrderInfoSql.mPosSep;
        lDebitOrderInfoSql.mAuthSeq = debitOrderInfoSql.mAuthSeq;
        lDebitOrderInfoSql.mOrderId = debitOrderInfoSql.mOrderId;
        lDebitOrderInfoSql.mSettDate = debitOrderInfoSql.mSettDate;
        lDebitOrderInfoSql.mRefundId = debitOrderInfoSql.mRefundId;
        lDebitOrderInfoSql.save();
    }


    public static List<DebitOrderInfoSql> find2Sql(){
        return DataSupport.findAll(DebitOrderInfoSql.class);
    }

}
