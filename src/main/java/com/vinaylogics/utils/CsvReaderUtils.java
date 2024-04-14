package com.vinaylogics.utils;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.vinaylogics.dto.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Slf4j
public class CsvReaderUtils {

    public static List<User> readDataFromCsv() {
        try(BufferedReader reader= new BufferedReader(new InputStreamReader(
                new ClassPathResource("users.csv").getInputStream()
        ))){
            CsvToBean<User> csvToBean = new CsvToBeanBuilder<User>(reader)
                    .withType(User.class)
                    .build();
            return csvToBean.parse();
        }catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
