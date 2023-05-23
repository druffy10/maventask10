public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(Product product) {
        super("Element with ID " + product.getId() + " already exists");
    }
}
