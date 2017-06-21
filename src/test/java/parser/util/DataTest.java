package parser.util;

import model.MetersData;
import model.Motion;
import model.OriginalMotionType;
import neuralnetwork.base.data.DataSet;
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

    @Test
    public void TestCollectDataSetsWithWindow1() throws IOException{

        int sliceWindow = 1;

        File file = new File("src/test/resources/collectTrainingData");

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(file.getAbsolutePath());

        Map<OriginalMotionType, DataSet> dtaSetsMap = Data.convertToDataSetsMap(trainingSet, sliceWindow, sliceWindow);

        Assert.assertNotNull(dtaSetsMap);
        Assert.assertEquals(1, dtaSetsMap.size());
        Assert.assertNotNull(dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT));
        Assert.assertEquals(3, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).size());
        Assert.assertEquals(sliceWindow*MetersData.UsedMetersDataCount, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).get(0).getInput().length);
    }

    @Test
    public void TestCollectDataSetsWithWindow2() throws IOException{

        int sliceWindow = 18;

        File file = new File("src/test/resources/collectTrainingData");

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(file.getAbsolutePath());

        Map<OriginalMotionType, DataSet> dtaSetsMap = Data.convertToDataSetsMap(trainingSet, sliceWindow, sliceWindow);

        Assert.assertNotNull(dtaSetsMap);
        Assert.assertEquals(1, dtaSetsMap.size());
        Assert.assertNotNull(dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT));
        Assert.assertEquals(3, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).size());
        Assert.assertEquals(sliceWindow*MetersData.UsedMetersDataCount, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).get(0).getInput().length);
    }

    @Test
    public void TestCollectDataSetsWithWindow3() throws IOException{

        int sliceWindow = 20;

        File file = new File("src/test/resources/collectTrainingData");

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(file.getAbsolutePath());

        Map<OriginalMotionType, DataSet> dtaSetsMap = Data.convertToDataSetsMap(trainingSet, sliceWindow, sliceWindow);

        Assert.assertNotNull(dtaSetsMap);
        Assert.assertEquals(1, dtaSetsMap.size());
        Assert.assertNotNull(dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT));
        Assert.assertEquals(1, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).size());
        Assert.assertEquals(sliceWindow*MetersData.UsedMetersDataCount, dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT).get(0).getInput().length);
    }

    @Test
    public void TestCollectDataSetsWithWindow30() throws IOException{

        int sliceWindow = 30;

        File file = new File("src/test/resources/collectTrainingData");

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(file.getAbsolutePath());

        Map<OriginalMotionType, DataSet> dtaSetsMap = Data.convertToDataSetsMap(trainingSet, sliceWindow, sliceWindow);

        Assert.assertNotNull(dtaSetsMap);
        Assert.assertEquals(0, dtaSetsMap.size());
    }
}
