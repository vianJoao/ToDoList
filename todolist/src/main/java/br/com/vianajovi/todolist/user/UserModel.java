package br.com.vianajovi.todolist.user;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import javax.validation.constraints.Size;
 
@Data //Getter e Setter
@Entity (name = "tb_users")

public class UserModel {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Column(unique = true) 
    private String username;
    /**
     * Coluna username se torna única
     */
    private String name;
    
    //@Column (length = 8)
    @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
    private String password;

    @CreationTimestamp
    private LocalDateTime createdAt;

}