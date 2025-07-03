package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Comments", schema = "system")
@Data
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {
    private static final long serialVersionUID = -915428707036605461L;
    @Id
    @Column(name = "id")
    private Integer Id;
    @Column(name = "content")
    private String content;
    @Column(name = "is_approved")
    private char isApproved;
    @Column(name = "blog_id")
    private Integer blogId;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "BLOG_COMMENT",
//            joinColumns = {@JoinColumn(name = "COMMENT_ID")},
//            inverseJoinColumns = {@JoinColumn(name = "BLOG_ID")}
//    )
//    private List<Blog> blogs;
}
