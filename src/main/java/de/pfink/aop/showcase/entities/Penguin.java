package de.pfink.aop.showcase.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
/**
 *
 * @author Patrick
 */
@Entity
@Getter
@Setter
@Accessors(chain = true)
public class Penguin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column
    private Integer id;
    @Column
    private String name;
    @ManyToOne(optional = false)
    private Island home;
}
