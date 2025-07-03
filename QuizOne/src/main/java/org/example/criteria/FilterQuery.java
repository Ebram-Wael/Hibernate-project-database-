package org.example.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilterQuery {
  private String Attribute;
  private Object value ;
  private Operator op;

}
