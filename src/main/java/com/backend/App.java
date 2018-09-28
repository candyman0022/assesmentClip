package com.backend;

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

        if(numberOfArguments < 2) {
            System.out.println(MSG_TOO_FEW_ARGUMENTS);
            System.out.println(USAGE);
            System.exit(1);
        } else if (numberOfArguments > 3) {
            System.out.println(MSG_TOO_MANY_ARGUMENTS);
            System.out.println(USAGE);
            System.exit(1);
        }

        for (int i = 0; i < numberOfArguments; i++) {
            if (i == 0 && !isInteger(args[i])) {
                System.out.println(MSG_FIRST_PARAM_NOT_USER_ID);
                System.out.println(USAGE);
                System.exit(1);
            }
        }

        System.exit(0);
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
