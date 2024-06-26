package br.com.vianajovi.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface IUserRepository extends JpaRepository<UserModel, UUID> {
    /** Definição de métodos
     * 
     */
    UserModel findByUsername(String username);
}
