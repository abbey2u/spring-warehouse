package nl.averageflow.springwarehouse.models;

import nl.averageflow.springwarehouse.requests.AddProductsRequestItem;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Set;
import java.util.UUID;

@Table(name = "products")
@Entity
public final class Product {
    @Id
    @GeneratedValue
    @Column(name = "uid", nullable = false)
    private UUID uid;

    @Column(name = "item_id", nullable = false)
    private long itemId;

    @Column(name = "item_name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ArticleAmountInProduct> articleProductRelation;


    protected Product() {
    }

    public Product(AddProductsRequestItem item) {
        this.itemId = item.getItemId();
        this.name = item.getName();
        this.price = item.getPrice();
    }

    public Product(Long itemId, String name, Double price) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
    }

    public UUID getUid() {
        return this.uid;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public long getProductStock() {
        ArrayList<Long> amountOfProductsPossibleList = new ArrayList<>();
        if (this.articleProductRelation == null || articleProductRelation.isEmpty()) {
            return 0L;
        }

        this.articleProductRelation.forEach(articleAmountInProduct -> {
            long articleAmountNeeded = articleAmountInProduct.getAmountOf();
            long articleStockPresent = articleAmountInProduct.getArticle().getStock();

            if (articleStockPresent >= articleAmountNeeded) {
                amountOfProductsPossibleList.add(articleStockPresent / articleAmountNeeded);
            }
        });

        if (amountOfProductsPossibleList.size() != this.articleProductRelation.size()) {
            return 0L;
        }


        Long smallestAmountPossible = amountOfProductsPossibleList.stream()
                .min(Comparator.naturalOrder())
                .get();
        return smallestAmountPossible;
    }

    public Set<ArticleAmountInProduct> getArticles() {
        return this.articleProductRelation;
    }

}