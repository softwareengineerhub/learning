package com.nyavro.app01;

import com.example.Customer;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;

public class GenericRecordExample {

    public static void main(String[] args) {

        Customer.Builder customerBuilder = Customer.newBuilder();
        customerBuilder.setAge(25);
        customerBuilder.setFirstName("FN");
        customerBuilder.setLastName("LN");
        customerBuilder.setHeight(1.3f);
        customerBuilder.setWeight(2.2f);
        customerBuilder.setAutomatedEmail(true);
        Customer customer = customerBuilder.build();
        System.out.println(customer);

        System.out.println("--------------------------");


        System.out.println("--------------------------");
        writeToFile(customer);

        System.out.println("--------------------------");
        readFromFile();
    }


    private static void writeToFile(Customer customer) {
        DatumWriter<Customer> datumWriter = new SpecificDatumWriter<>(Customer.class);
        try (DataFileWriter<Customer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.create(customer.getSchema(), new File("customer-generic.avro"));
            dataFileWriter.append(customer);
            System.out.println("Written customer-generic.avro");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void readFromFile() {
        File file = new File("customer-generic.avro");
        DatumReader<Customer> datumReader = new SpecificDatumReader<>(Customer.class);
        try (DataFileReader<Customer> dataFileWriter = new DataFileReader<>(file, datumReader)) {
            Customer record = dataFileWriter.next();
            System.out.println("Reading from customer-generic.avro:");
            System.out.println(record);
            System.out.println("Getting age="+record.get("age"));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

