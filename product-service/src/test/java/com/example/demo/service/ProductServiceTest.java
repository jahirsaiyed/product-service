package com.example.demo.service;

import com.example.demo.repository.Product;
import com.example.demo.repository.ProductJestClient;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductResult;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.ProductSearchRequest;
import com.example.demo.utils.ProductUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;
import java.util.Optional;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductJestClient productSearchRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private MessagingService messagingService;

    @InjectMocks
    private ProductServiceImpl productService;
    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void init() {
        product = new Product();
        product.setId("1");
        product.setBrand("Brand");
        product.setPhone("Name");
        productDTO = new ProductDTO();
        productDTO.setBrand("Brand");
        productDTO.setPhone("Name");
        productDTO.setId("1");
    }

    @Test
    public void search() {
        ProductSearchRequest productSearchRequest = new ProductSearchRequest();
        ProductResult repoResult = new ProductResult();
        Mockito.when(productSearchRepository.search(Mockito.any(ProductSearchRequest.class)))
                .thenReturn(repoResult);
        ProductSearchResultDTO resultDTO = productService.search(productSearchRequest);
        assertEquals(repoResult, resultDTO);
    }

    @Test
    void createProduct() {
        when(productRepository.save(Mockito.any())).then(returnsFirstArg());
        ProductDTO save = productService.save(productDTO);
        verify(productRepository).save(product);
        verify(messagingService).send(productDTO);
        Assertions.assertEquals(productDTO, save);
    }

    @Test
    void partialUpdate() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));
        when(productRepository.save(Mockito.any())).then(returnsFirstArg());
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId("1");
        productDTO.setBrand("Brand1");
        ProductDTO save = productService.partialUpdate(productDTO).get();
        verify(productRepository).save(product);
        ProductDTO productDTOMessageSent = ProductUtils.copy(this.productDTO);
        productDTOMessageSent.setId(productDTO.getId());
        productDTOMessageSent.setBrand(productDTO.getBrand());
        verify(messagingService).send(productDTOMessageSent);
        Assertions.assertNotEquals(this.productDTO, save);
        save.setBrand(productDTO.getBrand());
        Assertions.assertEquals(productDTOMessageSent, save);
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
