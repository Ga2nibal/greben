package util;

import model.MetersData;
import model.Motion;
import model.MotionPeriod;
import model.OriginalMotionType;
import neuralnetwork.base.data.DataSet;
import neuralnetwork.base.data.DataSetRow;
import org.apache.commons.io.FilenameUtils;
import parser.csv.CsvReader;
import parser.csv.PopulatableFromCsvReader;
import parser.fdf.FdfDataReader;
import parser.fdf.PopulatableFromFdfDataReader;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.stream.Collectors;

public class Data {

    public static Map<OriginalMotionType, DataSet> convertToDataSetsMap(Map<OriginalMotionType, List<Motion>> trainingData,
                                                                 int sliceWindows){

        Map<OriginalMotionType, DataSet> result = new HashMap<>();
        for (OriginalMotionType k : trainingData.keySet()) {
            DataSet dataSet = convertToDataSet(trainingData.get(k), sliceWindows);
            if(null == dataSet)
                continue;
            result.put(k, dataSet);
        }
        return result;
    }

    public static DataSet convertToDataSet(List<Motion> trainingData, int sliceWindows){





        List<DataSetRow> rows = trainingData.stream().map(m -> convertToDataSetRow(m, sliceWindows))
                .filter(Objects::nonNull).collect(Collectors.toList());
        if(rows.isEmpty())
            return null;
        DataSet result = new DataSet(rows.get(0).getInput().length);
        result.addAll(rows);
        return result;
    }

    public static DataSetRow convertToDataSetRow(Motion motion, int sliceWindow){

        return convertToDataSetRow(motion.getMetersData(), sliceWindow);
    }

    public static DataSetRow convertToDataSetRow(List<MetersData>  metersData, int sliceWindow){

        DataSetRow result;
        if(metersData.isEmpty())
            return null;
        else if(metersData.size() < sliceWindow)
            result = null;
        else if(metersData.size() == sliceWindow)
        {
            List<Double> doubles = new ArrayList<>();
            for (MetersData md: metersData) {
                doubles.addAll(md.toList());
            }
            double[] input = doubles.stream().mapToDouble(Double::doubleValue).toArray();
            result = new DataSetRow(input);
        }
        else {

            int metersDataSize = metersData.size();
            int greaterOn = metersDataSize - sliceWindow;
//            int[] skipMetersData = new int[greaterOn];
            List<Integer> skipMetersData = new ArrayList<>();
            for(int i = 0; i < greaterOn; i++){
//                skipMetersData[i] = ((metersDataSize - 1) / greaterOn) * i;
                skipMetersData.add(i);
            }

            List<Double> doubles = new ArrayList<>();
            for (int i = 0; i < metersDataSize; i++) {
                if(skipMetersData.contains(i))
                    continue;
                doubles.addAll(metersData.get(i).toList());
            }
            double[] input = doubles.stream().mapToDouble(Double::doubleValue).toArray();
            result = new DataSetRow(input);
        }

        return result;
    }

    public static Map<OriginalMotionType, List<Motion>> collectTrainingData(String folderPath)
        throws IOException{

        if (null == folderPath)
            throw new InvalidParameterException("folderPath is not defined");

        File folder = new File(folderPath);

        if(!folder.exists())
            throw new InvalidParameterException("folderPath is not exists");

        if(!folder.isDirectory())
            throw new InvalidParameterException("folder path is not a directory. folder path = " + folderPath);

        FdfDataReader fdfDataReader = new PopulatableFromFdfDataReader();
        CsvReader csvDataReader = new PopulatableFromCsvReader();
        csvDataReader.setDelimiter(';');

        Map<String, TrainingFilePair> trainingFilePairMap = new HashMap<>();

        for (File file: folder.listFiles()) {

            if(!file.exists() || file.isDirectory())
                continue;

            String fileName = getFileNameWithoutExtention(file);

            TrainingFilePair trainingFilePair = trainingFilePairMap.computeIfAbsent(fileName, k -> new TrainingFilePair());

            if(isFdfFile(file))
                trainingFilePair.fdfDataFile = file;
            else if(isCsvFile(file))
                trainingFilePair.csvTimeFile = file;
        }

        List<TrainingFilePair> trainingPairs = trainingFilePairMap.values()
                .stream().filter(TrainingFilePair::isPairPopulated)
                .collect(Collectors.toList());

        Map<OriginalMotionType, List<Motion>> result = new HashMap<>();

        for (TrainingFilePair currentTrainingFilePair : trainingPairs) {

            try (InputStream inFdf = new FileInputStream(currentTrainingFilePair.fdfDataFile)) {
                try (InputStream inCsv = new FileInputStream(currentTrainingFilePair.csvTimeFile)) {
                List<MetersData> datas = fdfDataReader.parse(new InputStreamReader(inFdf), MetersData.class);
                List<MotionPeriod> periods = csvDataReader.parseCsv(new InputStreamReader(inCsv), MotionPeriod.class);

                    Transformation.collectMotions(datas, periods, result);
                }
            }
        }

        return result;
    }

    private static boolean isFdfFile(File file){

        return "fdf".equals(FilenameUtils.getExtension(file.getName()).toLowerCase());
    }

    private static boolean isCsvFile(File file){

        return "csv".equals(FilenameUtils.getExtension(file.getName()).toLowerCase());
    }

    private static String getFileNameWithoutExtention(File file){
        String fileName = file.getName();
        return fileName.replaceFirst("[.][^.]+$", "");
    }

    private static class TrainingFilePair{

        private File fdfDataFile;
        private File csvTimeFile;

        public TrainingFilePair(){

        }

        public TrainingFilePair(File fdfDataFile, File csvTimeFile){

            if(null == fdfDataFile)
                throw new InvalidParameterException("fdfDataFile is not defined");
            if(fdfDataFile.exists())
                throw new InvalidParameterException("fdfDataFile is not exists");

            if(null == csvTimeFile)
                throw new InvalidParameterException("csvTimeFile is not defined");
            if(csvTimeFile.exists())
                throw new InvalidParameterException("csvTimeFile is not exists");

            this.fdfDataFile = fdfDataFile;
            this.csvTimeFile = csvTimeFile;
        }

        public File getFdfDataFile() {
            return fdfDataFile;
        }

        public void setFdfDataFile(File fdfDataFile) {
            if(null == fdfDataFile)
                throw new InvalidParameterException("fdfDataFile is not defined");
            if(fdfDataFile.exists())
                throw new InvalidParameterException("fdfDataFile is not exists");
            this.fdfDataFile = fdfDataFile;
        }

        public File getCsvTimeFile() {
            return csvTimeFile;
        }

        public void setCsvTimeFile(File csvTimeFile) {
            if(null == csvTimeFile)
                throw new InvalidParameterException("csvTimeFile is not defined");
            if(csvTimeFile.exists())
                throw new InvalidParameterException("csvTimeFile is not exists");
            this.csvTimeFile = csvTimeFile;
        }

        public boolean isPairPopulated(){
            return fdfDataFile != null && csvTimeFile != null;
        }
    }
}
