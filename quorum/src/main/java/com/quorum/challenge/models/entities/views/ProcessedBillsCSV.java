package com.quorum.challenge.models.entities.views;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class ProcessedBillsCSV {

    @Id
    @CsvBindByName(column = "id")
    Integer id;

    @CsvBindByName(column = "legislator_id")
    String title;

    @CsvBindByName(column = "supporter_count")
    Integer supporterCount = 0;

    @CsvBindByName(column = "opposer_count")
    Integer opposerCount = 0;

    @CsvBindByName(column = "primary_sponsor")
    String primarySponsor;

    Integer sponsorId;

    Integer voteId;


}
