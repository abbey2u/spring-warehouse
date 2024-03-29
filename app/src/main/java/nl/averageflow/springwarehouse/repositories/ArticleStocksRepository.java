package nl.averageflow.springwarehouse.repositories;

import nl.averageflow.springwarehouse.models.ArticleStock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ArticleStocksRepository extends CrudRepository<ArticleStock, UUID> {

    @NonNull
    Set<ArticleStock> findAll();

    @Transactional
    void deleteByArticleUid(@NonNull UUID uid);
}