package parser.csv;


public class PopulatableFromCsvReaderTest extends CsvReaderTest {

    protected CsvReader createCsvReader() {
        return new PopulatableFromCsvReader();
    }
}
