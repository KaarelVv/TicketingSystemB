package ee.sda.ticketingsystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class NoBannedWordsValidator implements ConstraintValidator<NoBannedWords,String> {

    private final List<String> bannedWords = Arrays.asList("badword1", "badword2", "badword3"); // Hardcoded list for demonstration

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) return true; // Null values are valid. Use @NotNull if you want it to be mandatory.
        return bannedWords.stream().noneMatch(value::contains);
    }
}
