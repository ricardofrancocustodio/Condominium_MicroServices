package register.service;


import register.dto.ResidentDTO;

import java.util.List;

public interface RegisterService {
    ResidentDTO createResident(ResidentDTO residentDTO);
    ResidentDTO updateResident(Integer id, ResidentDTO residentDTO);
    ResidentDTO findById(Integer id);
    List<ResidentDTO> findAll();
    void deleteResident(Integer id);
}
