package com.backend;

import com.backend.enums.TransactionType;

public class App
{
    static final String MSG_TOO_FEW_ARGUMENTS   = "too few arguments";
    static final String MSG_TOO_MANY_ARGUMENTS = "too many arguments";
    static final String MSG_FIRST_PARAM_NOT_USER_ID = "first param is not numberic user id";
    static final String USAGE = "Usage: \n./application <user_id> add <transaction_json>\n./application <user_id> <transaction_id>" +
            "\n";

    public static void main(String... args )
    {
        int numberOfArguments = args.length;

        validateCorrectNumberOfArgs(numberOfArguments);

        TransactionType type;

        for (int i = 0; i < numberOfArguments; i++) {
            if (i == 0 && !isInteger(args[i])) {
                exitWithError(MSG_FIRST_PARAM_NOT_USER_ID);
            }
            if(i == 1) {
                switch (args[i]) {
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
            }
        }

        System.exit(0);
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
