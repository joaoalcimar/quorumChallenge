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

    public void incrementSupportedVote(){this.numSupportedBills++;}

    public void incrementOpposedVote(){this.numOpposedBills++;}

    public String[] toStringArray(){
        return new String[]{id.toString(), name, numSupportedBills.toString(), numOpposedBills.toString()};
    }
}
