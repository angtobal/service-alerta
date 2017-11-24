package pe.org.alerta.sismo.persist;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface SismoRepository extends PagingAndSortingRepository<Sismo, String> {
    //List<User> findByLastname(String lastname);
    //Page<User> findByFirstname(String firstname, Pageable pageable);
} 