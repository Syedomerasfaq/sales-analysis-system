package com.salesanalytics.sales_analysis_system.service.Impl;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.salesanalytics.sales_analysis_system.dto.SalesDataDto;
import com.salesanalytics.sales_analysis_system.entity.*;
import com.salesanalytics.sales_analysis_system.repository.*;
import com.salesanalytics.sales_analysis_system.service.DataLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class DataLoadServiceImpl implements DataLoadService {

    private static final Logger log = LoggerFactory.getLogger(DataLoadServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RegionRepository regionRepository;

    @Transactional
    @Override
    public void loadCSVData(InputStream inputStream) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(inputStream))) {
            CsvToBean<SalesDataDto> csvToBean = new CsvToBeanBuilder<SalesDataDto>(reader)
                    .withType(SalesDataDto.class)
                    .withSkipLines(1)  // Skip header row
                    .build();

            List<SalesDataDto> salesRecords = csvToBean.parse();
            for (SalesDataDto record : salesRecords) {
                try {
                    String customerId = record.getCustomerId();
                    Long orderId = record.getOrderId();
                    String productId = record.getProductId();
                    String productName = record.getProductName();
                    String categoryName = record.getCategoryName();
                    String dateOfSale = record.getDateOfSale();
                    String quantitySold = record.getQuantitySold();
                    String unitPrice = record.getUnitPrice();
                    String discount = record.getDiscount();
                    String shippingCost = record.getShippingCost();
                    String paymentMethod = record.getPaymentMethod();
                    String customerName = record.getCustomerName();
                    String customerEmail = record.getCustomerEmail();
                    String customerAddress = record.getCustomerAddress();
                    String regionName = record.getRegionName();

                    Category category = categoryRepository.findByName(categoryName).orElse(new Category(categoryName));
                    categoryRepository.save(category);

                    Product product = productRepository.findByName(productId).orElse(new Product());
                    product.setName(productName);
                    product.setId(productId);
                    product.setUnitPrice(new BigDecimal(unitPrice));
                    product.setDiscount(new BigDecimal(discount));
                    product.setCategory(category);
                    productRepository.save(product);

                    Customer customer = customerRepository.findById(customerId)
                            .orElseGet(() -> new Customer(customerId, customerName, customerEmail, customerAddress));
                    customerRepository.save(customer);

                    Region region = regionRepository.findById(regionName)
                            .orElseGet(() -> {
                                Region newRegion = new Region();
                                newRegion.setName(regionName);
                                return regionRepository.save(newRegion);
                            });

                    Optional<Order> existingOrder = orderRepository.findById(orderId);
                    if (existingOrder.isEmpty()) {
                        Order order = new Order();
                        order.setId(orderId);
                        order.setDateOfSale(LocalDate.parse(dateOfSale));
                        order.setShippingCost(new BigDecimal(shippingCost));
                        order.setPaymentMethod(paymentMethod);
                        order.setCustomer(customer);
                        order.setRegion(region);
                        orderRepository.save(order);

                        OrderDetail orderDetail = new OrderDetail();
                        orderDetail.setOrder(order);
                        orderDetail.setProduct(product);
                        orderDetail.setQuantitySold(Integer.parseInt(quantitySold));
                        orderDetailRepository.save(orderDetail);
                    }
                } catch (Exception ex) {
                    log.error("Error processing row: {}", ex.getMessage(), ex);
                }
            }
            log.info("CSV data load completed successfully.");
        }
        catch (Exception e) {
            log.error("Failed to load CSV data", e);
            throw new Exception("CSV data load failed: " + e.getMessage(), e);
        }
    }
}
