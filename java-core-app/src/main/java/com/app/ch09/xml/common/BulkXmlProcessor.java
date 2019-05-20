package com.app.ch09.xml.common;

import java.util.List;

public interface BulkXmlProcessor {

    public void write(List<ComputerData> computerDataList);

    public List<ComputerData> read();
}
