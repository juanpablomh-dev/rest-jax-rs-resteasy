package org.jpmh.utils;

import org.jpmh.dao.PersonDAO;
import org.jpmh.model.InitEnum;
import org.jpmh.utils.mocks.MockPerson;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class Init {
    public static final InitEnum LOAD = InitEnum.ALL;

    public Init() {
    }

    @PostConstruct
    public void loadDataApp() {
        loadPerson();
    }

    private void loadPerson(){
        if (LOAD.equals(InitEnum.ALL) || LOAD.equals(InitEnum.PERSONS)) {
            PersonDAO personDAO = PersonDAO.getInstance();
            for (int i = 0; i < PersonDAO.CONFIG_HEAP; i++) {
                personDAO.addPerson(MockPerson.generateMockPerson());
            }
        }
    }

}
