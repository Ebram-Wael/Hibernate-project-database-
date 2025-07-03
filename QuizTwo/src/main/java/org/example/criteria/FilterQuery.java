package org.example.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterQuery {
  private String attribute;
  private Object value ;
  private Operator op;
//  private String type;// int double boolean
}
