package com.app;

import com.example.Customer;
import com.example.CustomerV2;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Customer customerV1 = Customer.newBuilder()
                .setAge(34)
                .setAutomatedEmail(false)
                .setFirstName("FN")
                .setLastName("LN")
                .setHeight(178f)
                .setWeight(75f)
                .build();

        final DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
        final DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter);
        dataFileWriter.create(customerV1.getSchema(), new File("customerV1.avro"));
        dataFileWriter.append(customerV1);
        dataFileWriter.close();
        System.out.println("successfully wrote customerV1.avro");

        System.out.println("----------------------------------");
        System.out.println("Reading out customerV1.avro with v2 schema");
        final File file = new File("customerV1.avro");
        final DatumReader<CustomerV2> datumReaderV2 = new SpecificDatumReader<>(CustomerV2.class);
        final DataFileReader<CustomerV2> dataFileReaderV2 = new DataFileReader<CustomerV2>(file, datumReaderV2);
        while(dataFileReaderV2.hasNext()){
            CustomerV2 customerV2read = dataFileReaderV2.next();
            System.out.println("Customer V2 = "+customerV2read.toString());
        }
        System.out.println("Backward schema evolution successful");

    }

}
