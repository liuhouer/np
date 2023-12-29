package cn.northpark.model;

public class Donates {
    private Integer id;

    private String addTime;

    private String productName;

    private String mercOrderId;

    private String alipayTransId;

    private String accountId;

    private String accountName;

    private String orderAmount;

    private String refundAmount;

    private String serviceCharge;

    private String tradingStatus;

    private String serviceRefund;

    private String merchantReceive;

    private String merchantOffers;

    private String branchOffice;

    private String bandOrderId;

    private String transactionType;

    private String transactionMeans;

    private String storeName;

    private String rewardMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime == null ? null : addTime.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getMercOrderId() {
        return mercOrderId;
    }

    public void setMercOrderId(String mercOrderId) {
        this.mercOrderId = mercOrderId == null ? null : mercOrderId.trim();
    }

    public String getAlipayTransId() {
        return alipayTransId;
    }

    public void setAlipayTransId(String alipayTransId) {
        this.alipayTransId = alipayTransId == null ? null : alipayTransId.trim();
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    public String getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount == null ? null : refundAmount.trim();
    }

    public String getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge == null ? null : serviceCharge.trim();
    }

    public String getTradingStatus() {
        return tradingStatus;
    }

    public void setTradingStatus(String tradingStatus) {
        this.tradingStatus = tradingStatus == null ? null : tradingStatus.trim();
    }

    public String getServiceRefund() {
        return serviceRefund;
    }

    public void setServiceRefund(String serviceRefund) {
        this.serviceRefund = serviceRefund == null ? null : serviceRefund.trim();
    }

    public String getMerchantReceive() {
        return merchantReceive;
    }

    public void setMerchantReceive(String merchantReceive) {
        this.merchantReceive = merchantReceive == null ? null : merchantReceive.trim();
    }

    public String getMerchantOffers() {
        return merchantOffers;
    }

    public void setMerchantOffers(String merchantOffers) {
        this.merchantOffers = merchantOffers == null ? null : merchantOffers.trim();
    }

    public String getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(String branchOffice) {
        this.branchOffice = branchOffice == null ? null : branchOffice.trim();
    }

    public String getBandOrderId() {
        return bandOrderId;
    }

    public void setBandOrderId(String bandOrderId) {
        this.bandOrderId = bandOrderId == null ? null : bandOrderId.trim();
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType == null ? null : transactionType.trim();
    }

    public String getTransactionMeans() {
        return transactionMeans;
    }

    public void setTransactionMeans(String transactionMeans) {
        this.transactionMeans = transactionMeans == null ? null : transactionMeans.trim();
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName == null ? null : storeName.trim();
    }

    public String getRewardMsg() {
        return rewardMsg;
    }

    public void setRewardMsg(String rewardMsg) {
        this.rewardMsg = rewardMsg == null ? null : rewardMsg.trim();
    }
}