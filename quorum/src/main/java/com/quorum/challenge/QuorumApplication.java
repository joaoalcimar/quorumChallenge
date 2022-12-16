package com.quorum.challenge;

import com.quorum.challenge.processors.BillsProcessor;
import com.quorum.challenge.processors.LegislatorsCountProcessor;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuorumApplication {

	public static void main(String[] args) throws Exception {
		BillsProcessor billsProcessor = new BillsProcessor();
		LegislatorsCountProcessor legislatorsCountProcessor = new LegislatorsCountProcessor();

		legislatorsCountProcessor.generateLegislatorVoteCountView();
		billsProcessor.generateBillView();
	}

}
