/**
 * 
 */
package io.katharsis.example.dropwizardSimple.domain.model;
import io.katharsis.resource.annotations.*;

/**
 * @author ashishde
 *
 */
@JsonApiResource(type = "projects")
public class Project {
	@JsonApiId
	  private Long id;

	  private String name;

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }
}
