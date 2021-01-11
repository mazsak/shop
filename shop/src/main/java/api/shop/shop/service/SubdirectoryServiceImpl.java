package api.shop.shop.service;

import api.shop.shop.model.Subdirectory;
import api.shop.shop.repo.SubdirectoryRepo;
import org.springframework.stereotype.Service;

@Service
public class SubdirectoryServiceImpl extends BasicServiceImpl<Subdirectory, SubdirectoryRepo, Long> implements SubdirectoryService {
    public SubdirectoryServiceImpl(SubdirectoryRepo subdirectoryRepo) {
        super(subdirectoryRepo);
    }
}
