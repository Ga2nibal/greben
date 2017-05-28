package parser.csv;

import org.apache.commons.csv.CSVRecord;

public interface PopulatableFromCsv {
    void populateFromCsv(CSVRecord csvRecord);
}
