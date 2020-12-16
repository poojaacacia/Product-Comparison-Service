package com.relayr.productcomparison;

public class Constants {
    public enum FILE_TYPE {
        CSV(1), XML(2), TEXT(3);
        int type;

        FILE_TYPE(int type) {
            this.type = type;
        }
    }
    public enum CURRENCY {
        USD("USD"), INR("INR"), EURO("EURO");
        private String name;

        CURRENCY(String name) {
            this.name=name;
        }
        public String getName() {
            return name;
        }
    }
}
