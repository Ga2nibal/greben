import model.MetersData;
import model.Motion;
import model.OriginalMotionType;
import neuralnetwork.base.Layer;
import neuralnetwork.base.NeuralNetwork;
import neuralnetwork.base.data.DataSet;
import neuralnetwork.base.data.DataSetRow;
import neuralnetwork.factories.NeuralNetworkFactory;
import neuralnetwork.implementation.leaning.KohonenLearning;
import parser.fdf.FdfDataReader;
import parser.fdf.PopulatableFromFdfDataReader;
import util.Data;
import util.GroupMotionType;
import util.MotionDetector;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Program {

    public static void main(String[] args){

        try {
            String trainingDirPath = args[0];
            String inputFilePath = args[1];

            int volleyWindow = 15;
            int otherWindow = 46;

            MotionDetector motionDetector = new MotionDetector(trainingDirPath,
                    volleyWindow, otherWindow);

            List<MetersData> datas;
            FdfDataReader fdfDataReader = new PopulatableFromFdfDataReader();
            File file = new File(inputFilePath);
            try (InputStream inFdf = new FileInputStream(file)) {
                datas = fdfDataReader.parse(new InputStreamReader(inFdf), MetersData.class);
            }

            DataSetRow drw = Data.convertToDataSetRow(datas, sliceWindow);


            netwrk.setInput(drw.getInput());
            netwrk.calculate();
            double[] output = netwrk.getOutput();

            System.out.println("output: " + Arrays.toString(output));
            System.out.println("Press any key to exit...");
            System.in.read();
        }
        catch (Throwable ex) {

            System.out.println("Program finished with error: " + ex.toString());
        }
    }
}
