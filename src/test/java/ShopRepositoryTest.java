import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    private ShopRepository shopRepository;

    @BeforeEach
    public void setUp() {
        shopRepository = new ShopRepository();
        Product product1 = new Product(1, "Apple", 150);
        Product product2 = new Product(2, "Banana", 175);
        Product product3 = new Product(3, "Orange", 200);
        shopRepository.add(product1);
        shopRepository.add(product2);
        shopRepository.add(product3);
    }

    @Test
    public void testFindAllReturnsArrayWithCorrectLength() {
        Product[] products = shopRepository.findAll();
        Assertions.assertEquals(3, products.length);
    }

    @Test
    public void testFindByIdReturnsCorrectProduct() {
        Product product = shopRepository.findById(1);
        Assertions.assertEquals("Apple", product.getTitle());
        Assertions.assertEquals(150, product.getPrice());
    }

    @Test
    public void testFindByIdReturnsNullIfProductNotFound() {
        Product product = shopRepository.findById(4);
        Assertions.assertEquals(null, product);
    }

    @Test
    public void testRemoveByIdThrowsExceptionIfProductNotFound() {
        Assertions.assertThrows(NotFoundException.class, () -> shopRepository.removeById(4));
    }

    @Test
    public void testRemoveByIdRemovesProductWithCorrectId() {
        shopRepository.removeById(1);
        Product[] products = shopRepository.findAll();
        Assertions.assertEquals(2, products.length);
        Assertions.assertEquals("Banana", products[0].getTitle());
        Assertions.assertEquals("Orange", products[1].getTitle());
    }

    @Test
    public void testAddProductWithExistingIdThrowsAlreadyExistsException() {
        Product product4 = new Product(2, "Grapes", 250);
        Assertions.assertThrows(AlreadyExistsException.class, () -> shopRepository.add(product4));
    }

    @Test
    public void testAddNewProductToRepository() {
        Product product4 = new Product(4, "Grapes", 250);
        shopRepository.add(product4);
        Assertions.assertEquals(product4, shopRepository.findById(4));
    }
}