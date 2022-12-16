package com.quorum.challenge.models.entities.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VoteResultsCSV extends DataCSV {

    @Id
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "legislator_id")
    private Integer legislatorId;

    @CsvBindByName(column = "vote_id")
    private Integer voteId;

    @CsvBindByName(column = "vote_type")
    private Integer voteType;

    public VoteResultsCSV buildModelFromCSV(String[] metadata) {
        Integer id = Integer.parseInt(metadata[0]);
        Integer legislatorId = Integer.parseInt(metadata[1]);
        Integer voteId = Integer.parseInt(metadata[2]);
        Integer voteType = Integer.parseInt(metadata[3]);
        return new VoteResultsCSV(id, legislatorId, voteId, voteType);
    }
}
