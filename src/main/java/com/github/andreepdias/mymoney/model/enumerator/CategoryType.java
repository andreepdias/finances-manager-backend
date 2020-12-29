package com.github.andreepdias.mymoney.model.enumerator;


public enum CategoryType {
    EXPENSE(1, "Expense"),
    INCOME(2, "Income");

    private int code;
    private String description;

    private CategoryType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static CategoryType toEnum(Integer code) {
        if(code == null) {
            return null;
        }
        for (CategoryType x : CategoryType.values()) {
            if(code.equals(x.getCode())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + code + " for enum CategoryType." );
    }

    public int getCode(){
        return code;
    }

    public String getDescription() {
        return description;
    }
}
