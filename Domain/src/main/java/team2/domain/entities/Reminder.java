package team2.domain.entities;

import javafx.util.Pair;
import team2.domain.enums.properties.ReminderProperty;

import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class Reminder extends BaseDomainEntity<ReminderProperty>  {
    private int _id;
    private Date _reminderDate;
    private Integer _reminderCount;

    @Override
    public int getID() {
        return _id;
    }

    public Date getReminderDate() {
        return _reminderDate;
    }

    public Integer getReminderCount() {
        return _reminderCount;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setReminderDate(Date reminderDate) {
        _reminderDate = reminderDate;
    }

    public void setReminderCount(Integer reminderCount) {
        _reminderCount = reminderCount;
    }

    @Override
    public List<Pair<String, String>> validate() {
        // @todo: implement
        return new LinkedList<>();
    }
}