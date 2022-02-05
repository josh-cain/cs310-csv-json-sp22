package edu.jsu.mcis;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    private String csvString;
    private String jsonString;

    private JSONParser parser;
    private JSONObject jsonData;

    @Before
    public void setUp() {

        ClassLoader loader = ClassLoader.getSystemClassLoader();

        // Get CSV Data

        StringBuilder csv = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.csv")));

            String line;
            while((line = reader.readLine()) != null) {
                csv.append(line).append('\n');
            }

        }

        catch(IOException e) { e.printStackTrace(); }

        csvString = csv.toString().trim();

        // Get JSON Data

        parser = new JSONParser();

        StringBuilder json = new StringBuilder();

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(loader.getResourceAsStream("grades.json")));

            String line;
            while((line = reader.readLine()) != null) {
                json.append(line).append('\n');
            }

        }

        catch(IOException e) { e.printStackTrace(); }

        jsonString = json.toString().trim();

        try {

            jsonData = (JSONObject)parser.parse(jsonString);

        }

        catch(ParseException e) { e.printStackTrace(); }
    }

    @Test
    public void testConvertCSVtoJSON() {
        assertEquals(jsonString, Converter.csvToJson(csvString));
    }

    @Test
    public void testConvertJSONtoCSV() {
        assertEquals(csvString, Converter.jsonToCsv(jsonString));
    }

    @Test
    public void testConvertJSONtoCSVtoJSON(){
        String csv = Converter.jsonToCsv(jsonString);
        String json = Converter.csvToJson(csv);
        assertEquals(jsonString, json);
    }

    @Test
    public void testConvertCSVtoJSONtoCSV(){
        String json = Converter.csvToJson(csvString);
        String csv = Converter.jsonToCsv(json);
        assertEquals(csvString, csv);
    }

}
