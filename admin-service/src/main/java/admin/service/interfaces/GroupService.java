package admin.service.interfaces;

import admin.dto.GroupDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    List<GroupDTO> getAllGroups();
    GroupDTO getGroupById(Long id);
    GroupDTO createGroup(GroupDTO groupDTO);
    GroupDTO updateGroup(Long id, GroupDTO groupDTO);
    void deleteGroup(Long id);
}
