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

            int i = 0;
            MetersData line = result.get(i++);
            //127	1.465752	-2.8740237	9.091495	0.12622166	0.39488763	-0.056743786	-3.375	-4.875	-6.0	-0.7037964	0.07287598	0.16809082	-0.6862793	100272.0	28.4	0.0	0.0	0.0	0.0	0.0	0
            Assert.assertEquals(127, line.getTime());
            Assert.assertEquals(1.465752, line.getAccelerationX(), 0.0000001);
            Assert.assertEquals(-2.8740237, line.getAccelerationY(), 0.0000001);
            Assert.assertEquals(9.091495, line.getAccelerationZ(), 0.0000001);
            Assert.assertEquals(0.12622166, line.getLinearAccelerationX(), 0.0000001);
            //TODO:

            line = result.get(i++);
            //227	1.4753321	-2.931504	9.129815	0.11986782	0.33545786	-0.016772455	-4.0	-5.125	-6.0	-0.7041626	0.07232666	0.1685791	-0.68585205	100272.0	28.4	0.0	0.0	0.0	0.0	0.0	0
            Assert.assertEquals(227, line.getTime());
            Assert.assertEquals(1.4753321, line.getAccelerationX(), 0.0000001);
            Assert.assertEquals(-2.931504, line.getAccelerationY(), 0.0000001);
            Assert.assertEquals(9.129815, line.getAccelerationZ(), 0.0000001);
            Assert.assertEquals(0.11986782, line.getLinearAccelerationX(), 0.0000001);
            //TODO:
        }
    }
}
