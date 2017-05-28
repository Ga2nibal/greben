package parser.csv;

import model.MotionPeriod;
import model.OriginalMotionType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

public abstract class CsvReaderTest {

    protected abstract CsvReader createCsvReader();

    @Test
    public void parseMotionPeriodOneLineFromStringToMap() {

        CsvReader csvReader = createCsvReader();
        csvReader.setDelimiter(';');

        String csvString = "SampleId;MotionId;OriginalMotionType;StartTime;EndTime;Duration;; \\n" +
                "407;1;BACKHAND_FLAT;18919;20571;1652;";
        StringReader stringReader = new StringReader(csvString);

        List<Map<String, String>> result = csvReader.parseCsvToMap(stringReader);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertNotNull(result.get(0));
        Assert.assertEquals(6, result.get(0).size());

        Map<String, String> line = result.get(0);
        Assert.assertTrue(line.containsKey("SampleId"));
        Assert.assertEquals("407" ,line.get("SampleId"));

        Assert.assertTrue(line.containsKey("MotionId"));
        Assert.assertEquals("1" ,line.get("MotionId"));

        Assert.assertTrue(line.containsKey("OriginalMotionType"));
        Assert.assertEquals("BACKHAND_FLAT" ,line.get("OriginalMotionType"));

        Assert.assertTrue(line.containsKey("StartTime"));
        Assert.assertEquals("18919" ,line.get("StartTime"));

        Assert.assertTrue(line.containsKey("EndTime"));
        Assert.assertEquals("20571" ,line.get("EndTime"));

        Assert.assertTrue(line.containsKey("Duration"));
        Assert.assertEquals("1652" ,line.get("Duration"));
    }

    @Test
    public void parseMotionPeriodOneLineFromStringToInstances() {

        CsvReader csvReader = createCsvReader();
        csvReader.setDelimiter(';');

        String csvString = "SampleId;MotionId;OriginalMotionType;StartTime;EndTime;Duration; \\r\\n" +
                "407;1;BACKHAND_FLAT;18919;20571;1652; \\r\\n";
        StringReader stringReader = new StringReader(csvString);

        List<MotionPeriod> result = csvReader.parseCsv(stringReader, MotionPeriod.class);
        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertNotNull(result.get(0));

        MotionPeriod line = result.get(0);
        Assert.assertEquals(407 , line.getSampleId());
        Assert.assertEquals(1 , line.getMotionId());
        Assert.assertSame(OriginalMotionType.BACKHAND_FLAT, line.getOriginalMotionType());
        Assert.assertEquals(18919 , line.getStartTime());
        Assert.assertEquals(20571 , line.getEndTime());
        Assert.assertEquals(1652 , line.getDuration());
    }

    @Test
    public void parseMotionPeriodOneLineFromFileToInstances() throws IOException {

        CsvReader csvReader = createCsvReader();
        csvReader.setDelimiter(';');

        try (InputStream in = this.getClass().getClassLoader().getResourceAsStream("csv/BackhandsMotions.csv")) {

            List<MotionPeriod> result = csvReader.parseCsv(new InputStreamReader(in), MotionPeriod.class);
            Assert.assertNotNull(result);
            Assert.assertEquals(1, result.size());
            Assert.assertNotNull(result.get(0));

            MotionPeriod line = result.get(0);
            Assert.assertEquals(407, line.getSampleId());
            Assert.assertEquals(1, line.getMotionId());
            Assert.assertSame(OriginalMotionType.BACKHAND_FLAT, line.getOriginalMotionType());
            Assert.assertEquals(18919, line.getStartTime());
            Assert.assertEquals(20571, line.getEndTime());
            Assert.assertEquals(1652, line.getDuration());
        }
    }
}

