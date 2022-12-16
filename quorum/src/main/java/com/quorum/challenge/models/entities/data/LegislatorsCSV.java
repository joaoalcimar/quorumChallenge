package com.quorum.challenge.models.entities.data;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@Getter
@NoArgsConstructor
public class LegislatorsCSV extends DataCSV {

    @Id
    @CsvBindByName(column = "id")
    private Integer id;

    @CsvBindByName(column = "name")
    private String name;

}
