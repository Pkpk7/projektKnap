package IntegrationTest.com.company;

import com.company.*;
import org.hibernate.Session;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JUnitIntegrationTests {
    public List<Uzytkownik> getLabels() {
        Session session =
                SessionFactoryHelper.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        // We read labels record from database using a simple Hibernate
        // query, the Hibernate Query Language (HQL).
        List<Uzytkownik> labels = session.createQuery("from Label", Label.class)
                .list();
        session.getTransaction().commit();

        return labels;
    }

    @Test
    public void wszystkie(){

        List<Uzytkownik> users = session.createQuery("SELECT a FROM Student a", Student.class).getResultList();
    }
}
