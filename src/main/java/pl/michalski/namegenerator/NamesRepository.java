package pl.michalski.namegenerator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NamesRepository extends JpaRepository<Names, Long> {
    List<Names> findAllByType(NameType nameType);

}
