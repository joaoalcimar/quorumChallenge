package com.quorum.challenge.models.entities.views;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class ProcessedLegislatorsVotesCSV{

    @Id
    @CsvBindByName(column = "id")
    Integer id;

    @CsvBindByName(column = "name")
    String name;

    @CsvBindByName(column = "num_support_bills")
    Integer numSupportedBills = 0;

    @CsvBindByName(column = "num_opposed_bills")
    Integer numOpposedBills = 0;

}
