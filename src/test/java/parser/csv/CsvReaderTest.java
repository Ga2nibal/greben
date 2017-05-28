package parser.csv;

import org.junit.Test;

public abstract class CsvReaderTest {

    protected abstract CsvReader createCsvReader(long max, long min);

    @Test
    public void getMaxMinEqualsToFactoryParams() {

        //TODO:
    }
}
