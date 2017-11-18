package at.team2.server.remote;

import at.team2.application.facade.MediaMemberApplicationFacade;
import at.team2.application.helper.MapperHelper;
import at.team2.common.dto.detailed.MediaMemberDetailedDto;
import at.team2.common.dto.small.MediaMemberSmallDto;
import at.team2.common.interfaces.MediaMemberRemoteObjectInf;
import at.team2.domain.entities.MediaMember;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class MediaMemberRemoteObject extends UnicastRemoteObject implements MediaMemberRemoteObjectInf {
    private static Type typeSmall = new TypeToken<List<MediaMemberSmallDto>>() {}.getType();

    public MediaMemberRemoteObject() throws RemoteException {
        super(0);
    }

    @Override
    public MediaMemberSmallDto getMediaMemberSmallById(int id) throws RemoteException {
        MediaMemberApplicationFacade facade = MediaMemberApplicationFacade.getInstance();
        ModelMapper mapper = MapperHelper.getMapper();
        MediaMember entity = facade.getById(id);

        if(entity != null) {
            return mapper.map(entity, MediaMemberSmallDto.class);
        }

        return null;
    }

    @Override
    public List<MediaMemberSmallDto> getMediaMemberSmallList() throws RemoteException {
        MediaMemberApplicationFacade facade = MediaMemberApplicationFacade.getInstance();
        ModelMapper mapper = MapperHelper.getMapper();

        return mapper.map(facade.getList(), typeSmall);
    }

    @Override
    public MediaMemberSmallDto getMediaMemberByIndex(String searchString) throws RemoteException {
        MediaMemberApplicationFacade facade = MediaMemberApplicationFacade.getInstance();
        MediaMember entity = facade.getMediaMemberByIndex(searchString);

        if(entity != null) {
            ModelMapper mapper = MapperHelper.getMapper();
            return mapper.map(entity, MediaMemberSmallDto.class);
        }

        return null;
    }

    @Override
    public MediaMemberDetailedDto getMediaMemberDetailedById(int id) throws RemoteException {
        MediaMemberApplicationFacade facade = MediaMemberApplicationFacade.getInstance();
        ModelMapper mapper = MapperHelper.getMapper();
        MediaMember entity = facade.getById(id);

        if(entity != null) {
            return mapper.map(entity, MediaMemberDetailedDto.class);
        }

        return null;
    }
}