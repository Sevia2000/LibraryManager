package at.team2.domain.entities;

import at.team2.domain.interfaces.BaseDomainEntity;
import javafx.util.Pair;
import at.team2.domain.enums.properties.LoanConditionProperty;

import java.util.LinkedList;
import java.util.List;

public class LoanCondition extends BaseDomainEntity<LoanConditionProperty> {
    private int _id;
    private int _loanTerm;
    private int _extension;

    @Override
    public int getId() {
        return _id;
    }

    public int getLoanTerm() {
        return _loanTerm;
    }

    public int getExtension() {
        return _extension;
    }

    public void setId(int id) {
        _id = id;
    }

    public void setLoanTerm(int loanTerm) {
        _loanTerm = loanTerm;
    }

    public void setExtension(int extension) {
        _extension = extension;
    }

    @Override
    public List<Pair<LoanConditionProperty, String>> validate() {
        // @todo: implement
        return new LinkedList<>();
    }
}