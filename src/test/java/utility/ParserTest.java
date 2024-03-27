package utility;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
class ParserTest {

    /**
     * Tests the behaviour of the parseDate function with a correctly formatted string.
     */
    @Test
    void parseDate_correctDateInput_returnDate() {
        LocalDate result = Parser.parseDate("08-03-2024");
        LocalDate expected = LocalDate.of(2024, 3, 8);
        assertEquals(expected, result);
    }

    /**
     * Tests the behaviour of the parseDate function with an incorrectly formatted string.
     */
    @Test
    void parseDate_incorrectDateInput_throwException () {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setErr(new PrintStream(outputStream));
        String invalidDate = "2024-03-08";
        Parser.parseDate(invalidDate);
        String errorMessage = outputStream.toString().trim();
        assertTrue(errorMessage.contains("Error parsing date"));
        System.setErr(System.err);
    }

    @Test
    public void validateDateInput_validDate_noExceptionThrown() {
        String validDate = "09-11-2024";
        assertDoesNotThrow(() -> {
            Parser.validateDateInput(validDate);
        });
    }

    @Test
    public void validateDateInput_invalidDayFormat_throwInvalidInputException() {
        String invalidDate = "9-11-2024";

        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidMonthFormat_throwInvalidInputException() {
        String invalidDate = "9-1-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidYearFormat_throwInvalidInputException() {
        String invalidDate = "9-11-24";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_illegalDayNumber_throwInvalidInputException() {
        String invalidDate = "32-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_zeroDayNumber_throwInvalidInputException() {
        String invalidDate = "00-11-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_illegalMonthNumber_throwInvalidInputException() {
        String invalidDate = "09-13-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_zeroMonthNumber_throwInvalidInputException() {
        String invalidDate = "09-00-2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_wrongDateDelimiter_throwInvalidInputException() {
        String invalidDate = "09/12/2024";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }

    @Test
    public void validateDateInput_invalidDateParameters_throwInvalidInputException() {
        String invalidDate = "09/12";
        assertThrows(CustomExceptions.InvalidInput.class, () -> {
            Parser.validateDateInput(invalidDate);
        });
    }
}
