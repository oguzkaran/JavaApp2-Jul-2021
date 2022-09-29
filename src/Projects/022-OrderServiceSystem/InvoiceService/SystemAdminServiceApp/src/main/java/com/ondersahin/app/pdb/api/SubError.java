package com.ondersahin.app.pdb.api;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author onder sahin
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubError implements Serializable {
    private String name;
    private String description;

    public SubError() {
        this(null, null);
    }

    public SubError(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
