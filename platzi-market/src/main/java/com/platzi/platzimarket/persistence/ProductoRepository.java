package com.platzi.platzimarket.persistence;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.repository.ProductRepository;
import com.platzi.platzimarket.persistence.CRUD.ProductoCrudRepositorio;
import com.platzi.platzimarket.persistence.entity.Producto;
import com.platzi.platzimarket.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepositorio productoCrudRepositorio;
    private ProductMapper mapper;
    @Override
    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepositorio.findAll();
        return mapper.toProducts(productos);
    }
    @Override
    public Optional<List<Product>> getByCategory(int categoryId) {
        List<Producto> productos = productoCrudRepositorio.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProducts(productos));
    }
    @Override
    public Optional<List<Product>> getScarseProducts(int quantity) {
        Optional<List<Producto>> productos = productoCrudRepositorio.findByCantidadStockLessThanAndEstado(quantity,true);
        return productos.map(prods -> mapper.toProducts(prods));
    }
    @Override
    public Optional<Product> getProduct(int idProduct) {
        return productoCrudRepositorio.findById(idProduct).map(producto -> mapper.toProduct(producto));

    }
    @Override
    public Product save(Product product) {
        Producto producto = mapper.toProducto(product);
        return mapper.toProduct( productoCrudRepositorio.save(producto));
    }
    @Override
    public void delete(int productId){
        productoCrudRepositorio.deleteById(productId);
    }

}
