package com.quorum.challenge.utils;

import com.quorum.challenge.models.entities.data.DataCSV;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CSVTransferUtils {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(CSVTransferUtils.class);

    public List getDataFromCSV(Path csvPath, DataCSV dataCsv){
        List<Object> data = new ArrayList<>();
        try(BufferedReader br = Files.newBufferedReader(csvPath, StandardCharsets.US_ASCII)) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                Object book = dataCsv.buildModelFromCSV(attributes);
                data.add(book);
                line = br.readLine(); }
        } catch (IOException e) {
            LOGGER.info("Error Reading CSV file.");
            LOGGER.error(e.getMessage());
        }

        return data;
    }
}
