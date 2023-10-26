package exercise.controller;

import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.stream.Collectors;

import exercise.repository.ProductRepository;
import exercise.dto.ProductDTO;
import exercise.dto.ProductCreateDTO;
import exercise.mapper.ProductMapper;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Iterable<ProductDTO> index() {
        return productRepository
                   .findAll()
                   .stream()
                   .map(product -> productMapper.map(product)).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ProductDTO create(@RequestBody ProductCreateDTO productCreateDTO) {
        return productMapper.map(productRepository.save(productMapper.map(productCreateDTO)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO update(@RequestBody ProductUpdateDTO productUpdateDTO, @PathVariable Long id) {
        Product fromBase = productRepository.findById(id).orElseThrow();

        productMapper.update(productUpdateDTO, fromBase);

        Product product = productRepository.save(fromBase);

        return productMapper.map(product);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductDTO show(@PathVariable Long id) {
        return productMapper.map(productRepository.findById(id).orElseThrow());
    }
    // END
}
