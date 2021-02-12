package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private Product first = new Book(1, "XXX", 100, "Gogl", 100, 2020);
    private Product second = new TShirt(2, "Summer", 200, "blue", "XS");
    private Product third = new Book(3, "YYY", 300, "Push", 300, 1988);
    private Product fourth = new TShirt(4, "HHH", 500, "white", "XL");

    @Test
    void shouldRemoveById() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        ProductManager managers = new ProductManager(repository);
        int id = 2;
        managers.removeById(id);
        Product[] actual = managers.getAll();
        Product[] expected = new Product[]{fourth, third,first};
        assertArrayEquals(actual, expected);

    }

    @Test
    void shouldRemoveByIdException(){
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        ProductManager managers = new ProductManager(repository);
        int id = 9;
        assertThrows(NotFoundException.class, () -> managers.removeById(id));

    }
}