package com.example.sprint2.service.impl;

import com.example.sprint2.model.SectionEntity;
import com.example.sprint2.model.VersionEntity;
import com.example.sprint2.repositories.SectionRepo;
import com.example.sprint2.repositories.VersionRepo;
import com.example.sprint2.service.VersionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VersionServiceImpl implements VersionService {
    private final VersionRepo repo;
    private final SectionRepo sectionRepo;
    //private final UserTransactionImp utx;
    //private final AtomikosDataSourceBean dataSource;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveChangesByAuthorizedUser(String newText, String username, SectionEntity section) throws Exception {
        VersionEntity entity = new VersionEntity();
        entity.setSectionId(section.getId());
        entity.setPersonedited(username);
        entity.setSectiontext(newText);
        entity.setStatus("Ожидает проверки");
        repo.save(entity);
        /*boolean rollback = false;
        try {
            utx.begin();
            Connection connection = dataSource.getConnection();
            VersionEntity entity = new VersionEntity();
            entity.setSectionId(section.getId());
            entity.setPersonedited(username);
            entity.setSectiontext(newText);
            entity.setStatus("Ожидает проверки");
            repo.save(entity);

            sectionRepo.updateText(newText, section.getId());

            connection.close();
        } catch (Exception e) {
            rollback = true;
            e.printStackTrace();
        } finally {
            if (!rollback) utx.commit();
            else utx.rollback();
        }
        **/
    }

    @Override
    public void saveChangesByUnauthorizedUser(String newText, SectionEntity section) {
        VersionEntity entity = new VersionEntity();
        entity.setSectionId(section.getId());
        //entity.setIpedited(ip);
        entity.setSectiontext(newText);
        entity.setStatus("Ожидает проверки");
        repo.save(entity);
    }

    @Override
    public List<VersionEntity> getListOfWaitingUpdates() {
        return repo.findAllByStatus("Ожидает проверки");
    }

    @Override
    public String getTextOfLastUpdateBySection(SectionEntity section) {
        List<VersionEntity> versionEntities = repo.findAllBySectionId(section.getId());
        return Collections.max(versionEntities).getSectiontext();
    }

    @Override
    public String getTextOfLastApprovedVersion(SectionEntity section) {
        List<VersionEntity> versionEntities = repo.findAllBySectionIdAndStatus(section.getId(), "Принято");
        String text = Collections.max(versionEntities).getSectiontext();
        sectionRepo.updateText(text, section.getId());
        return text;
    }

    @Override
    public void approve(Integer id) {
        VersionEntity entity = repo.findById(id).get();
        entity.setStatus("Принято");
        repo.save(entity);
    }

    @Override
    public void decline(Integer id) {
        VersionEntity entity = repo.findById(id).get();
        entity.setStatus("Отклонено");
        repo.save(entity);
    }

}
