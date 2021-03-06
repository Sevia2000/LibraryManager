package at.team2.database_wrapper.facade;

import at.team2.database_wrapper.common.FilterConnector;
import at.team2.database_wrapper.enums.TransactionType;
import at.team2.database_wrapper.interfaces.BaseDatabaseFacade;
import at.team2.domain.enums.properties.LoanConditionProperty;
import org.modelmapper.ModelMapper;
import at.team2.database_wrapper.entities.LoanConditionEntity;
import at.team2.database_wrapper.helper.MapperHelper;
import at.team2.database_wrapper.helper.StoreHelper;
import at.team2.domain.entities.LoanCondition;
import org.modelmapper.TypeToken;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.lang.reflect.Type;
import java.util.List;

public class LoanConditionFacade extends BaseDatabaseFacade<LoanCondition, LoanConditionProperty> {
    private static final Type type = new TypeToken<List<LoanCondition>>() {}.getType();

    public LoanConditionFacade() {
        super();
    }

    public LoanConditionFacade(EntityManager session) {
        super(session);
    }

    @Override
    public LoanCondition getById(int id) {
        EntityManager session = getCurrentSession();
        Query query = session.createQuery("from LoanConditionEntity where id = :id");
        query.setParameter("id", id);
        LoanConditionEntity entity = getFirstOrDefault(query);

        if(entity != null) {
            ModelMapper mapper = MapperHelper.getMapper();
            return mapper.map(entity, LoanCondition.class);
        }

        return null;
    }

    @Override
    public List<LoanCondition> getList() {
        EntityManager session = getCurrentSession();
        Query query = session.createQuery("from LoanConditionEntity");
        List<LoanConditionEntity> entities = query.getResultList();
        ModelMapper mapper = MapperHelper.getMapper();

        return mapper.map(entities, type);
    }

    @Override
    protected String getColumnNameForProperty(LoanConditionProperty property) {
        switch (property) {
            case ID:
                return "id";
            case EXTENSION:
                return "extension";
            case LOAN_TERM:
                return "loanTerm";
        }

        return null;
    }

    @Override
    public List<LoanCondition> filter(FilterConnector<LoanConditionProperty, LoanConditionProperty> filterConnector) {
        Query query = getByFilter("from LoanConditionEntity where", getCurrentSession(), filterConnector);
        List<LoanConditionEntity> entities = query.getResultList();
        ModelMapper mapper = MapperHelper.getMapper();

        return mapper.map(entities, type);
    }

    @Override
    public int add(LoanCondition value, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        ModelMapper mapper = MapperHelper.getMapper();
        LoanConditionEntity entity = mapper.map(value, LoanConditionEntity.class);

        session.persist(entity);
        StoreHelper.storeEntities(session, transactionType);

        return entity.getId();
    }

    @Override
    public int update(LoanCondition value, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        ModelMapper mapper = MapperHelper.getMapper();
        LoanConditionEntity entity = mapper.map(value, LoanConditionEntity.class);

        session.merge(entity);
        StoreHelper.storeEntities(session, transactionType);

        return entity.getId();
    }

    @Override
    public boolean delete(int id, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        Query query = session.createQuery("delete LoanConditionEntity where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        return StoreHelper.storeEntities(session, transactionType);
    }
}