package org.example;

import org.example.criteria.FilterQuery;
import org.example.criteria.Operator;
import org.example.deo.DBBlog;
import org.example.deo.DBComment;
import org.example.models.Blog;
import org.example.models.Comment;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DBBlog dbBlog = new DBBlog();
        Blog b1 = new Blog();
        b1.setId(1);
        b1.setTitle("Better Lesson");
        b1.setPublishedDate(new Date("13-sep-2023"));
        dbBlog.insert(b1);
        Blog b2 = new Blog();
        b2.setId(2);
        b2.setTitle("Edutopia");
        b2.setPublishedDate(new Date("09-Oct-2005"));

        Comment c1 = new Comment(1, "great blogs", 'T', 1);
        Comment c2 = new Comment(2, "find something else", 'F', 1);
        DBComment dbComment = new DBComment();
        dbComment.insert(c1);
        dbComment.insert(c2);

        List<FilterQuery> f =new ArrayList<>();
        f.add(new FilterQuery("id",0, Operator.GT));
        f.add(new FilterQuery("isApproved",'T', Operator.EQ));
        System.out.println(new DBComment().filterComment(f));

    }
}