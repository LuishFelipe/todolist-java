package br.com.luishfelipe.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.context.annotation.Bean;

public class Utils {
  
  public static void copyNonNullProperties(Object Source, Object Target){
    BeanUtils.copyProperties(Source, Target, getNullPropertyNames(Source));
  }

  public static String[] getNullPropertyNames(Object Source) {
    final BeanWrapper src = new BeanWrapperImpl(Source);
    
    PropertyDescriptor[] pds = src.getPropertyDescriptors();

    Set<String> emptyNames = new HashSet<>();
    
    for(PropertyDescriptor pd: pds){
      Object Values = src.getPropertyValue(pd.getName());
      if(Values == null){
        emptyNames.add(pd.getName());
      }
    }
    String[] results = new String[emptyNames.size()];
    return emptyNames.toArray(results);
  }
}
