package com.quorum.challenge.processors;

import com.opencsv.CSVWriter;
import com.quorum.challenge.constants.VoteSubtitle;
import com.quorum.challenge.models.entities.data.LegislatorsCSV;
import com.quorum.challenge.models.entities.data.VoteResultsCSV;
import com.quorum.challenge.models.entities.views.ProcessedLegislatorsVotesCSV;
import com.quorum.challenge.utils.CSVTransferUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quorum.challenge.constants.CsvPaths.*;

public class LegislatorsCountProcessor {

    private final CSVTransferUtils csvTransferUtils = new CSVTransferUtils();

    private final String[] TITLE = {"id", "name", "num_supported_bills", "num_opposed_bills"};

    public void generateLegislatorVoteCountView() throws IOException {
        List<ProcessedLegislatorsVotesCSV> legislatorsVotesCount = new ArrayList<ProcessedLegislatorsVotesCSV>();

        List<LegislatorsCSV> legislatorsData = csvTransferUtils.getDataFromCSV(RAW_LEGISLATORS_PATH, new LegislatorsCSV());
        List<VoteResultsCSV> voteResultsData = csvTransferUtils.getDataFromCSV(RAW_VOTE_RESULTS_PATH, new VoteResultsCSV());

        for(LegislatorsCSV legislator : legislatorsData){
            ProcessedLegislatorsVotesCSV viewLine = new ProcessedLegislatorsVotesCSV();

            viewLine.setId(legislator.getId());
            viewLine.setName(legislator.getName());

            legislatorsVotesCount.add(viewLine);
        }

        for(VoteResultsCSV votes: voteResultsData){
            int idx = findLegislatorIdx(legislatorsVotesCount, votes.getLegislatorId());
            if (votes.getVoteType().equals(VoteSubtitle.FAVORABLE_VOTE)){
                legislatorsVotesCount.get(idx).incrementSupportedVote();
            }else{
                legislatorsVotesCount.get(idx).incrementOpposedVote();
            }
        }

        CSVWriter writer = new CSVWriter(new FileWriter(String.valueOf(PROCESSED_LEGISLATORS_COUNT_PATH)));
            writer.writeAll(Collections.singleton(TITLE));
        for(ProcessedLegislatorsVotesCSV legislators: legislatorsVotesCount){
            writer.writeNext(legislators.toStringArray());
        }
        writer.close();
    }

    private Integer findLegislatorIdx(List<ProcessedLegislatorsVotesCSV> legislatorsVotesCount, Integer legislatorId){
        for (int i = 0; i < legislatorsVotesCount.size(); i++){
            if (legislatorsVotesCount.get(i).getId().equals(legislatorId)){
                return i;
            }
        }

        return -1;
    }

}
