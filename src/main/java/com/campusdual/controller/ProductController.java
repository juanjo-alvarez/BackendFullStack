package com.campusdual.controller;

import com.campusdual.api.IProductService;
import com.campusdual.model.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController()
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping(value="/test")
    public String testController(){
        return "Products controller works!";
    }

    @PostMapping(value="/test")
    public String testHelowController(@RequestBody String name){
        return "Helow +"+name+",Products controller works!";
    }

    @PutMapping(value="/test")
    public String testController(@RequestBody String name){
        switch (name.toLowerCase()){
            case "juanjo": return "Hola profe";
            case "isa": return "Hola Isaura";
            case "angel": return "Hola José Ángel";
            case "ruli": return  "Hola Raúl";
            default:return "Hola "+name;
        }
    }

    @PostMapping("/get")
    public ProductDto queryProduct(@RequestBody ProductDto productDto){
        try{
            return productService.queryProduct(productDto);
        }catch (EntityNotFoundException e){
            ProductDto result = new ProductDto();
            result.setName("Producto no encontrado");
            return result;
        }
    }

    @PostMapping("/getname")
    public List<ProductDto> queryProductName(@RequestBody ProductDto productDto){
        return productService.queryProductsByName(productDto);
    }

    @PostMapping("/getstock")
    public List<ProductDto> queryProductStock(@RequestBody ProductDto productDto){
        return productService.queryProductsByStock(productDto);
    }

    @GetMapping("/getAll")
    public List<ProductDto> queryAllProducts(){
        return productService.queryAllProducts();
    }

    @PostMapping("/add")
    public int addProduct(@RequestBody ProductDto productDto){
        return productService.insertProduct(productDto);
    }

    @PutMapping("/update")
    public int updateProduct(@RequestBody ProductDto productDto){
        return productService.updateProduct(productDto);
    }

    @DeleteMapping("/delete")
    public int deleteProduct(@RequestBody ProductDto productDto){
        return productService.deleteProduct(productDto);
    }
}
