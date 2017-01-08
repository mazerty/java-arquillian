package fr.mazerty.shika.n;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.TableRecord;

import javax.inject.Inject;

public abstract class MyDaoTest extends MyArquillianTest {

    @Inject
    private DSLContext dslContext;

    @Deployment
    public static WebArchive deployment() {
        return defaultDeployment();
    }

    protected void delete(Table... tables) {
        dslContext.transaction(configuration -> {
            for (Table table : tables) {
                dslContext.delete(table).execute();
            }
        });
    }

    protected void insert(TableRecord... records) {
        dslContext.transaction(configuration -> {
            for (TableRecord record : records) {
                dslContext.executeInsert(record);
            }
        });
    }

}
