package com.manikala.shop.service;

import com.manikala.shop.dao.BucketRepository;
import com.manikala.shop.dao.ProductRepository;
import com.manikala.shop.dto.BucketDTO;
import com.manikala.shop.dto.BucketDetailDTO;
import com.manikala.shop.obj.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository; //добавляем репозиторий корзины и продукта
    private final ProductRepository productRepository;
    private final UserService userService;
    private final OrderService orderService;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository, UserService userService, OrderService orderService) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.orderService = orderService;
    }


    @Override
    @Transactional
    public Bucket createBucket (User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProducts(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds (List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)//добавляем первый попавшийся продукт по id
                .collect(Collectors.toList());
        // getOne вытаскивает ссылку на объект, findById - вытаскивает сам объект
    }

    @Override
    @Transactional
    public void addProducts (Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProducts();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepository.save(bucket);
    }

    @Override
    public BucketDTO getBucketByUser (String name) { // чтобы правильно одсчитывалась сумма товаров
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null) {
            return new BucketDTO();
        }

        BucketDTO bucketDTO = new BucketDTO();
        Map<Long, BucketDetailDTO> mapByProductId= new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for (Product product : products) {
            BucketDetailDTO detail = mapByProductId.get(product.getId());
            if (detail == null) {
                mapByProductId.put(product.getId(), new BucketDetailDTO(product));

            }else {
                detail.setAmount(detail.getAmount().add(new BigDecimal("1.0")));
                detail.setSum(detail.getSum() + Double.parseDouble(product.getPrice().toString()));
            }

        }

        bucketDTO.setBucketDetails(new ArrayList<>(mapByProductId.values()));
        bucketDTO.aggregate();

        return bucketDTO;

    }


    @Override
    @Transactional
    public void commitBucketToOrder(String username) {
        User user = userService.findByName(username); //получаем корзину по юзеру
        if(user == null){
            throw new RuntimeException("User is not found");
        }
        Bucket bucket = user.getBucket();
        if(bucket == null || bucket.getProducts().isEmpty()){
            return; //возращаемся если корзины нет или она пуста
        }

        Order order = new Order();
        order.setUser(user);

        Map<Product, Long> productWithAmount = bucket.getProducts().stream() //берем все количество продуктов
                .collect(Collectors.groupingBy(product -> product, Collectors.counting())); //групируем все через стримы

        List<OrderDetails> orderDetails = productWithAmount.entrySet().stream()
                .map(pair -> new OrderDetails(order, pair.getKey(), pair.getValue())) // в том числе по деталям заказа
                .collect(Collectors.toList());

        BigDecimal total = new BigDecimal(orderDetails.stream()
                .map(detail -> detail.getPrice().multiply(detail.getAmount()))
                .mapToDouble(BigDecimal::doubleValue).sum());

        order.setDetails(orderDetails);
        order.setSum(total);


        orderService.saveOrder(order); //сохраянем наш заказ
        bucket.getProducts().clear(); //очищаем корзину после заказа
        bucketRepository.save(bucket); //и сохраняем
    }



}


