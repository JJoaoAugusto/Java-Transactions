package br.com.joaoaugusto.springbootapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.joaoaugusto.springbootapplication.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsUserByCpf(final String cpf);
    boolean existsUserByEmail(final String email);
}
