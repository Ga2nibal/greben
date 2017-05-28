package parser.fdf;

import parser.PopulatableFromCsv;
import parser.csv.CsvReader;
import parser.csv.PopulatableFromCsvReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public class PopulatableFromFdfDataReader<T extends PopulatableFromCsv> implements FdfDataReader<T>{

    private char delimiter = '\t';

    @Override
    public List<Map<String, String>> parseToMap(String filepath) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<T> parse(Reader reader, Class<T> clazz) {

        try {
            try (BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    if(line.contains("@Data"))
                        break;
                }

                CsvReader<T> csvReader = new PopulatableFromCsvReader<>();
                csvReader.setDelimiter(delimiter);
                List<T> result = csvReader.parseCsv(br, clazz);
                return result;
            }
        }
        catch (Exception ex){
            //TODO: think about handling
            throw new RuntimeException("Can not parse file", ex);
        }
    }

    @Override
    public char getDelimiter() {
        return this.delimiter;
    }

    @Override
    public void setDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }
}
