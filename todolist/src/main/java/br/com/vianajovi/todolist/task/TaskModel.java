package br.com.vianajovi.todolist.task;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

 /**
     * ID
     * usuário (ID_USUARIO)
     * Descrição
     * Titulo resumido
     * Data de inicio - Data de término
     * prioridade
     */

@Data
@Entity(name = "TB_TASKS")
public class TaskModel {
   
     @Id
     @GeneratedValue (generator = "UUID")
     private  UUID id;
     @Column(length = 120)
     private String descricao;

     @Column(length = 40)
     @Size(min = 5)
     private String titulo;
     private LocalDateTime inicioEm; // yyyy--mm--ddTHH:mm:ss
     private LocalDateTime fimEm;   //  yyyy--mm--ddTHH:mm:ss
     private String prioridade;
     private UUID idUser;
     @CreationTimestamp
     private LocalDateTime CriadoEm;

}
