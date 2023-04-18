package org.qmbupt.grp88.Entity;

import java.util.ArrayList;

public class CourseList extends ArrayList {
    public CourseList(){
        this.add(new Course("RFID","EBU0001",4));
        this.add(new Course("Microprocessor","EBU0002",3));
        this.add(new Course("Software Engineer","EBU0003",4));
        this.add(new Course("Wireless Sensor","EBU0004",2));
        this.add(new Course("Mobile Internet","EBU0005",2));
        this.add(new Course("Information processing technology","EBU0006",2));
        this.add(new Course("PE","EBU0007",2));
    }
}
