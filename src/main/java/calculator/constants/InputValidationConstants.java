package calculator.constants;

public class InputValidationConstants {
    public static final String CONTAINS_NUMBERS_PATTERN = ".*[0-9].*";
    public static final String CUSTOM_DELIMITER_PATTERN_PREFIX = "^([0-9]*";
    public static final String CUSTOM_DELIMITER_PATTERN_SUFFIX = ")*[0-9]*$";
    public static final String NORMAL_DELIMITER_INPUT_PATTERN = "^[:,0-9]*$";

    private InputValidationConstants(){
        throw new RuntimeException("Constants class cannot be instantiated");
    }
}
