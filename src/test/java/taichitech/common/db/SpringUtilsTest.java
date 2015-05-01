package taichitech.common.db;

import java.util.Collection;

import org.junit.After;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpringUtilsTest {
    @After
    public void destory() {
        SpringUtils.closeSpring();
    }

    @Test
    public void testFindByExample() throws Exception {
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.debug("test Book");
        ;
        TtEntityManager em = SpringUtils.getBean(TtEntityManager.class);
        Book exampleBook = new Book();
        exampleBook.setIsbn("886-7890-88-2");
        exampleBook.setAuthor("XXX");
        Collection<Book> bookResults = em.findByExample(exampleBook);
        if (bookResults.size() == 0) {
            exampleBook.setName("亂碼1/2");
            em.save(exampleBook);
        }
        logger.debug("test Shelf");
        ;
        Shelf exampleShelf = new Shelf();
        String shelfId = em.getAutoGenId();
        exampleShelf.setId(shelfId);
        Collection<Shelf> shelfResult = em.findByExample(exampleShelf);
        if (shelfResult.size() == 0) {
            exampleShelf.setName("中文書櫃[" + shelfId + "]");
            em.save(exampleShelf);

        }
    }

}
