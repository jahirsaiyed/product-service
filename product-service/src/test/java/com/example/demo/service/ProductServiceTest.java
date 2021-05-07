package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductResult;
import com.example.demo.service.dto.ProductSearchRequest;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void search() {
        ProductSearchRequest productSearchRequest = new ProductSearchRequest();
        ProductResult repoResult = new ProductResult();
        Mockito.when(productRepository.search(Mockito.any(ProductSearchRequest.class)))
                .thenReturn(repoResult);
        ProductSearchResultDTO resultDTO = productService.search(productSearchRequest);
        assertEquals(repoResult, resultDTO);
    }

    private void assertEquals(ProductResult repoResult, ProductSearchResultDTO resultDTO) {
        repoResult.getProducts()
                .stream()
                .filter(Objects::nonNull)
                .forEach(p -> resultDTO.getProducts().stream()
                        .filter(productSearchDTO -> StringUtils.equals(p.getId(), String.valueOf(productSearchDTO.getId())))
                        .forEach(r -> {
                            Assertions.assertEquals(p.getBrand(), r.getBrand());
                            Assertions.assertEquals(p.getPhone(), r.getPhone());
                            Assertions.assertEquals(p.getPicture(), r.getPicture());
                            Assertions.assertEquals(p.getResolution(), r.getResolution());
                            Assertions.assertEquals(p.getSim(), r.getSim());
                        })
                );
    }
}
