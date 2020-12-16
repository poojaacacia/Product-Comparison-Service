package com.relayr.productcomparison;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
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


    @Value("${product.path.text}")
    private String txtPath;

    public static String TXT_PATH ;

    @Value("${product.path.text}")
    public void setTxtPath(String txtPath){
        TXT_PATH = txtPath;
    }
    @Value("${product.path.xml}")
    private String XMLPath;

    public static String XML_PATH;

    @Value("${product.path.xml}")
    public void setXMLPath(String XMLPath){
        XML_PATH = XMLPath;
    }
    @Value("${product.path.csv}")
    private String CSVPath;

    public static String CSV_PATH;

    @Value("${product.path.csv}")
    public void setCSVPath(String CSVPath){
        CSV_PATH = CSVPath;
    }
}
