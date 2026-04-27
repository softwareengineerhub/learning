package org.example;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.*;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Schema.Parser parser = new Schema.Parser();
        Schema schema = parser.parse("""
                {
                     "type": "record",
                     "namespace": "com.example",
                     "name": "Customer",
                     "doc": "Avro Schema for our Customer",
                     "fields": [
                       { "name": "first_name", "type": "string", "doc": "First Name of Customer" },
                       { "name": "last_name", "type": "string", "doc": "Last Name of Customer" },
                       { "name": "age", "type": "int", "doc": "Age at the time of registration" },
                       { "name": "height", "type": "float", "doc": "Height at the time of registration in cm" },
                       { "name": "weight", "type": "float", "doc": "Weight at the time of registration in kg" },
                       { "name": "automated_email", "type": "boolean", "default": true, "doc": "Field indicating if the user is enrolled in marketing emails" }
                     ]
                }
                """);
        GenericRecordBuilder customerBuilder = new GenericRecordBuilder(schema);
        customerBuilder.set("first_name", "John");
        customerBuilder.set("last_name", "Doe");
        customerBuilder.set("age", 25);
        customerBuilder.set("height", 170f);
        customerBuilder.set("weight", 80.5f);
        customerBuilder.set("automated_email", false);
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
            System.out.println("Getting age="+record.get("age"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}