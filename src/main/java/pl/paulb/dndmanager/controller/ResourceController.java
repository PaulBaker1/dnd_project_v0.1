package pl.paulb.dndmanager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paulb.dndmanager.model.Resource;
import pl.paulb.dndmanager.service.ResourceService;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {


    private final ResourceService resourceService;

    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping
    public ResponseEntity<Resource> uploadResource(@RequestBody Resource resource) {
        Resource newResource = resourceService.uploadResource(resource);
        return ResponseEntity.status(201).body(newResource);
    }

    @GetMapping("/{campaignId}")
    public List<Resource> getResourcesForCampaign(@PathVariable Long campaignId) {
        return resourceService.getResourcesForCampaign(campaignId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResource(@PathVariable Long id) {
        boolean isDeleted = resourceService.deleteResource(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }
}
