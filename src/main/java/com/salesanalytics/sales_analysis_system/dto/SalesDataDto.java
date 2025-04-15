package com.salesanalytics.sales_analysis_system.dto;

import com.opencsv.bean.CsvBindByName;

public class SalesDataDto {

    @CsvBindByName(column = "Order ID")
    private Long orderId;

    @CsvBindByName(column = "Product ID")
    private String productId;

    @CsvBindByName(column = "Customer ID")
    private String customerId;

    @CsvBindByName(column = "Product Name")
    private String productName;

    @CsvBindByName(column = "Category")
    private String categoryName;

    @CsvBindByName(column = "Region")
    private String regionName;

    @CsvBindByName(column = "Date of Sale")
    private String dateOfSale;

    @CsvBindByName(column = "Quantity Sold")
    private String quantitySold;

    @CsvBindByName(column = "Unit Price")
    private String unitPrice;

    @CsvBindByName(column = "Discount")
    private String discount;

    @CsvBindByName(column = "Shipping Cost")
    private String shippingCost;

    @CsvBindByName(column = "Payment Method")
    private String paymentMethod;

    @CsvBindByName(column = "Customer Name")
    private String customerName;

    @CsvBindByName(column = "Customer Email")
    private String customerEmail;

    @CsvBindByName(column = "Customer Address")
    private String customerAddress;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDateOfSale() {
        return dateOfSale;
    }

    public void setDateOfSale(String dateOfSale) {
        this.dateOfSale = dateOfSale;
    }

    public String getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(String quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(String shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }
}
