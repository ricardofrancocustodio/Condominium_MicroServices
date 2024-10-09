package register.service;


import register.dto.ResidentDTO;

import java.util.List;

public interface RegisterService {
    ResidentDTO createResident(ResidentDTO residentDTO);
    ResidentDTO editResident(ResidentDTO residentDTO, Integer id);
    ResidentDTO findById(Integer id);
    List<ResidentDTO> findAll();
    void deleteResident(Integer id);
}
