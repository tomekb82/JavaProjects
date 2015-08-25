package com.angularjs.web.rest.dto;

import javax.persistence.ElementCollection;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 * A DTO for the Photo entity.
 */
public class PhotoDTO implements Serializable {

    private String id;

    private String name;

    private List<String> opinions;

    private String author;

    private Date date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOpinions() {
        return opinions;
    }

    public void setOpinions(List<String> opinions) {
        this.opinions = opinions;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PhotoDTO photoDTO = (PhotoDTO) o;

        if ( ! Objects.equals(id, photoDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PhotoDTO{" +
                "id=" + id +
                ", name='" + name + "'" +
                '}';
    }
}
