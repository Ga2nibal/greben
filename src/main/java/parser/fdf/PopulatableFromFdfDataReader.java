package parser.fdf;

import parser.PopulatableFromCsv;

import java.io.Reader;
import java.util.List;
import java.util.Map;

public class PopulatableFromFdfDataReader<T extends PopulatableFromCsv> implements FdfDataReader<T>{

    private char delimiter = '\t';

    @Override
    public List<Map<String, String>> parseToMap(Reader reader) {
        return null;
    }

    @Override
    public List<T> parse(Reader reader, Class<T> clazz) {
        return null;
    }

    @Override
    public char getDelimiter() {
        return this.delimiter;
    }

    @Override
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
}
