package com.evolvoinnovations.csvnormalizer

import com.opencsv.CSVWriter
import com.opencsv.bean.CsvToBeanBuilder
import com.opencsv.bean.StatefulBeanToCsv
import com.opencsv.bean.StatefulBeanToCsvBuilder
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.io.StringReader


@SpringBootApplication
class CsvNormalizerApplication : CommandLineRunner {
    override fun run(vararg args: String?) {
        // read CSV from stdin
        val input = generateSequence(::readLine)
        val list = input.toList()
        val headers = list[0]
        val lines = list.joinToString(separator = "\n")
        val reader = StringReader(lines)
        
        // Parse CSV
        val csv = CsvToBeanBuilder<CsvRow>(reader).
                    withSkipLines(1). // skip headers line
                    withType(CsvRow::class.java). // conversion logic found on the getters
                    build().
                    parse()
        
        // echo converted CSV to stdout
        println(headers)
        val out = BufferedWriter(OutputStreamWriter(System.out))
        val sbc: StatefulBeanToCsv<CsvRow> = StatefulBeanToCsvBuilder<CsvRow>(out)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build()
        sbc.write(csv)
        out.flush()
        out.close()
    }
}

fun main(args: Array<String>) {
    runApplication<CsvNormalizerApplication>(*args)
}
