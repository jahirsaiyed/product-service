package com.example.demo.service.mapper;

import com.example.demo.repository.Product;
import com.example.demo.service.dto.ProductSearchDTO;
import com.example.demo.service.dto.ProductSearchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ProductMapper {
    ProductSearchRequest map(com.example.demo.web.request.ProfileSearchRequest request);
    @Mappings({
        @Mapping(target="release.priceEur", source="priceEur"),
        @Mapping(target="release.announceDate", source="announceDate")
    })
    Product map(ProductSearchRequest request);

    ProductSearchDTO map(Product product);
    List<ProductSearchDTO> map(List<Product> product);

}
