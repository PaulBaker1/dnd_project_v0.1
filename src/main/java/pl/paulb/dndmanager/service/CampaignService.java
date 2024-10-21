package pl.paulb.dndmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.paulb.dndmanager.model.Campaign;
import pl.paulb.dndmanager.model.SessionLog;
import pl.paulb.dndmanager.repository.CampaignRepository;
import pl.paulb.dndmanager.repository.SessionLogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignService {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private SessionLogRepository sessionLogRepository;

    public Campaign createCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    public Optional<Page<SessionLog>> getCampaignHistory(Long campaignId, Pageable pageable) {
        return Optional.of(sessionLogRepository.findByCampaignId(campaignId, pageable));
    }

    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

    public Optional<Campaign> getCampaignById(Long id) {
        return campaignRepository.findById(id);
    }

    public Optional<SessionLog> addSessionLog(Long campaignId, SessionLog log) {
        return campaignRepository.findById(campaignId).map(campaign -> {
            log.setCampaign(campaign);
            return sessionLogRepository.save(log);
        });
    }

}
