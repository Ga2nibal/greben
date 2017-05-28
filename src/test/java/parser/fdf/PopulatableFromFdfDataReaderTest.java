package parser.fdf;

public class PopulatableFromFdfDataReaderTest extends FdfDataReaderTest {
    @Override
    protected FdfDataReader createFdfDataReader() {
        return new PopulatableFromFdfDataReader();
    }
}
