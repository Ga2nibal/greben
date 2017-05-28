package parser.csv;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import parser.PopulatableFromCsv;

import java.io.Reader;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PopulatableFromCsvReader<T extends PopulatableFromCsv> implements CsvReader<T> {

    private char delimiter = ';';

    public List<Map<String, String>> parseCsvToMap(Reader reader) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public List<T> parseCsv(Reader reader, Class<T> clazz) {

        if(null == reader)
            throw new InvalidParameterException("reader is not defined");
        try {
            List<T> result = new ArrayList<T>();
            Iterable<CSVRecord> records = CSVFormat.newFormat(delimiter).
                    withHeader().parse(reader);
//                    CSVFormat.DEFAULT.withDelimiter(delimiter)
//                    .withSkipHeaderRecord(false).withHeader().parse(reader);
            for (CSVRecord record : records) {
                System.out.println(record);
                T instance = (T) clazz.newInstance();
                instance.populateFromCsv(record);
                result.add(instance);
            }
            return result;
        }
        catch (Exception ex){
            //TODO: think about handling
            throw new RuntimeException("Can not parse file", ex);
        }
    }

    public char getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
}
