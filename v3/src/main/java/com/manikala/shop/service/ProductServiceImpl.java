package com.manikala.shop.service;

import com.manikala.shop.dao.ProductRepository;
//import com.manikala.shop.service.ProductService;
import com.manikala.shop.dto.ProductDTO;
import com.manikala.shop.mapper.ProductMapper;
import com.manikala.shop.obj.Bucket;
import com.manikala.shop.obj.Product;
import com.manikala.shop.obj.User;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductMapper mapper = ProductMapper.MAPPER; //Подключаем маппер из продукт маппера

    private final ProductRepository productRepository;
    private final UserService userService;
    private final BucketService bucketService;
    private final SimpMessagingTemplate template;

    public ProductServiceImpl(ProductRepository productRepository, UserService userService, BucketService bucketService, SimpMessagingTemplate template) {
        this.productRepository = productRepository;
        this.userService = userService;
        this.bucketService = bucketService;
        this.template = template;
    }

    @Override
    public List<ProductDTO> getAll() {
        return mapper.fromProductList(productRepository.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket (Long productId, String username) {
        User user = userService.findByName(username); // ищем пользователя по юзер нейм
        if (user == null){
            throw new RuntimeException("User not found - " + username);
        }

        Bucket bucket = user.getBucket(); // у юзера ищем бакет
        if (bucket == null) {
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId)); // если бакет нул то мы ее создадим
            user.setBucket(newBucket);// устанавливаем бакет к юзеру
            userService.save(user);
        } else {
            bucketService.addProducts(bucket, Collections.singletonList(productId));
        }

    }

    @Override
    @Transactional
    public void addProduct(ProductDTO dto) {
        Product product = mapper.toProduct(dto);
        Product savedProduct = productRepository.save(product);

        template.convertAndSend("/topic/products",
                ProductMapper.MAPPER.fromProduct(savedProduct));
    }

    @Override
    public ProductDTO getById(Long id) {
        Product product = productRepository.findById(id).orElse(new Product());
        return ProductMapper.MAPPER.fromProduct(product);
    }

}

