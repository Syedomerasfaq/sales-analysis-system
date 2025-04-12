package com.salesanalytics.sales_analysis_system.service.Impl;

import com.opencsv.CSVReader;
import com.salesanalytics.sales_analysis_system.entity.*;
import com.salesanalytics.sales_analysis_system.repository.*;
import com.salesanalytics.sales_analysis_system.service.DataLoadService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class DataLoadServiceImpl implements DataLoadService {

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
            String[] line;

            reader.readNext();

            while ((line = reader.readNext()) != null) {

                String customerId = line[2];
                Long orderId = Long.valueOf(line[0]);
                String productId = line[1];
                String productName = line[3];
                String categoryName = line[4];
                String dateOfSale = line[6];
                String quantitySold = line[7];
                String unitPrice = line[8];
                String discount = line[9];
                String shippingCost = line[10];
                String paymentMethod = line[11];
                String customerName = line[12];
                String customerEmail = line[13];
                String customerAddress = line[14];
                String regionName = line[5];


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
                        .orElseGet(() -> new Customer(customerId,customerName, customerEmail, customerAddress));
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
            }
        }
    }
}
