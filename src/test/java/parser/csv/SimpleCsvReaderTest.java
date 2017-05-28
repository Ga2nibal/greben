package parser.csv;


public class SimpleCsvReaderTest extends CsvReaderTest {

    protected CsvReader createCsvReader() {
        return new SimpleCsvReader();
    }
}
