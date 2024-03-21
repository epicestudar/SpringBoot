// package com.example.projeto_escola.Repository;

// import java.util.List;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.CrudRepository;
// import com.example.projeto_escola.Model.Administrador;

// public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
//     // criado para a busca Funcionario por id ou chave primária
//     Administrador findById(long id);

//     // criado para a busca Funcionario por nome
//     Administrador findByNome(String nome);

//     // Busca para vários nomes Funcionários
//     @Query(value = "select u from Funcionario u where u.nome like %?1%")
//     List<Administrador> findByNomes(String nome);
// }
