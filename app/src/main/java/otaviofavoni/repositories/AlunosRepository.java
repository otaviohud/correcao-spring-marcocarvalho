package marcocarvalho.repositories;

import org.springframework.data.repository.CrudRepository;
import marcocarvalho.models.Aluno;

public interface AlunosRepository extends CrudRepository<Aluno, Integer> {
}