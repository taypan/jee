package cz.cvut.fel.jee.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * @author Vaclav Rechtberger
 */
@Entity
public class Manufacturer {
    @Id
    @GeneratedValue
    private long id;
    @NotNull
    @Size(min = 2, max = 25)
    @Pattern(regexp = "[a-zA-Z0-9ěščřžýáíéúůďťó.:~]", message = "Must contains only alfanumeric symbols and \"-\", \":\", \",\" or <space>")
    private String name;
    @NotNull
    @URL
    @Size(min = 2, max = 25)
    private String url;
}
