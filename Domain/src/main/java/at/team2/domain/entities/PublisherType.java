package at.team2.domain.entities;

import at.team2.domain.enums.properties.PublisherTypeProperty;
import at.team2.domain.interfaces.BaseDomainEntity;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.List;

public class PublisherType extends BaseDomainEntity<PublisherTypeProperty> {
    private int _id;
    private String _typeName;

    @Override
    public int getId() {
        return _id;
    }

    public String getTypeName() {
        return _typeName;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setTypeName(String typeName) {
        _typeName = typeName;
    }

    @Override
    public List<Pair<PublisherTypeProperty, String>> validate() {
        // @todo: implement
        return new LinkedList<>();
    }
}