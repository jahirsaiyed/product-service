package com.example.demo.service.mapper;

import com.example.demo.repository.Product;
import com.example.demo.repository.ProductResult;
import com.example.demo.service.ProductSearchResultDTO;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.dto.ProductSearchRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {
    ProductSearchRequest map(com.example.demo.web.request.ProfileSearchRequest request);
    @Mappings({
        @Mapping(target="release.priceEur", source="priceEur"),
        @Mapping(target="release.announceDate", source="announceDate")
    })
    Product map(ProductSearchRequest request);

    ProductSearchResultDTO map(ProductResult result);

}
