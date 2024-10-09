package register.dto;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Builder
@Data
@NoArgsConstructor
public class ResidentDTO {
    private Integer id;
    private String name;
    private String email;
    private String cpf;

    @Override
    public String toString() {
        return "ResidentDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }


}
