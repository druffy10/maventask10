public class NotFoundException extends RuntimeException {
    public NotFoundException(int id) {
        super("Element with ID: " + id + " not found");
    }
}