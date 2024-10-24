package calculator.util;

import calculator.constants.DelimiterConstants;
import calculator.domain.UserInput;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    public static boolean hasCustomDelimiter(String string){
        if(string == null || string.length() < DelimiterConstants.CUSTOM_DELIMITER_BOUND_LENGTH+1) {
            return false;
        }

        if(!string.startsWith(DelimiterConstants.CUSTOM_DELIMITER_PREFIX)) {
            return false;
        }

        int endIndex = string.indexOf(DelimiterConstants.CUSTOM_DELIMITER_SUFFIX, 2);
        return endIndex > 2;
    }

    public static String extractCustomDelimiter(String string){
        StringBuilder delimiter = new StringBuilder();

        for(int index = 2; index < string.length()-1; index++){
            if(string.startsWith(DelimiterConstants.CUSTOM_DELIMITER_SUFFIX, index)) {
                break;
            }

            delimiter.append(string.charAt(index));
        }

        return delimiter.toString();
    }

    public static List<Integer> parseNumbersFromInput(UserInput userInput) {
        List<Integer> result = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        String string = userInput.input();
        List<String> delimiters = userInput.delimiters();

        int delimiterLength = userInput.hasCustomDelimiter() ? delimiters.getFirst().length() : 1;
        int index = userInput.hasCustomDelimiter() ? delimiterLength + DelimiterConstants.CUSTOM_DELIMITER_BOUND_LENGTH : 0;

        while (index < string.length()) {
            if (matchesDelimiter(string, index, delimiters)) {
                if(!number.isEmpty()){
                    result.add(Integer.parseInt(number.toString()));
                    number = new StringBuilder();
                }
                index += delimiterLength;
                continue;
            }
            number.append(string.charAt(index++));
        }

        if (!number.isEmpty()) {
            result.add(Integer.parseInt(number.toString()));
        }
        return result;
    }

    private static boolean matchesDelimiter(String string, int start, List<String> delimiters){
        for(String delimiter : delimiters) {
            if(string.startsWith(delimiter,start)) {
                return true;
            }
        }

        return false;
    }
}
