package parser.csv;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class CsvReaderTest {

    protected abstract CsvReader createCsvReader();

    @Test
    public void parseList() {

        CsvReader reader = createCsvReader();

        List<String> data = new ArrayList<String>();
        data.add("SampleId;MotionId;OriginalMotionType;StartTime;EndTime;Duration;;");
        data.add("407;1;BACKHAND_FLAT;18919;20571;1652;");

        List<Map<String, String>> result = reader.parseCsvToMap(data);
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
}
