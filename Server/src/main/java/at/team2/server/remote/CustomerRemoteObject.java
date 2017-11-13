package at.team2.server.remote;

import at.team2.application.facade.CustomerApplicationFacade;
import org.modelmapper.ModelMapper;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import at.team2.application.helper.MapperHelper;
import at.team2.common.dto.small.CustomerSmallDto;
import at.team2.common.interfaces.CustomerRemoteObjectInf;
import at.team2.domain.entities.Customer;
import org.modelmapper.TypeToken;

public class CustomerRemoteObject extends UnicastRemoteObject implements CustomerRemoteObjectInf {
    private static Type typeSmall = new TypeToken<List<CustomerSmallDto>>() {}.getType();

    protected CustomerRemoteObject() throws RemoteException {
        super(0);
    }

    @Override
    public CustomerSmallDto getCustomerSmallById(int id) throws RemoteException {
        CustomerApplicationFacade facade = CustomerApplicationFacade.getInstance();
        ModelMapper mapper = MapperHelper.getMapper();
        Customer entity = facade.getById(id);

        if(entity != null) {
            return mapper.map(entity, CustomerSmallDto.class);
        }

        return null;
    }

    @Override
    public List<CustomerSmallDto> getList(String searchString) throws RemoteException {
        CustomerApplicationFacade facade = CustomerApplicationFacade.getInstance();
        ModelMapper mapper = MapperHelper.getMapper();

        return mapper.map(facade.search(searchString), typeSmall);
    }
}