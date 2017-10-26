package at.team2.database_wrapper.facade;

import at.team2.database_wrapper.enums.TransactionType;
import at.team2.database_wrapper.helper.MapperHelper;
import org.modelmapper.ModelMapper;
import at.team2.database_wrapper.entities.ReminderEntity;
import at.team2.database_wrapper.helper.StoreHelper;
import at.team2.domain.entities.Reminder;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.LinkedList;
import java.util.List;

public class ReminderFacade extends BaseDatabaseFacade<Reminder> {
    private static List<Reminder> listType = new LinkedList<>();

    public ReminderFacade() {
        super();
    }

    public ReminderFacade(EntityManager session) {
        super(session);
    }

    @Override
    public Reminder getById(int id) {
        EntityManager session = getCurrentSession();
        Query query = session.createQuery("from ReminderEntity where id = :id");
        query.setParameter("id", id);
        query.setMaxResults(1);
        List<ReminderEntity> entities = query.getResultList();

        if (entities.size() > 0) {
            ReminderEntity entity = entities.get(0);

            ModelMapper mapper = MapperHelper.getMapper();
            return mapper.map(entity, Reminder.class);
        }

        return null;
    }

    @Override
    public List<Reminder> getList() {
        EntityManager session = getCurrentSession();
        Query query = session.createQuery("from ReminderEntity ");
        List<ReminderEntity> entities = query.getResultList();
        ModelMapper mapper = MapperHelper.getMapper();

        return mapper.map(entities, listType.getClass());
    }

    @Override
    public int add(Reminder value, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        ModelMapper mapper = MapperHelper.getMapper();
        ReminderEntity entity = mapper.map(value, ReminderEntity.class);

        session.persist(entity);
        StoreHelper.storeEntities(session, transactionType);

        return entity.getId();
    }

    @Override
    public int update(Reminder value, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        ModelMapper mapper = MapperHelper.getMapper();
        ReminderEntity entity = mapper.map(value, ReminderEntity.class);

        session.merge(entity);
        StoreHelper.storeEntities(session, transactionType);

        return entity.getId();
    }

    @Override
    public boolean delete(int id, TransactionType transactionType) {
        EntityManager session = getCurrentSession(transactionType);
        Query query = session.createQuery("delete ReminderEntity where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();

        return StoreHelper.storeEntities(session, transactionType);
    }
}