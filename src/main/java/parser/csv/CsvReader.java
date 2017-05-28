package parser.csv;

import java.util.List;
import java.util.Map;

public interface CsvReader {

    List<Map<String, String>> parseCsvToMap(String filePath);
    List<Map<String, String>> parseCsvToMap(Iterable<String> lines);
}
