package com.quorum.challenge.models.entities.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VotesCSV extends DataCSV {

    @Id
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "bill_id")
    private Integer billId;

    public VotesCSV buildModelFromCSV(String[] metadata) {
        Integer id = Integer.parseInt(metadata[0]);
        Integer billId = Integer.parseInt(metadata[1]);
        return new VotesCSV(id, billId);
    }
}
