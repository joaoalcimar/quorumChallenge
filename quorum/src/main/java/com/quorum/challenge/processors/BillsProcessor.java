package com.quorum.challenge.processors;

import com.opencsv.CSVWriter;
import com.quorum.challenge.constants.VoteSubtitle;
import com.quorum.challenge.models.entities.data.*;
import com.quorum.challenge.models.entities.views.ProcessedBillsCSV;
import com.quorum.challenge.utils.CSVTransferUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.quorum.challenge.constants.CsvPaths.*;

public class BillsProcessor {

    private final String UNKNOWN_SPONSOR = "Unknown";

    private final CSVTransferUtils csvTransferUtils = new CSVTransferUtils();

    private final String[] TITLE = {"id", "title", "supporter_count", "opposer_count", "primary_sponsor"};

    public void generateBillView() throws IOException {
        List<ProcessedBillsCSV> processedBills = new ArrayList<>();

        List<LegislatorsCSV> legislatorsData = csvTransferUtils.getDataFromCSV(RAW_LEGISLATORS_PATH, new LegislatorsCSV());
        List<VoteResultsCSV> voteResultsData = csvTransferUtils.getDataFromCSV(RAW_VOTE_RESULTS_PATH, new VoteResultsCSV());
        List<VotesCSV> votesData = csvTransferUtils.getDataFromCSV(RAW_VOTES_PATH, new VotesCSV());
        List<BillsCSV> billsData = csvTransferUtils.getDataFromCSV(RAW_BILLS_PATH, new BillsCSV());

        for(BillsCSV bills : billsData){
            ProcessedBillsCSV viewLine = new ProcessedBillsCSV();

            viewLine.setId(bills.getId());
            viewLine.setTitle(bills.getTitle());
            viewLine.setSponsorId(bills.getPrimarySponsor());

            processedBills.add(viewLine);
        }

        for(VotesCSV vote: votesData){
            int idx = findBillIdx(processedBills, vote.getBillId());
            processedBills.get(idx).setVoteId(vote.getId());
        }

        for(VoteResultsCSV votesResult: voteResultsData){
            int idx = findVoteId(processedBills, votesResult.getVoteId());

            if (votesResult.getVoteType().equals(VoteSubtitle.FAVORABLE_VOTE)){
                processedBills.get(idx).incrementSupportedVote();
            }else{
                processedBills.get(idx).incrementOpposedVote();
            }
        }

        for(ProcessedBillsCSV bills: processedBills){
            int idx = findLegislatorId(legislatorsData, bills.getSponsorId());
            if(idx == -1){
                bills.setPrimarySponsor(UNKNOWN_SPONSOR);
            }else{
                bills.setPrimarySponsor(legislatorsData.get(idx).getName());
            }
        }

        CSVWriter writer = new CSVWriter(new FileWriter(String.valueOf(PROCESSED_BILLS_PATH)));
        writer.writeAll(Collections.singleton(TITLE));
        for(ProcessedBillsCSV bills: processedBills){
            writer.writeNext(bills.toStringArray());
        }
        writer.close();
    }

    private Integer findBillIdx(List<ProcessedBillsCSV> bills, Integer billId){
        for (int i = 0; i < bills.size(); i++){
            if (bills.get(i).getId().equals(billId)){
                return i;
            }
        }

        return -1;
    }

    private Integer findVoteId(List<ProcessedBillsCSV> bills, Integer voteId){
        for (int i = 0; i < bills.size(); i++){
            if (bills.get(i).getVoteId().equals(voteId)){
                return i;
            }
        }

        return -1;
    }

    private Integer findLegislatorId(List<LegislatorsCSV> legislators, Integer legislatorId){
        for (int i = 0; i < legislators.size(); i++){
            if (legislators.get(i).getId().equals(legislatorId)){
                return i;
            }
        }

        return -1;
    }
}
