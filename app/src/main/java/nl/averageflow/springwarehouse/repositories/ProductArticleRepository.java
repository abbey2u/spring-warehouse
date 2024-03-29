package nl.averageflow.springwarehouse.repositories;

import nl.averageflow.springwarehouse.models.ArticleAmountInProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface ProductArticleRepository extends CrudRepository<ArticleAmountInProduct, Long> {

    @NonNull
    Set<ArticleAmountInProduct> findAll();

    @Transactional
    void deleteByArticleUid(@NonNull UUID uid);
}
