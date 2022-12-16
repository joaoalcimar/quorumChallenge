package com.quorum.challenge.constants;

import java.nio.file.Path;

public class CsvPaths {

    public final static Path PROCESSED_BILLS_PATH = Path.of("quorum\\src\\main\\resources\\data\\processed\\bills.csv");
    public final static Path PROCESSED_LEGISLATORS_COUNT_PATH = Path.of("quorum\\src\\main\\resources\\data\\processed\\legislators-support-oppose-count.csv");

    public final static Path RAW_BILLS_PATH = Path.of("quorum\\src\\main\\resources\\data\\raw\\bills.csv");
    public final static Path RAW_LEGISLATORS_PATH = Path.of("quorum\\src\\main\\resources\\data\\raw\\legislators.csv");
    public final static Path RAW_VOTE_RESULTS_PATH = Path.of("quorum\\src\\main\\resources\\data\\raw\\vote_results.csv");
    public final static Path RAW_VOTES_PATH = Path.of("quorum\\src\\main\\resources\\data\\raw\\votes.csv");
}
