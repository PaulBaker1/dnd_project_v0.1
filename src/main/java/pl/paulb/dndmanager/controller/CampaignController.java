package pl.paulb.dndmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.paulb.dndmanager.model.Campaign;
import pl.paulb.dndmanager.model.SessionLog;
import pl.paulb.dndmanager.service.CampaignService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {

    @Autowired
    private CampaignService campaignService;

    @PostMapping
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        Campaign newCampaign = campaignService.createCampaign(campaign);
        return ResponseEntity.status(201).body(newCampaign);
    }

    @GetMapping
    public List<Campaign> getAllCampaigns() {
        return campaignService.getAllCampaigns();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Campaign> getCampaignById(@PathVariable Long id) {
        Optional<Campaign> campaign = campaignService.getCampaignById(id);
        return campaign.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());
    }

    // CampaignController.java

    @PostMapping("/{id}/log")
    public ResponseEntity<SessionLog> addSessionLog(@PathVariable Long id, @RequestBody SessionLog log) {
        Optional<SessionLog> sessionLog = campaignService.addSessionLog(id, log);
        return sessionLog.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).build());  // Return 404 if the campaign ID is not found
    }


    // CampaignController.java

    @GetMapping("/{id}/history")
    public ResponseEntity<Page<SessionLog>> getCampaignHistory(@PathVariable Long id, Pageable pageable) {
        return campaignService.getCampaignHistory(id, pageable)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());  // Return 404 if no history is found
    }


}
