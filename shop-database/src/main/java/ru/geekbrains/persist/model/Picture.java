package ru.geekbrains.persist.model;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "content_type", nullable = false)
    private String contentType;       //  тип картинки напрмер:  image/jpeg

    @OneToOne(fetch = FetchType.LAZY, cascade= CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name="picture_data_id")
    private PictureData pictureData;

    @ManyToOne
    private Product product;

    public Picture() {
    }

    public Picture(String name, String contentType, PictureData pictureData, Product product) {
        this.name = name;
        this.contentType = contentType;
        this.pictureData = pictureData;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public PictureData getPictureData() {
        return pictureData;
    }

    public void setPictureData(PictureData pictureData) {
        this.pictureData = pictureData;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
