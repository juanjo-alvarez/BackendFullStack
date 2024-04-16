package com.campusdual.service;

import com.campusdual.api.IProductService;
import com.campusdual.model.Product;
import com.campusdual.model.dao.ProductDao;
import com.campusdual.model.dto.ProductDto;
import com.campusdual.model.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("ProductService")
@Lazy
public class ProductService implements IProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public ProductDto queryProduct(ProductDto productDto) {
        //Mapeo el DTO a Entidad para poder usarlo en la consulta
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        //Lamo al DAO para que haga la consulta con la entidad
        Product productResult = productDao.getReferenceById(product.getId());
        //Mapeo la Entidad que devuelve el DAO a un DTO para generar el resultado
        ProductDto resultado = ProductMapper.INSTANCE.toDto(productResult);
        return resultado;
    }

    public List<ProductDto> queryProductsByName(ProductDto productDto){
        String name = productDto.getName();
        if(!name.contains("%")){
            name = "%"+name+"%";
        }
        List<Product> productList = productDao.findByNameLike(name);
        return ProductMapper.INSTANCE.toDtoList(productList);
    }

    @Override
    public List<ProductDto> queryProductsByStock(ProductDto productDto) {
        Integer stock = productDto.getStock();
        List<Product> productList = productDao.findByStockIs(stock);
        return ProductMapper.INSTANCE.toDtoList(productList);
    }

    @Override
    public List<ProductDto> queryAllProducts() {
        //LLamada que obtiene todas las entidades de base de datos de la tabla Products
        List<Product> productList = productDao.findAll();
        //Mapeamos este resultado a DTO
        List<ProductDto> result = ProductMapper.INSTANCE.toDtoList(productList);
        return result;
    }

    @Override
    public int insertProduct(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        productDao.saveAndFlush(product);
        return product.getId();
    }

    @Override
    public int updateProduct(ProductDto productDto) {
        return insertProduct(productDto);
    }

    @Override
    public int deleteProduct(ProductDto productDto) {
        //TODO probar otras implementaciones
        int id = productDto.getId();
        Product product = ProductMapper.INSTANCE.toEntity(productDto);
        productDao.delete(product);
        return id;
    }
}
