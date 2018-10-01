package com.backend;

import com.backend.enums.TransactionType;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class App
{
    private static final String MSG_TOO_FEW_ARGUMENTS   = "too few arguments";
    static final String MSG_TOO_MANY_ARGUMENTS = "too many arguments";
    private static final String MSG_FIRST_PARAM_NOT_USER_ID = "first param is not numberic user id";
    private static final String JSON_STRING_IS_INVALID = "json is invalid";
    static final String USAGE = "Usage: \n./application <user_id> add <transaction_json>\n./application <user_id> <transaction_id>" +
            "\n./application <user_id> list\n./application <user_id> sum\n";

    public static void main(String... args )
    {

        Injector injector = Guice.createInjector(new TransactionModule());
        TransactionService service = injector.getInstance(TransactionService.class);

        int numberOfArguments = args.length;

        validateCorrectNumberOfArgs(numberOfArguments);
        TransactionType type = TransactionType.INVALID;

        for (int i = 0; i < numberOfArguments; i++) {
            if (i == 0 && !isInteger(args[i])) {
                exitWithError(MSG_FIRST_PARAM_NOT_USER_ID);
            }
            if(i == 1) {
                type = setTransactionType(args[i]);
            }
        }

        String userId = args[0];
        switch (type) {
            case ADD:
                if (args.length < 3) {
                    exitWithError("No cumple con el numero correcto de argumentos");
                }
                String parsedDoubleQuotes = args[2].replaceAll("\"", "\\\"");
                JSONObject jsonObject = getJsonObject(parsedDoubleQuotes);

                if (jsonObject != null) {
                    if (!service.getAdder().add(userId,jsonObject)) {
                        System.out.printf("ERROR adding transaction");
                    }
                }
                break;
            case SHOW:
                System.out.println(service.getSearcher().search(userId, args[1]));
                break;
            case LIST:
                System.out.println(Arrays.toString(service.getLister().list(userId)));
                break;
            case SUM:
                System.out.println(service.getCalculate().sum(userId));
                break;
        }
        System.exit(0);
    }

    static TransactionType setTransactionType(String arg) {
        TransactionType type;
        switch (arg) {
            case "add":
                type = TransactionType.ADD;
                break;
            case "list":
                type = TransactionType.LIST;
                break;
            case "sum":
                type = TransactionType.SUM;
                break;
            default:
                type = TransactionType.SHOW;
                break;
        }
        return type;
    }

    private static JSONObject getJsonObject(String json) {
        try {
            return new JSONObject(json);
        } catch (JSONException ex) {
            exitWithError(JSON_STRING_IS_INVALID);
        }
        return null;
    }

    private static void validateCorrectNumberOfArgs(int numberOfArguments) {
        if(numberOfArguments < 2) {
            exitWithError(MSG_TOO_FEW_ARGUMENTS);
        } else if (numberOfArguments > 3) {
            exitWithError(MSG_TOO_MANY_ARGUMENTS);
        }
    }

    private static void exitWithError(String errorMessage) {
        System.out.println(errorMessage);
        System.out.println(USAGE);
        System.exit(1);
    }

    private static boolean isInteger(String input) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( NumberFormatException e ) {
            return false;
        }
    }
}
