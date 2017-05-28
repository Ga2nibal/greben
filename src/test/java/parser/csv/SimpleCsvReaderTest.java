package parser.csv;


public class SimpleCsvReaderTest extends CsvReaderTest {

    protected CsvReader createCsvReader(long max, long min) {
        return new SimpleCsvReader();
    }
}
