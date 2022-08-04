package com.manikala.shop.service;

import com.manikala.shop.dto.BucketDTO;
import com.manikala.shop.obj.Bucket;
import com.manikala.shop.obj.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long> productIds);

    void addProducts (Bucket bucket, List<Long> productIds);

    BucketDTO getBucketByUser (String name); // поиск карзины по юзеру

}
