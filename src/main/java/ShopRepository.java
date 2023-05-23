public class ShopRepository {
    private Product[] products = new Product[0];

    private Product[] addToArray(Product[] current, Product product) {
        Product[] tmp = new Product[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = product;
        return tmp;
    }

    public void add(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                throw new AlreadyExistsException(product);
            }
        }
        products = addToArray(products, product);
    }

    public Product[] findAll() {
        return products;
    }


    public Product findById(int id) {
        for (int i = 0; i < products.length; i++) {
            Product product = products[i];
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void removeById(int id) {
        Product productToRemove = findById(id);
        if (productToRemove == null) {
            throw new NotFoundException(id);
        }
        Product[] tmp = new Product[products.length - 1];
        int copyToIndex = 0;
        for (Product product : products) {
            if (product.getId() != id) {
                tmp[copyToIndex] = product;
                copyToIndex++;
            }
        }
        products = tmp;
    }
}