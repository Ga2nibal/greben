package parser.fdf;

import model.MetersData;
import model.MotionPeriod;
import model.OriginalMotionType;
import org.junit.Assert;
import org.junit.Test;
import parser.csv.CsvReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public abstract class FdfDataReaderTest {

    protected abstract FdfDataReader createFdfDataReader();

    @Test
    public void parseMotionPeriodOneLineFromFileToInstances() throws IOException {

        FdfDataReader fdfReader = createFdfDataReader();

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("fdf/BackhandsData.fdf")) {

            List<MetersData> result = fdfReader.parse(new InputStreamReader(in), MetersData.class);
            Assert.assertNotNull(result);
            Assert.assertEquals(2, result.size());
            Assert.assertNotNull(result.get(0));
            Assert.assertNotNull(result.get(1));

            MetersData line = result.get(0);
            Assert.assertEquals(239, line.getTime());
            Assert.assertEquals(-9.081915, line.getAccelerationX(), 0.0000001);
            Assert.assertEquals(2.0118165, line.getAccelerationY(), 0.0000001);
            //TODO:
        }
    }
}
