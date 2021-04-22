package com.app.my;

import org.apache.cassandra.db.Mutation;
import org.apache.cassandra.db.marshal.AbstractType;
import org.apache.cassandra.db.partitions.Partition;
import org.apache.cassandra.db.rows.*;
import org.apache.cassandra.triggers.ITrigger;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;

public class DenisTrigger implements ITrigger {


    @Override
    public Collection<Mutation> augment(Partition partition) {
        String msg = new Date() + " !!!TRIGGER!!! start";
        System.out.println(msg);

        UnfilteredRowIterator itr = partition.unfilteredIterator();
        while (itr.hasNext()) {
            Unfiltered unfiltered = itr.next();
            process(unfiltered);
        }
        try {
            Thread.sleep(60000);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
        System.out.println(new Date() + " !!!TRIGGER!!! finish");


        return Collections.emptyList();
    }

    private void process(Unfiltered unfiltered){
        switch (unfiltered.kind()){
            case ROW:
                Row row = (Row) unfiltered;
                process(row);
                break;
            case RANGE_TOMBSTONE_MARKER:
                System.out.println("deletion event");
        }
    }

    private void process(Row row){
        System.out.println("-------------process row");
        System.out.println("-------------process column data");
        Iterator<ColumnData> itr =  row.iterator();
        while(itr.hasNext()){
            ColumnData columnData = itr.next();
            System.out.println("\tcolumnData="+columnData);
            //columnData.column().
        }

        System.out.println("-------------process cell data");
        for(Cell cell: row.cells()){
            AbstractType type  = cell.column().type;
            System.out.println("\ttype="+type);
            ByteBuffer bb = cell.value();
            System.out.println("\tvalue="+bb);

        }
    }

}
