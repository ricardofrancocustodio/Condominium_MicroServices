package register.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import register.dto.ResidentDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final List<ResidentDTO> residentDTOList =  new ArrayList<>();
    @Override
    public ResidentDTO createResident(ResidentDTO residentDTO) {
        residentDTO.setId(residentDTO.getId());
        residentDTO.setName(residentDTO.getName());
        residentDTO.setCpf(residentDTO.getCpf());
        residentDTO.setEmail(residentDTO.getEmail());

        residentDTOList.add(residentDTO);

        return residentDTO;
    }

    @Override
    public ResidentDTO updateResident(Integer id, ResidentDTO residentDTO) {

        ResidentDTO residentDTOFound = findById(id);

        if (residentDTOFound == null) {
            throw new IllegalArgumentException("Resident not found with id " + id);
        }

        residentDTOFound.setName(residentDTO.getName());
        residentDTOFound.setCpf(residentDTO.getCpf());
        residentDTOFound.setEmail(residentDTO.getEmail());

        return residentDTOFound;
    }

    @Override
    public ResidentDTO findById(Integer id) {
        for (ResidentDTO residentDTO : residentDTOList) {
            if (residentDTO.getId().equals(id)) {
                return residentDTO;
            }
        }

        return null; // ou lançar uma exceção

    }

    @Override
    public List<ResidentDTO> findAll() {
        return residentDTOList;
    }

    @Override
    public void deleteResident(Integer id) {
        ResidentDTO residentDTO = findById(id);
        residentDTOList.remove(residentDTO);

    }
}
