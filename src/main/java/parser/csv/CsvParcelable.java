package parser.csv;

import org.apache.commons.csv.CSVRecord;

public interface CsvParcelable {
    void populateFromCsv(CSVRecord csvRecord);
}
