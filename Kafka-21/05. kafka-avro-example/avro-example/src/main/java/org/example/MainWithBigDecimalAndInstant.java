package org.example;

import org.apache.avro.Conversions;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;
import java.math.BigDecimal;
import java.nio.ByteBuffer;

public class MainWithBigDecimalAndInstant {

    public static void main(String[] args) {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse("""
                {
                  "type": "record",
                  "namespace": "com.example",
                  "name": "Customer",
                  "doc": "Avro Schema for our Customer",
                  "fields": [
                    {
                      "name": "amount",
                      "type": [
                        "null",
                        {
                          "type": "bytes",
                          "logicalType": "decimal",
                          "precision": 10,
                          "scale": 2
                        }
                      ],
                      "default": null
                    },
                    { "name": "last_name", "type": "string" },
                    { "name": "age", "type": "int" },
                    { "name": "height", "type": "float" },
                    { "name": "weight", "type": "float" },
                    { "name": "automated_email", "type": "boolean", "default": true },
                    { "name": "created_at", "type": "long", "logicalType": "timestamp-millis" }
                  ]
                }
                """);
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        //customerBuilder.set("amount", BigDecimal.valueOf(3.4567));
        // get the actual decimal schema (second element of union)

        BigDecimal value = new BigDecimal("3.45"); // must respect scale=2
        Schema amountSchema = schema.getField("amount").schema().getTypes().get(1);
        LogicalTypes.Decimal decimalType = (LogicalTypes.Decimal) LogicalTypes.fromSchema(amountSchema);
        Conversions.DecimalConversion conversion = new Conversions.DecimalConversion();
        ByteBuffer bytes = conversion.toBytes(value, amountSchema, decimalType);
        customerBuilder.set("amount", bytes);

        customerBuilder.set("last_name", "Doe");
        customerBuilder.set("age", 25);
        customerBuilder.set("height", 170f);
        customerBuilder.set("weight", 80.5f);
        customerBuilder.set("automated_email", false);
        customerBuilder.set("created_at", System.currentTimeMillis());
        GenericData.Record customer = customerBuilder.build();
        System.out.println(customer);

        System.out.println("--------------------------");
        System.out.println("--------------------------");
        writeToFile(schema, customer);

        System.out.println("--------------------------");
        readFromFile(schema, customer);
    }

    private static void writeToFile(Schema schema, GenericData.Record customer) {
        DatumWriter<GenericRecord> datumWriter = new GenericDatumWriter<>(schema);
        try (DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-generic.avro"));
            dataFileWriter.append(customer);
            System.out.println("Written customer-generic.avro");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void readFromFile(Schema schema, GenericData.Record customer) {
        File file = new File("customer-generic.avro");
        DatumReader<GenericRecord> datumReader = new GenericDatumReader<>(schema);
        try (DataFileReader<GenericRecord> dataFileWriter = new DataFileReader<>(file, datumReader)) {
            GenericRecord record = dataFileWriter.next();
            System.out.println("Reading from customer-generic.avro:");
            System.out.println(record);
            System.out.println("Getting age=" + record.get("age"));

            ByteBuffer storedBytes = (ByteBuffer) record.get("amount");
            Schema amountSchema = record.getSchema().getField("amount").schema().getTypes().get(1);
            LogicalTypes.Decimal decimalType = (LogicalTypes.Decimal) LogicalTypes.fromSchema(amountSchema);
            BigDecimal amount = new Conversions.DecimalConversion().fromBytes(storedBytes, amountSchema, decimalType);
            System.out.println("Getting amount=" + amount);

            Object createdAt = record.get("created_at");
            System.out.println("created_at=" + createdAt);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}