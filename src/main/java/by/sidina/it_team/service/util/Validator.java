package by.sidina.it_team.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    private Validator() {
    }

    private static final String EMAIL_REGEX = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]+";
    private static final String TEXT_REGEX = "^.{0,255}$";
    private static final String DATE_REGEX = "^(19|20)\\d\\d([-])(0[1-9]|1[012])\\2(0[1-9]|[12][0-9]|3[01])$";
    private static final String NAME_REGEX = "[a-zA-Zа-яА-Я]{1,100}";
    private static final String POSITION_REGEX = "[1-9]{1}|1[0-1]{1}";
    private static final String USER_STATUS_REGEX = "[1-2]{1}";
    private static final String PROJECT_STATUS_REGEX = "[1-5]{1}";
    private static final String ROLE_REGEX = "[1-3]{1}";
    private static final String LEVEL_REGEX = "[1-4]{1}";
    private static final String PAYMENT_REGEX = "[\\d]+[.]?[0-9]{0,2}";

    public static boolean isValidEmail(String email) {
        boolean isValid;
        if (email != null && !email.isBlank()) {
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email);
            isValid = matcher.matches();
        } else {
            isValid = false;
        }
        return isValid;
    }

    public static boolean isValidPassword(String password) {
        if (password == null || password.isBlank()) {
            return false;
        }
        return password.matches(TEXT_REGEX);
    }

    public static boolean isValidDate(String date) {
        if (date == null || date.isBlank()) {
            return false;
        }
        return date.matches(DATE_REGEX);
    }

    public static boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            return false;
        }
        return name.matches(NAME_REGEX);
    }

    public static boolean isValidPosition(String position) {
        if (position == null || position.isBlank()) {
            return false;
        }
        return position.matches(POSITION_REGEX);
    }

    public static boolean isValidUserStatus(String userStatus) {
        if (userStatus == null || userStatus.isBlank()) {
            return false;
        }
        return userStatus.matches(USER_STATUS_REGEX);
    }

    public static boolean isValidRole(String role) {
        if (role == null || role.isBlank()) {
            return false;
        }
        return role.matches(ROLE_REGEX);
    }

    public static boolean isValidLevel(String level) {
        if (level == null || level.isBlank()) {
            return false;
        }
        return level.matches(LEVEL_REGEX);
    }

    public static boolean isValidPayment(String payment) {
        if (payment == null || payment.isBlank()) {
            return false;
        }
        return payment.matches(PAYMENT_REGEX);
    }

    public static boolean isValidProjectStatus(String projectStatus) {
        if (projectStatus == null || projectStatus.isBlank()) {
            return false;
        }
        return projectStatus.matches(PROJECT_STATUS_REGEX);
    }
}
