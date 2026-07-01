package com.cognizant.spring_learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class SpringLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(SpringLearnApplication.class);

    public static void main(String[] args) {

        SpringApplication.run(SpringLearnApplication.class, args);

        LOGGER.info("Inside main");

        displayDate();
    }

    private static void displayDate() {

        LOGGER.info("Start displayDate");

        ApplicationContext context =
                new ClassPathXmlApplicationContext("date-format.xml");

        LOGGER.debug("Spring XML Context loaded from date-format.xml");

        SimpleDateFormat format =
                context.getBean("dateFormat", SimpleDateFormat.class);

        LOGGER.debug("dateFormat bean retrieved: {}", format.toPattern());

        try {
            Date date = format.parse("31/12/2018");
            System.out.println("Parsed Date: " + date);
            LOGGER.info("Date parsed successfully: {}", date);

        } catch (ParseException e) {
            LOGGER.error("Failed to parse date: {}", e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n--- Multiple Date Parsing Demo ---");
        String[] dateStrings = {
            "01/01/2020",
            "15/08/1947",
            "26/01/1950",
            "31/12/2024"
        };

        for (String dateStr : dateStrings) {
            try {
                Date parsed = format.parse(dateStr);
                System.out.println("Input: " + dateStr
                        + " → Parsed: " + parsed);
            } catch (ParseException e) {
                LOGGER.error("Parse error for {}: {}",
                        dateStr, e.getMessage());
            }
        }

        System.out.println("\n--- Date Formatting Demo ---");
        Date now = new Date();
        String formatted = format.format(now);
        System.out.println("Today formatted as dd/MM/yyyy: " + formatted);

        System.out.println("\n--- Singleton Bean Proof ---");
        SimpleDateFormat format2 =
                context.getBean("dateFormat", SimpleDateFormat.class);
        System.out.println("format == format2 (same instance)? "
                + (format == format2));

        ((ClassPathXmlApplicationContext) context).close();
        LOGGER.info("End displayDate");
    }
}