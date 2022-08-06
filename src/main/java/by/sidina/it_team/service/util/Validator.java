package by.sidina.it_team.service.util;

public final class Validator {
    private Validator() {
    }
    private static final String EMAIL_REGEX = "[a-zA-Z]{1}[a-zA-Z\\d\\u002E\\u005F]+@([a-zA-Z]+\\u002E){1,2}[a-zA-Z]+";
    private static final String HOURS_REGEX = "^([0-9]|([1][0-9]|[2][0-4]))";
    private static final String PAYMENT_REGEX = "[\\d]+[.]?[0-9]{0,2}";
    private static final String NAME_SURNAME_REGEX = "[a-zA-Zа-яА-Я]+";
}
