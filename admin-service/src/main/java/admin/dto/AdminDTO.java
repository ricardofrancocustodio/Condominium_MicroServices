package admin.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private List<String> phoneNumbers;


    public String getPassword() {
        return "";
    }
}
