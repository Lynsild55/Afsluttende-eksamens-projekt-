package fr.hovedopgave.demo.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntityDTO {
    private int id; 
    private String username; 
    private List<RoleDTO> roles; 
}
