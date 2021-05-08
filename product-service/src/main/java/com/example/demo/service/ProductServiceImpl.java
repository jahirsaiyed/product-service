package com.example.demo.service;

import com.example.demo.repository.Product;
import com.example.demo.repository.ProductJestClient;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductResult;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.ProductSearchRequest;
import com.example.demo.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductJestClient productJestClient;
    private final ProductRepository productRepository;
    private final MessagingService messagingService;

    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @Override
    public ProductSearchResultDTO search(ProductSearchRequest productSearchRequest) {
        ProductResult productResult = productJestClient.search(productSearchRequest);
        if (productResult != null && !CollectionUtils.isEmpty(productResult.getProducts())) {
            return mapper.map(productResult);
        }
        return new ProductSearchResultDTO();
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", productDTO);
        Product product = mapper.toEntity(productDTO);
        product = productRepository.save(product);
        ProductDTO result = mapper.toDto(product);
        messagingService.send(result);
        return result;
    }

    @Override
    public Optional<ProductDTO> partialUpdate(ProductDTO productDTO) {
        log.debug("Request to partially update Product : {}", productDTO);

        return productRepository
                .findById(productDTO.getId())
                .map(existingProduct -> {
                    mapper.partialUpdate(existingProduct, productDTO);
                    return existingProduct;
                })
                .map(product -> {
                    product = productRepository.save(product);
                    messagingService.send(mapper.toDto(product));
                    return product;
                })
                .map(mapper::toDto);
    }

    @Override
    public Optional<ProductDTO> findById(String id) {
        return productRepository.findById(id).map(mapper::toDto);
    }

}
