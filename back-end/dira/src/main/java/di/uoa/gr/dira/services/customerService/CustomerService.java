package di.uoa.gr.dira.services.customerService;

import di.uoa.gr.dira.entities.customer.Customer;
import di.uoa.gr.dira.entities.project.Project;
import di.uoa.gr.dira.models.customer.CustomerModel;
import di.uoa.gr.dira.models.project.ProjectModel;
import di.uoa.gr.dira.repositories.CustomerRepository;
import di.uoa.gr.dira.security.PasswordManager;
import di.uoa.gr.dira.services.BaseService;
import di.uoa.gr.dira.shared.SubscriptionPlanEnum;
import di.uoa.gr.dira.util.mapper.MapperHelper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService extends BaseService<CustomerModel, Customer, Long, CustomerRepository> implements ICustomerService {
    public CustomerService(CustomerRepository repository, ModelMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public boolean authenticateUser(String username, String password) {
        return repository.findByUsername(username)
                .map(customer -> PasswordManager.encoder().matches(password, customer.getPassword()))
                .orElse(false);
    }

    @Override
    public CustomerModel findByUsername(String username) {
        return repository.findByUsername(username)
                .map(customer -> mapper.<CustomerModel>map(customer, modelType))
                .orElse(null);
    }

    @Override
    public CustomerModel findByEmail(String email) {
        return repository.findByEmail(email)
                .map(customer -> mapper.<CustomerModel>map(customer, modelType))
                .orElse(null);
    }

    @Override
    public void updatePlan(Long customerId) {
        Customer customer = repository.findById(customerId).orElse(null);
        if (customer != null) {
            customer.setSubscriptionPlanFromEnum(SubscriptionPlanEnum.PREMIUM);
            repository.save(customer);
        }
    }

    @Override
    public List<ProjectModel> getAllProjects(Long customerId) {
        return repository.findById(customerId)
                .map(customer -> MapperHelper.<Project, ProjectModel>mapList(mapper, customer.getProjects(), ProjectModel.class))
                .orElse(null);
    }
}
