package register.service;

import org.springframework.stereotype.Service;
import register.dto.ResidentDTO;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {

    private List<ResidentDTO> residentDTOList =  new ArrayList<>();
    @Override
    public ResidentDTO createResident(ResidentDTO residentDTO) {
        residentDTOList.add(residentDTO);

        return residentDTO;
    }

    @Override
    public ResidentDTO editResident(ResidentDTO residentDTO, Integer id) {
        ResidentDTO residentDTOFound = findById(residentDTO.getId());
        residentDTOFound.setName(residentDTO.getName());
        residentDTOFound.setCpf(residentDTO.getCpf());
        residentDTOFound.setEmail(residentDTO.getEmail());

        return residentDTOFound;
    }

    @Override
    public ResidentDTO findById(Integer id) {
        return residentDTOList.stream()
                .filter(residentDTO -> false)
                .findFirst()
                .get();
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
