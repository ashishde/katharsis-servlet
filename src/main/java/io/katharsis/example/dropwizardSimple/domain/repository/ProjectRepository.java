/**
 * 
 */
package io.katharsis.example.dropwizardSimple.domain.repository;

import com.google.common.collect.Iterables;

import io.katharsis.errorhandling.exception.ResourceNotFoundException;
import io.katharsis.example.dropwizardSimple.domain.model.Project;
import io.katharsis.legacy.queryParams.QueryParams;
import io.katharsis.legacy.repository.annotations.JsonApiDelete;
import io.katharsis.legacy.repository.annotations.JsonApiFindAll;
import io.katharsis.legacy.repository.annotations.JsonApiFindAllWithIds;
import io.katharsis.legacy.repository.annotations.JsonApiFindOne;
import io.katharsis.legacy.repository.annotations.JsonApiResourceRepository;
import io.katharsis.legacy.repository.annotations.JsonApiSave;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * @author ashishde
 *
 */

@SuppressWarnings("deprecation")
@JsonApiResourceRepository(Project.class)
public class ProjectRepository {
	private static final Map<Long, Project> REPOSITORY = new ConcurrentHashMap<>();
	private static final AtomicLong ID_GENERATOR = new AtomicLong(1);

	@JsonApiSave
	public <S extends Project> S save(S entity) {
		System.out.println("********** in Save function.. ");
	
		if (entity.getId() == null) {
			entity.setId(ID_GENERATOR.getAndIncrement());
		}
		REPOSITORY.put(entity.getId(), entity);
		return entity;
	}

	@JsonApiFindOne
    public Project findOne(Long id){
		System.out.println("********** in findOne function.. ");
        Project project = REPOSITORY.get(id);
        if (project == null) {
            throw new ResourceNotFoundException("Project not found");
        }
        return project;
    }

	@SuppressWarnings("deprecation")
	@JsonApiFindAll
	public Iterable<Project> findAll(QueryParams queryParams) {
		System.out.println("********** in findAll function.. ");
		return REPOSITORY.values();
	}

	@SuppressWarnings("deprecation")
	@JsonApiFindAllWithIds
	public Iterable<Project> findAll(Iterable<Long> iterable, io.katharsis.legacy.queryParams.QueryParams queryParams) {
		System.out.println("********** in findAll (with two params) function.. ");
		return REPOSITORY.entrySet().stream().filter(p -> Iterables.contains(iterable, p.getKey()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)).values();
	}

	@SuppressWarnings("deprecation")
	@JsonApiDelete
	public void delete(Long id) {
		System.out.println("********** in delete function.. ");
		REPOSITORY.remove(id);
	}
}
