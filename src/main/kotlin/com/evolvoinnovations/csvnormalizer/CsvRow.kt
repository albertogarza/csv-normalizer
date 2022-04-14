package com.evolvoinnovations.csvnormalizer

import com.opencsv.bean.CsvBindByName
import com.opencsv.bean.CsvBindByPosition
import java.io.Serializable
import java.nio.ByteBuffer
import java.nio.charset.Charset
import java.nio.charset.CodingErrorAction
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*


class CsvRow : Serializable {
    @CsvBindByName(column = "Timestamp")
    @CsvBindByPosition(position = 0)
    var timestamp: String = ""
        get() {
            val formatter = DateTimeFormatter.ofPattern("M/d/yy h:m:s a", Locale.ENGLISH)
            val date = Date.from(LocalDateTime.parse(field, formatter).atZone(ZoneId.of("America/Los_Angeles")).withZoneSameInstant(ZoneId.of("America/New_York")).toInstant())
            return SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").format(date)
        }
        set(value) {
            field = value
        }

    @CsvBindByName(column = "Address")
    @CsvBindByPosition(position = 1)
    var address: String = ""


    @CsvBindByName(column = "ZIP")
    @CsvBindByPosition(position = 2) 
    var zip: String = ""
        get() {
            return field.padStart(5, '0')
        }
        set(value) {
            field = value
        }

    @CsvBindByName(column = "FullName")
    @CsvBindByPosition(position = 3)
    var fullName: String = ""
        get() {
            return field.uppercase()
        }
        set(value) {
            field = value
        }

    @CsvBindByName(column = "FooDuration")
    @CsvBindByPosition(position = 4) 
    var fooDuration: String = ""
        get() {
            // HH:MM:SS.MS 
            val split = field.split(":") 
            return Duration.parse("PT${split[0]}H${split[1]}M${split[2]}S").toSeconds().toString()
        }
        set(value) {
            field = value
        }

    @CsvBindByName(column = "BarDuration")
    @CsvBindByPosition(position = 5)
    var barDuration: String = ""
        get() {
            val split = field.split(":") 
            return Duration.parse("PT${split[0]}H${split[1]}M${split[2]}S").toSeconds().toString()
        }
        set(value) {
            field = value
        }

    @CsvBindByName(column = "TotalDuration")
    @CsvBindByPosition(position = 6)
    var totalDuration: String = ""
        get() {
            return (fooDuration.toInt() + barDuration.toInt()).toString()
        }

    @CsvBindByName(column = "Notes")
    @CsvBindByPosition(position = 7) 
    var notes: String = ""
        get() {
            // source: https://stackoverflow.com/a/2869155/3621222
            val utf8Decoder = Charset.forName("UTF-8").newDecoder()
            utf8Decoder.onMalformedInput(CodingErrorAction.REPLACE)
            utf8Decoder.onUnmappableCharacter(CodingErrorAction.REPLACE)
            val parsed = utf8Decoder.decode(ByteBuffer.wrap(field.toByteArray()))
            return parsed.toString()
        }
        set(value) {
            field = value
        }
}