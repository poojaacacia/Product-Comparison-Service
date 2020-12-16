package com.relayr.productcomparison;

import com.relayr.productcomparison.exception.NotImplementedDataSource;
import com.relayr.productcomparison.model.ProductWebsiteDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This functional interface reads and parses data into list according to the file type,
 * Currently on TEXT data source is implemented but more data sources can be included in the future
 * The text files can be stores in an FTP location from where it can read from.
 */
@FunctionalInterface
public interface ProductReader {

    List<ProductWebsiteDetails> read(String filePath) throws IOException;


    static List<ProductWebsiteDetails> readFile(Constants.FILE_TYPE fileType) throws IOException {
        switch (fileType) {
            case CSV:
                return csvProductReader().read(Constants.CSV_PATH);
            case TEXT:
                return textProductReader().read(Constants.TXT_PATH);
            case XML:
                return xmlProductReader().read(Constants.XML_PATH);
            default:
                return null;
        }
    }
    static ProductReader textProductReader() {
        return productsFile -> {
            List<ProductWebsiteDetails> list = new ArrayList<>();
            String line = "";
            String cvsSplitBy = ",";
            try (BufferedReader br = new BufferedReader(new InputStreamReader(
                    Objects.requireNonNull(ProductReader.class.getClassLoader()
                            .getResourceAsStream(productsFile))));) {
                while ((line = br.readLine()) != null) {
                    String[] productAttributes = line.split(cvsSplitBy);
                    ProductWebsiteDetails productWebsiteDetails =
                     Utils.getProductWebsiteDetails(productAttributes);
                    list.add(productWebsiteDetails);
                }
                return list;
            }
        };
    }

    static ProductReader xmlProductReader() {
        throw new NotImplementedDataSource("This data source has" +
                " not yet been implemented");
    }

    static ProductReader csvProductReader() {
        throw new NotImplementedDataSource("This data source has" +
                " not yet been implemented");
    }

}
