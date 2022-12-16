package com.quorum.challenge.models.entities.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BillsCSV extends DataCSV {

    @Id
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "title")
    private String title;

    @CsvBindByName(column = "Primary Sponsor")
    private Integer primarySponsor;

    public BillsCSV buildModelFromCSV(String[] metadata) {
        Integer id = Integer.parseInt(metadata[0]);
        String title = metadata[1];
        Integer primarySponsor = Integer.parseInt(metadata[2]);
        return new BillsCSV(id, title, primarySponsor);
    }
}
