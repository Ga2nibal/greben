package parser.csv;

import java.io.Reader;
import java.util.List;
import java.util.Map;

public interface CsvReader<T> {

    List<Map<String, String>> parseCsvToMap(Reader reader);

    List<T> parseCsv(Reader reader, Class<T> clazz);

    char getDelimiter();
    void setDelimiter(char delimiter);
}
