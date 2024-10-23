package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Resource;
import pl.paulb.dndmanager.repository.ResourceRepository;

import java.util.List;

@Service
public class ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;


    public Resource uploadResource(Resource resource) {
        return resourceRepository.save(resource);
    }

    public List<Resource> getResourcesForCampaign(Long campaignId) {
        return resourceRepository.findByCampaignId(campaignId);
    }

    public boolean deleteResource(Long id) {
        return resourceRepository.findById(id).map(resource -> {
            resourceRepository.delete(resource);
            return true;
        }).orElse(false);
    }
}

