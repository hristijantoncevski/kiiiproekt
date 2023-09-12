package mk.ukim.finki.kiiiproekt.model.exceptions;

public class UsernameWithThatCnAlreadyExists extends Exception {
    public UsernameWithThatCnAlreadyExists(String CN) {
        super(String.format("User with CN: %s already exists", CN));
    }
}
