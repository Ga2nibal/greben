package util;

import model.MetersData;
import model.Motion;
import model.MotionPeriod;
import model.OriginalMotionType;
import parser.csv.CsvReader;
import parser.csv.PopulatableFromCsvReader;
import parser.fdf.FdfDataReader;
import parser.fdf.PopulatableFromFdfDataReader;

import java.io.*;
import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Data {

    public Map<OriginalMotionType, List<Motion>> collectTrainingData(String folderPath)
        throws IOException{

        if (null == folderPath)
            throw new InvalidParameterException("folderPath is not defined");

        FdfDataReader fdfDataReader = new PopulatableFromFdfDataReader();
        CsvReader csvDataReader = new PopulatableFromCsvReader();
        csvDataReader.setDelimiter(';');

        //TODO: if directory not exist throw exception

        //TODO: Collect training pairs from input folder
        List<TrainingFilePair> trainingPairs = null;

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

    private class TrainingFilePair{

        private File fdfDataFile;
        private File csvTimeFile;

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
    }
}
