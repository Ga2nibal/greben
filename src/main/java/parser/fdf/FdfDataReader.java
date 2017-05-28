package parser.fdf;

import java.io.Reader;
import java.util.List;
import java.util.Map;

public interface FdfDataReader<T> {

    List<Map<String, String>> parseToMap(String filepath);
    List<T> parse(Reader reader, Class<T> clazz);

    char getDelimiter();
    void setDelimiter(char delimiter);
}
