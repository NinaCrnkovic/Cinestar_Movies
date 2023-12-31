/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Nina
 */
@XmlAccessorType(XmlAccessType.FIELD)
public final class Genre {
@XmlAttribute
    private int id;
     @XmlElement(name = "genre")
    private String name;

    public Genre() {
    }

    
    public Genre(String name) {
        this.name = name;
    }

    public Genre(int id, String name) {
        this(name);
        this.id = id;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
