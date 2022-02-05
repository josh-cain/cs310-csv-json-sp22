package edu.jsu.mcis;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class ConverterTest {

    private String csvString;
    private String jsonString;

    @Before
    public void setUp() throws IOException {
        csvString = Files.readString(Path.of(   "src/test/resources/grades.csv")).trim();
        jsonString = Files.readString(Path.of(   "src/test/resources/grades.json")).trim();
    }

    @Test
    public void testConvertCSVtoJSON() {
        assertThat(jsonString, is(equalTo(Converter.csvToJson(csvString))));
    }

    @Test
    public void testConvertJSONtoCSV() {
        assertThat(csvString, is(equalTo(Converter.jsonToCsv(jsonString))));
    }

    @Test
    public void testConvertJSONtoCSVtoJSON(){
        String csv = Converter.jsonToCsv(jsonString);
        String json = Converter.csvToJson(csv);
        assertThat(jsonString, is(equalTo(json)));
    }

    @Test
    public void testConvertCSVtoJSONtoCSV(){
        String json = Converter.csvToJson(csvString);
        String csv = Converter.jsonToCsv(json);
        assertThat(csvString, is(equalTo(csv)));
    }
}
