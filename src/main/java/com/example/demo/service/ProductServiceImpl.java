package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductResult;
import com.example.demo.repository.ProductSearchRepositoryV2;
import com.example.demo.service.dto.ProductSearchDTO;
import com.example.demo.service.dto.ProductSearchRequest;
import com.example.demo.service.mapper.ProductMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

//    @Autowired
//    private ProductSearchRepositoryV2 productSearchRepository;

    @Autowired
    private ProductRepository productRepository;

    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public List<ProductSearchDTO> search(ProductSearchRequest productSearchRequest) {
        ProductResult productResult = productRepository.search(productSearchRequest);
        if (productResult != null && !CollectionUtils.isEmpty(productResult.getProducts())) {
            return mapper.map(productResult.getProducts());
        }
        return Collections.emptyList();
    }

}
