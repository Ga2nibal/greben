package parser.util;

import model.Motion;
import model.OriginalMotionType;
import org.junit.Assert;
import org.junit.Test;
import util.Data;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataTest {

    @Test
    public void Test0() throws IOException{

        File file = new File("src/test/resources/collectTrainingData");

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(file.getAbsolutePath());

        Assert.assertNotNull(trainingSet);
    }
}
