package util;

import model.MetersData;
import model.Motion;
import model.MotionPeriod;
import model.OriginalMotionType;
import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.data.DataSet;
import neuralnetwork.base.data.DataSetRow;
import neuralnetwork.factories.NeuralNetworkFactory;
import neuralnetwork.implementation.leaning.KohonenLearning;
import parser.fdf.FdfDataReader;
import parser.fdf.PopulatableFromFdfDataReader;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MotionDetector {

    private final NeuralNetwork<KohonenLearning> serveNetwrk;
    private final NeuralNetwork<KohonenLearning> backVsDoreNetwrk;
    private final NeuralNetwork<KohonenLearning> volleyNetwrk;
    private final NeuralNetwork<KohonenLearning> forehandNetwrk;
    private final NeuralNetwork<KohonenLearning> backhandNetwrk;

    private final int volleyWindow;
    private final int otherWindow;

    public MotionDetector(String trainigDataDir,
                          int volleyWindow, int otherWindow) throws IOException{
        if(volleyWindow <= 0)
            throw new InvalidParameterException("volleyWindow must be greater than zero. volleyWindow = "
                    + String.valueOf(volleyWindow));
        if(otherWindow <= 0)
            throw new InvalidParameterException("otherWindow must be greater than zero. otherWindow = "
                    + String.valueOf(otherWindow));

        this.volleyWindow = volleyWindow;
        this.otherWindow = otherWindow;

        File fileYtainigDir = new File(trainigDataDir);

        Map<OriginalMotionType, List<Motion>> trainingSet =
                Data.collectTrainingData(fileYtainigDir.getAbsolutePath());

        Map<OriginalMotionType, DataSet> dtaSetsMap = Data.convertToDataSetsMap(trainingSet,
                volleyWindow, otherWindow);

        serveNetwrk = buildServeNeuralNetwork(dtaSetsMap, otherWindow);
        backVsDoreNetwrk = buildForehandsBackhandsNeuralNetwork(dtaSetsMap, otherWindow);
        volleyNetwrk = buildVolleyNeuralNetwork(dtaSetsMap, volleyWindow);
        forehandNetwrk = buildForehandSubTypesNeuralNetwork(dtaSetsMap, otherWindow);
        backhandNetwrk = buildBackhandSubTypesNeuralNetwork(dtaSetsMap, otherWindow);
    }

    public List<MotionPeriod> predictMotionPeriods(String inputFilePath) throws IOException{
        List<MetersData> datas;
        FdfDataReader fdfDataReader = new PopulatableFromFdfDataReader();
        File file = new File(inputFilePath);
        try (InputStream inFdf = new FileInputStream(file)) {
            datas = fdfDataReader.parse(new InputStreamReader(inFdf), MetersData.class);
        }
        return predictMotionPeriods(datas);
    }

    public List<MotionPeriod> predictMotionPeriods(List<MetersData> metersData){

        List<MotionPeriod> result = new ArrayList<>();
        for(int i = volleyWindow; i <= metersData.size(); i++)
        {
            DataSetRow dsr = Data.convertToDataSetRow(metersData.subList(i-volleyWindow, i), volleyWindow);
            OriginalMotionType omt = predictVollyMotion(dsr);
            if(omt != OriginalMotionType.UNDEFINED) {
                MotionPeriod mp = new MotionPeriod(omt, metersData.get(i-volleyWindow).getTime(),
                        metersData.get(i-1).getTime());
                result.add(mp);
            }
        }

        for(int i = otherWindow; i <= metersData.size(); i++)
        {
            DataSetRow dsr = Data.convertToDataSetRow(metersData.subList(i-otherWindow, i), otherWindow);
            OriginalMotionType omt = predictOtherMotion(dsr);
            if(omt != OriginalMotionType.UNDEFINED) {
                MotionPeriod mp = new MotionPeriod(omt, metersData.get(i-volleyWindow).getTime(),
                        metersData.get(i-1).getTime());
                result.add(mp);
            }
        }

        return result;
    }

    public OriginalMotionType predictVollyMotion(DataSetRow dsr){

        if(dsr.getInput().length != volleyWindow)
            return OriginalMotionType.UNDEFINED;

        volleyNetwrk.setInput(dsr.getInput());
        volleyNetwrk.calculate();
        double[] output = volleyNetwrk.getOutput();
        if(output[0] > output[1] && output[0] > output[2])
            return OriginalMotionType.VOLLEY_FOREHAND;
        else  if(output[1] > output[0] && output[1] > output[2])
            return OriginalMotionType.VOLLEY_BACKHAND;
        else
            return OriginalMotionType.UNDEFINED;
    }

    public OriginalMotionType predictOtherMotion(DataSetRow dsr){
        if(dsr.getInput().length != volleyWindow)
            return OriginalMotionType.UNDEFINED;

        serveNetwrk.setInput(dsr.getInput());
        serveNetwrk.calculate();
        double[] output = serveNetwrk.getOutput();
        if(output[0] > output[1])
            return OriginalMotionType.SMASH;

        backVsDoreNetwrk.setInput(dsr.getInput());
        backVsDoreNetwrk.calculate();
        output = backVsDoreNetwrk.getOutput();
        if(output[0] > output[1] && output[0] > output[2]){
            //backhand
            backhandNetwrk.setInput(dsr.getInput());
            backhandNetwrk.calculate();
            output = backhandNetwrk.getOutput();
            if(output[0] > output[1] && output[0] > output[2]
                    && output[0] > output[3])
                return OriginalMotionType.BACKHAND_FLAT;
            else if(output[1] > output[0] && output[1] > output[2]
                    && output[1] > output[3])
                return OriginalMotionType.BACKHAND_LIFTED;
            else if(output[2] > output[1] && output[2] > output[0]
                    && output[2] > output[3])
                return OriginalMotionType.BACKHAND_SLICED;
            else
                return OriginalMotionType.UNDEFINED;
        }
        else if(output[1] > output[0] && output[1] > output[2])
        {
            //forehand
            forehandNetwrk.setInput(dsr.getInput());
            forehandNetwrk.calculate();
            output = forehandNetwrk.getOutput();
            if(output[0] > output[1] && output[0] > output[2]
                    && output[0] > output[3])
                return OriginalMotionType.FOREHAND_FLAT;
            else if(output[1] > output[0] && output[1] > output[2]
                    && output[1] > output[3])
                return OriginalMotionType.FOREHAND_LIFTED;
            else if(output[2] > output[1] && output[2] > output[0]
                    && output[2] > output[3])
                return OriginalMotionType.FOREHAND_SLICED;
            else
                return OriginalMotionType.UNDEFINED;
        }
        else
            return OriginalMotionType.UNDEFINED;
    }

    public int getVolleyWindow() {
        return volleyWindow;
    }

    public int getOtherWindow() {
        return otherWindow;
    }

    public NeuralNetwork<KohonenLearning> getServeNetwrk() {
        return serveNetwrk;
    }

    public NeuralNetwork<KohonenLearning> getBackVsDoreNetwrk() {
        return backVsDoreNetwrk;
    }

    public NeuralNetwork<KohonenLearning> getVolleyNetwrk() {
        return volleyNetwrk;
    }

    public NeuralNetwork<KohonenLearning> getForehandNetwrk() {
        return forehandNetwrk;
    }

    public NeuralNetwork<KohonenLearning> getBackhandNetwrk() {
        return backhandNetwrk;
    }

    public static NeuralNetwork<KohonenLearning> buildServeNeuralNetwork(Map<OriginalMotionType, DataSet> dtaSetsMap, int sliceWindow) throws IOException{

        DataSet testDataSet = dtaSetsMap.get(OriginalMotionType.SMASH);

        NeuralNetwork<KohonenLearning> network =
                NeuralNetworkFactory.createKohonen(testDataSet.getInputSize(), 2);
        network.addLayer(new Layer(10));

        double[] serveOut = new double[]{1,0};
        double[] undefOut = new double[]{0,1};

        testDataSet.forEach(dsr -> {
            if(OriginalMotionType.SMASH.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(serveOut );
            else
                dsr.setDesiredOutput(undefOut);
        });

        network.learn(testDataSet);
        return network;
    }

    public static NeuralNetwork<KohonenLearning> buildForehandsBackhandsNeuralNetwork(Map<OriginalMotionType, DataSet> dtaSetsMap, int sliceWindow) throws IOException{

        DataSet forehend_flat_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT);
        DataSet forehend_lifted_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_LIFTED);
        DataSet forehend_sliced_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_SLICED);
        DataSet foreDataSetTraining = new DataSet(forehend_flat_ds.getInputSize());
        foreDataSetTraining.addAll(forehend_flat_ds.getRows());
        foreDataSetTraining.addAll(forehend_lifted_ds.getRows());
        foreDataSetTraining.addAll(forehend_sliced_ds.getRows());
        foreDataSetTraining.getRows().forEach(dr -> dr.setDesiredOutput(new double[]{GroupMotionType.FOREHAND.ordinal()}));

        DataSet backhend_flat_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_FLAT);
        DataSet backhend_lifted_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_LIFTED);
        DataSet backhend_sliced_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_SLICED);
        DataSet backDataSetTraining = new DataSet(backhend_flat_ds.getInputSize());
        backDataSetTraining.addAll(backhend_flat_ds.getRows());
        backDataSetTraining.addAll(backhend_lifted_ds.getRows());
        backDataSetTraining.addAll(backhend_sliced_ds.getRows());
        backDataSetTraining.getRows().forEach(dr -> dr.setDesiredOutput(new double[]{GroupMotionType.BACKHAND.ordinal()}));

        DataSet dataSetTraining = new DataSet(forehend_flat_ds.getInputSize());
        dataSetTraining.addAll(foreDataSetTraining.getRows());
        dataSetTraining.addAll(backDataSetTraining.getRows());

        double[] forOut = new double[]{1,0,0};
        double[] backOut = new double[]{0,1,0};
        double[] undefOut = new double[]{0,0,1};

        dataSetTraining.forEach(dsr -> {
            if(OriginalMotionType.BACKHAND_LIFTED.name().equals(dsr.getLabel())
                    || OriginalMotionType.BACKHAND_SLICED.name().equals(dsr.getLabel())
                    || OriginalMotionType.BACKHAND_FLAT.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(backOut );
            else if(OriginalMotionType.FOREHAND_SLICED.name().equals(dsr.getLabel())
                    || OriginalMotionType.FOREHAND_LIFTED.name().equals(dsr.getLabel())
                    || OriginalMotionType.FOREHAND_FLAT.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(forOut );
            else
                dsr.setDesiredOutput(undefOut);
        });

        NeuralNetwork<KohonenLearning> network =
                NeuralNetworkFactory.createKohonen(dataSetTraining.getInputSize(), 3);
        network.addLayer(new Layer(8));

        network.learn(dataSetTraining);
        return network;
    }

    public static NeuralNetwork<KohonenLearning> buildVolleyNeuralNetwork(Map<OriginalMotionType, DataSet> dtaSetsMap, int sliceWindow) throws IOException{

        DataSet volley_forehend_ds = dtaSetsMap.get(OriginalMotionType.VOLLEY_FOREHAND);
        DataSet volley_backhend_ds = dtaSetsMap.get(OriginalMotionType.VOLLEY_BACKHAND);

        DataSet dataSetTraining = new DataSet(volley_forehend_ds.getInputSize());
        dataSetTraining.addAll(volley_forehend_ds.getRows());
        dataSetTraining.addAll(volley_backhend_ds.getRows());

        double[] vforOut = new double[]{1,0,0};
        double[] vbackOut = new double[]{0,1,0};
        double[] undefOut = new double[]{0,0,1};

        dataSetTraining.forEach(dsr -> {
            if(OriginalMotionType.VOLLEY_FOREHAND.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(vforOut );
            else if(OriginalMotionType.VOLLEY_BACKHAND.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(vbackOut );
            else
                dsr.setDesiredOutput(undefOut);
        });

        NeuralNetwork<KohonenLearning> network =
                NeuralNetworkFactory.createKohonen(dataSetTraining.getInputSize(), 3);
        network.addLayer(new Layer(9));

        network.learn(dataSetTraining);
        return network;
    }

    public static NeuralNetwork<KohonenLearning> buildForehandSubTypesNeuralNetwork(Map<OriginalMotionType, DataSet> dtaSetsMap, int sliceWindow) throws IOException{

        DataSet forehend_flat_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_FLAT);
        DataSet forehend_lifted_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_LIFTED);
        DataSet forehend_sliced_ds = dtaSetsMap.get(OriginalMotionType.FOREHAND_SLICED);

        DataSet dataSetTraining = new DataSet(forehend_flat_ds.getInputSize());
        dataSetTraining.addAll(forehend_flat_ds.getRows());
        dataSetTraining.addAll(forehend_lifted_ds.getRows());
        dataSetTraining.addAll(forehend_sliced_ds.getRows());

        double[] fFlatOut = new double[]{1,0,0,0};
        double[] fLiftedOut = new double[]{0,1,0,0};
        double[] fSlicedOut = new double[]{0,0,1,0};
        double[] undefOut = new double[]{0,0,0,1};

        dataSetTraining.forEach(dsr -> {
            if(OriginalMotionType.FOREHAND_FLAT.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(fFlatOut );
            else if(OriginalMotionType.FOREHAND_LIFTED.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(fLiftedOut );
            else if(OriginalMotionType.FOREHAND_SLICED.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(fSlicedOut );
            else
                dsr.setDesiredOutput(undefOut);
        });

        NeuralNetwork<KohonenLearning> network =
                NeuralNetworkFactory.createKohonen(dataSetTraining.getInputSize(), 4);
        network.addLayer(new Layer(12));

        network.learn(dataSetTraining);
        return network;
    }

    public static NeuralNetwork<KohonenLearning> buildBackhandSubTypesNeuralNetwork(Map<OriginalMotionType, DataSet> dtaSetsMap, int sliceWindow) throws IOException{

        DataSet backhend_flat_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_FLAT);
        DataSet backhend_lifted_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_LIFTED);
        DataSet backhend_sliced_ds = dtaSetsMap.get(OriginalMotionType.BACKHAND_SLICED);

        DataSet dataSetTraining = new DataSet(backhend_flat_ds.getInputSize());
        dataSetTraining.addAll(backhend_flat_ds.getRows());
        dataSetTraining.addAll(backhend_lifted_ds.getRows());
        dataSetTraining.addAll(backhend_sliced_ds.getRows());

        double[] bFlatOut = new double[]{1,0,0,0};
        double[] bLiftedOut = new double[]{0,1,0,0};
        double[] bSlicedOut = new double[]{0,0,1,0};
        double[] undefOut = new double[]{0,0,0,1};

        dataSetTraining.forEach(dsr -> {
            if(OriginalMotionType.BACKHAND_FLAT.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(bFlatOut );
            else if(OriginalMotionType.BACKHAND_LIFTED.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(bLiftedOut );
            else if(OriginalMotionType.BACKHAND_SLICED.name().equals(dsr.getLabel()))
                dsr.setDesiredOutput(bSlicedOut );
            else
                dsr.setDesiredOutput(undefOut);
        });

        NeuralNetwork<KohonenLearning> network =
                NeuralNetworkFactory.createKohonen(dataSetTraining.getInputSize(), 4);
        network.addLayer(new Layer(12));

        network.learn(dataSetTraining);
        return network;
    }
}
