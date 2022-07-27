package com.manikala.shop.service;

import com.manikala.shop.dao.BucketRepository;
import com.manikala.shop.dao.ProductRepository;
import com.manikala.shop.obj.Bucket;
import com.manikala.shop.obj.Product;
import com.manikala.shop.obj.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BucketServiceImpl implements BucketService {
    private final BucketRepository bucketRepository;
    private final ProductRepository productRepository;

    public BucketServiceImpl(BucketRepository bucketRepository, ProductRepository productRepository) {
        this.bucketRepository = bucketRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Bucket createBucket (User user, List<Long> productIds) {
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product> productList = getCollectRefProductsByIds(productIds);
        bucket.setProduct(productList);
        return bucketRepository.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds (List<Long> productIds) {
        return productIds.stream()
                .map(productRepository::getOne)
                .collect(Collectors.toList());
        // getOne вытаскивает ссылку на объект, findById - вытаскивает сам объект
    }

    @Override
    public void addProducts (Bucket bucket, List<Long> productIds) {
        List<Product> products = bucket.getProduct();
        List<Product> newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProduct(newProductList);
        bucketRepository.save(bucket);
    }



}


