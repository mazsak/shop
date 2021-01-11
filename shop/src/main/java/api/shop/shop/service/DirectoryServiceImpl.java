package api.shop.shop.service;

import api.shop.shop.model.Directory;
import api.shop.shop.repo.DirectoryRepo;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl extends BasicServiceImpl<Directory, DirectoryRepo, Long> implements DirectoryService {
    public DirectoryServiceImpl(DirectoryRepo directoryRepo) {
        super(directoryRepo);
    }
}


