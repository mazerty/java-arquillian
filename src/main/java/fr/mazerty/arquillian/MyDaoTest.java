package fr.mazerty.arquillian;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableRecord;

import javax.inject.Inject;

public abstract class MyDaoTest extends MyArquillianTest {

    @Inject
    private DSLContext dslContext;

    @SafeVarargs
    protected final void delete(Table<? extends Record>... tables) {
        dslContext.transaction(configuration -> {
            for (Table<? extends Record> table : tables) {
                dslContext.delete(table).execute();
            }
        });
    }

    @SuppressWarnings("unchecked") // because TableRecord<R extends TableRecord<R>> isn't generifiable in this case
    protected void insert(TableRecord... records) {
        dslContext.transaction(configuration -> {
            for (TableRecord record : records) {
                dslContext.executeInsert(record);
            }
        });
    }

}
