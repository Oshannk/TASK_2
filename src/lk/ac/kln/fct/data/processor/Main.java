package lk.ac.kln.fct.data.processor;

import lk.ac.kln.fct.data.pojo.Record;

import java.util.ArrayList;

public class Main {
    private static final String FILE_PATH = "src/covid19data.json";
    private static final String DATE = "18/05/2020";

    public static void main(String[] args) {
        DataProcessor processor = new DataProcessor();
        sortAndPrint(processor.getDataByDate(FILE_PATH, DATE));
    }

    public static void sortAndPrint(ArrayList<Record> records) {
        records.sort((record1, record2) -> Integer.compare(record2.getCases(), record1.getCases()));
        for (Record i: records) {
            if (i.getCases() >= 1000) {
                System.out.println(i.getCountriesAndTerritories() + " : " + i.getCases() + " : " + i.getDeaths());
            }
        }
    }
}
