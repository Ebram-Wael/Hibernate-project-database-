package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Blogs", schema = "system")
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
public class Blog implements Serializable {
    private static final long serialVersionUID = -915428707036605461L;
    @Id
    @Column(name = "ID")
    private Integer Id;
    @Column(name = "title")
    private String title;
    @Column(name = "published_data")
    private Date publishedDate;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "BLOG_COMMENT",
            joinColumns = {@JoinColumn(name = "BLOG_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COMMENT_ID")}
    )
    private List<Comment> comments;
}
