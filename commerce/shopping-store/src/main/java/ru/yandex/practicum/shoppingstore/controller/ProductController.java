package ru.yandex.practicum.shoppingstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.shoppingstore.dto.ProductDto;
import ru.yandex.practicum.shoppingstore.model.ProductCategory;
import ru.yandex.practicum.shoppingstore.model.QuantityState;
import ru.yandex.practicum.shoppingstore.model.SetProductQuantityStateRequest;
import ru.yandex.practicum.shoppingstore.service.ProductService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/shopping-store")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @GetMapping
    public Page<ProductDto> getProducts(@RequestParam ProductCategory category, Pageable pageable) {
        return service.getProductsByCategory(category, pageable);
    }

    @PutMapping
    public ProductDto createNewProduct(@RequestBody ProductDto dto) {
        return service.createProduct(dto);
    }

    @PostMapping
    public ProductDto updateProduct(@RequestBody ProductDto dto) {
        return service.updateProduct(dto);
    }

    @PostMapping("/removeProductFromStore")
    public boolean removeProduct(@RequestBody UUID productId) {
        return service.removeProductFromStore(productId);
    }

    @PostMapping("/quantityState")
    public boolean setProductQuantityState(@RequestParam UUID productId,
                                           @RequestParam String quantityState) {
        return service.updateProductQuantityState(
                new SetProductQuantityStateRequest(productId, QuantityState.valueOf(quantityState))
        );
    }

    @GetMapping("/{productId}")
    public ProductDto getProduct(@PathVariable UUID productId) {
        return service.getProductById(productId).orElseThrow(() -> new IllegalArgumentException("Product not found"));
    }
}
