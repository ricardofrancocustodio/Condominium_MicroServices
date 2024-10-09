package register.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import register.dto.ResidentDTO;
import register.service.RegisterService;

import java.util.List;


@RestController
@RequestMapping("home")
public class RegisterController {

    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public ResidentDTO createNewResident(@RequestBody ResidentDTO residentDTO){
        //dá para chamar outros serviços
        //tratamento de exceções
        //segurança
       return registerService.createResident(residentDTO);

    }


    @PutMapping("edit/{name}")
    public ResidentDTO update(@RequestBody ResidentDTO residentDTO, Integer id){
        return registerService.editResident(residentDTO, id);

    }


    @DeleteMapping("delete/{id}")
    public void delete(@RequestBody ResidentDTO residentDTO){
        registerService.deleteResident(residentDTO.getId());
    }

    @GetMapping("{id}")
    public ResidentDTO findById(@PathVariable Integer id){
            return registerService.findById(id);
    }


    @GetMapping("resident-list")
    public List<ResidentDTO> findAll(){
        return registerService.findAll();
    }

}
