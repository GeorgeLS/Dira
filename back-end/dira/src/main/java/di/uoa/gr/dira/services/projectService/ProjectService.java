package di.uoa.gr.dira.services.projectService;

import di.uoa.gr.dira.entities.customer.Customer;
import di.uoa.gr.dira.entities.project.Project;
import di.uoa.gr.dira.models.project.ProjectModel;
import di.uoa.gr.dira.models.project.ProjectUsersModel;
import di.uoa.gr.dira.repositories.CustomerRepository;
import di.uoa.gr.dira.repositories.ProjectRepository;
import di.uoa.gr.dira.services.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProjectService extends BaseService<ProjectModel, Project, Long, ProjectRepository> implements IProjectService {
    CustomerRepository customerRepository;

    ProjectService(ProjectRepository repository, CustomerRepository customerRepository, ModelMapper mapper) {
        super(repository, mapper);
        this.customerRepository = customerRepository;
    }

    @Override
    public ProjectUsersModel findUsersByProjectId(Long id) {
        return repository.findById(id).map(project -> mapper.map(project, ProjectUsersModel.class)).orElse(null);
    }

    @Override
    public void addUserToProjectWithId(Long id, Long userId) {
        Project project = repository.findById(id).orElse(null);

        if (project != null) {
            // TODO: search the list by userId, without querying the database
            Customer customer = customerRepository.findById(userId).orElse(null);
            if (customer != null) {
                project.getCustomers().add(customer);
                repository.save(project);
            }
        }
    }

    @Override
    // TODO: fix
    public ProjectModel updateProjectWithId(ProjectModel projectModel) {
        super.delete(projectModel);
        ProjectModel updated = super.save(projectModel);
        Optional<Project> project = repository.findById(updated.getId());
        if (!project.isPresent()) return null;

        // TODO: maybe need a seperate model
        return null;
    }

    @Override
    // TODO: fix
    public void deleteUserFromProjectWithId(Long id, Long userId) {
        Project project = repository.findById(id).orElse(null);

        if (project != null) {
            // TODO: search the list by userId, without querying the database
            Customer customer = customerRepository.findById(userId).orElse(null);
            if (customer != null) {
                project.getCustomers().remove(customer);
                repository.save(project);
            }
        }
    }

}