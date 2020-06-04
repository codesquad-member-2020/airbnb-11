package kr.codesquad.airbnb11.domain.user;

import java.util.Optional;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

  Optional<User> findByEmail(String email);
}
