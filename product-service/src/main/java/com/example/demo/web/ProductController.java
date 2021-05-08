package com.example.demo.web;

import com.example.demo.service.ProductService;
import com.example.demo.service.dto.ProductDTO;
import com.example.demo.service.mapper.ProductMapper;
import com.example.demo.web.request.ProfileSearchRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@RestController
@RequestMapping(value = "/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ObjectMapper objectMapper;
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @PostMapping("/")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save Product : {}", productDTO);
        if (productDTO.getId() != null) {
            throw new IllegalArgumentException("A new product cannot already have an ID");
        }
        ProductDTO result = productService.save(productDTO);
        return ResponseEntity
                .created(new URI("/api/products/" + result.getId()))
                .body(result);
    }

    @PatchMapping(value = "/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<ProductDTO> partialUpdateProduct(
            @PathVariable(value = "id", required = false) final String id,
            @RequestBody ProductDTO productDTO) {
        log.debug("REST request to partial update Product partially : {}, {}", id, productDTO);
        if (productDTO.getId() == null) {
            throw new IllegalArgumentException("Invalid id");
        }
        if (!Objects.equals(id, productDTO.getId())) {
            throw new IllegalArgumentException("Invalid ID");
        }

        if (!productService.findById(id).isPresent()) {
            throw new IllegalArgumentException("Entity not found");
        }
        return productService.partialUpdate(productDTO)
                .map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @ApiOperation(value = "Search for product", notes = "This uri will search for products")
    private ResponseEntity<?> searchProduct(@RequestBody ProfileSearchRequest request) {
        return new ResponseEntity<>(productService.search(mapper.map(request)), HttpStatus.OK);
    }

    //    @GetMapping
//    private ResponseEntity<?> search() {
//        return new ResponseEntity<>(productSearchRepositoryV2.findAll(), HttpStatus.OK);
//    }
}
