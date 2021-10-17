package com.app;

import org.apache.avro.Schema;
import org.apache.avro.file.CodecFactory;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        Schema schema = ReflectData.get().getSchema(ReflectedCustomer.class);
        System.out.println("schema="+schema.toString(true));
        ReflectedCustomer customer = new ReflectedCustomer();
        customer.setFirstName("FN");
        customer.setLastName("LN");
        customer.setNickName("NN");
        writeToFile(schema, customer);
        readFromFile();

    }


    private static void writeToFile(Schema schema, ReflectedCustomer customer) {
        System.out.println("Writing customer-reflected.avro");
        File file = new File("customer-reflected.avro");
        DatumWriter<ReflectedCustomer> datumWriter = new ReflectDatumWriter<>(ReflectedCustomer.class);
        try (DataFileWriter<ReflectedCustomer> dataFileWriter = new DataFileWriter<>(datumWriter)) {
            dataFileWriter.setCodec(CodecFactory.deflateCodec(9));
            dataFileWriter.create(schema, file);
            dataFileWriter.append(customer);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void readFromFile() {
        System.out.println("Reading customer-reflected.avro");
        File file = new File("customer-reflected.avro");
        DatumReader<ReflectedCustomer> datumReader = new ReflectDatumReader<>(ReflectedCustomer.class);
        try (DataFileReader<ReflectedCustomer> dataFileReader = new DataFileReader<ReflectedCustomer>(file, datumReader)) {
            ReflectedCustomer record = dataFileReader.next();
            System.out.println("Reading from customer-generic.avro");
            System.out.println(record);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
