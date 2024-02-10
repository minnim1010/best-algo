package com.ssafy.bestalgo.global.constraints;

public final class RequestValidation {

    public static final int MIN_ID = 1;
    public static final int MIN_MEMBER_NAME_LENGTH = 1;
    public static final int MAX_MEMBER_NAME_LENGTH = 10;
    public static final int MIN_MEMBER_PASSWORD_LENGTH = 1;
    public static final int MAX_MEMBER_PASSWORD_LENGTH = 10;
    public static final int MIN_PROBLEM_NAME_LENGTH = 1;
    public static final int MAX_PROBLEM_NAME_LENGTH = 30;
    public static final int MIN_PROBLEM_CATEGORY_LENGTH = 1;
    public static final int MAX_PROBLEM_CATEGORY_LENGTH = 30;
    public static final int MIN_CODE_CONTENT_LENGTH = 1;
    public static final int MAX_CODE_CONTENT_LENGTH = 20000;
    public static final int MIN_CODE_TYPE_LENGTH = 1;
    public static final int MAX_CODE_TYPE_LENGTH = 10;
    public static final int MIN_ADMIN_PASSWORD_LENGTH = 10;
    public static final int MAX_ADMIN_PASSWORD_LENGTH = 30;

    private RequestValidation() {
    }
}
