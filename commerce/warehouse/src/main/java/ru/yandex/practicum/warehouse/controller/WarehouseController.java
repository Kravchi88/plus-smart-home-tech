package ru.yandex.practicum.warehouse.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.warehouse.dto.AddProductToWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.AddressDto;
import ru.yandex.practicum.warehouse.dto.BookedProductsDto;
import ru.yandex.practicum.warehouse.dto.NewProductInWarehouseRequest;
import ru.yandex.practicum.warehouse.dto.ShoppingCartDto;
import ru.yandex.practicum.warehouse.service.WarehouseService;

@RestController
@RequestMapping("/api/v1/warehouse")
@RequiredArgsConstructor
public class WarehouseController {

    private final WarehouseService service;

    @PutMapping
    public void registerNewProduct(@RequestBody NewProductInWarehouseRequest request) {
        service.registerNewProduct(request);
    }

    @PostMapping("/add")
    public void addProductQuantity(@RequestBody AddProductToWarehouseRequest request) {
        service.addProductQuantity(request);
    }

    @PostMapping("/check")
    public BookedProductsDto checkAvailability(@RequestBody ShoppingCartDto cart) {
        return service.checkAvailabilityAndBook(cart);
    }

    @GetMapping("/address")
    public AddressDto getWarehouseAddress() {
        return service.getWarehouseAddress();
    }
}
