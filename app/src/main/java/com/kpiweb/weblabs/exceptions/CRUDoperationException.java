package com.kpiweb.weblabs.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CRUDoperationException extends IllegalStateException{
    private String fieldName;

    public CRUDoperationException(String s, String fieldName) {
        super(s);
        this.fieldName = fieldName;
    }
}
