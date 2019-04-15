package com.app.ch06.io.app03.serial.bulk;

import com.app.ch06.io.app03.serial.MyCup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class BulkOperationImpl implements BulksOperation {


    @Override
    public void save(List<MyCup> list, String filePath) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath))) {
            out.writeObject(list);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<MyCup> read(String filePath) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            return (List<MyCup>) in.readObject();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
