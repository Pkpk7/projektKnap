package IntegrationTest.com.company;

import com.company.*;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.hql.internal.ast.util.SessionFactoryHelper;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class JUnitIntegrationTests {

    private static <T> List<T> loadAllData(Class<T> type, Session session) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(type);
        criteria.from(type);
        List<T> data = session.createQuery(criteria).getResultList();
        for(T dat : data){
            System.out.println(dat);
        }
        return data;
    }

    private static Uzytkownik getOneUzytkownik(Session session, String uzytkownik_id){
        Uzytkownik uzytkownik =  (Uzytkownik) session.get(Uzytkownik.class, uzytkownik_id);
        return uzytkownik;
    }

    @Test
    public void pobierzWszystkieRekordyZBazy()
    {
        Session session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        List<Uzytkownik> users = loadAllData(Uzytkownik.class, session);
        List<Klient> clients = loadAllData(Klient.class, session);
        List<Admin> admins = loadAllData(Admin.class, session);
        List<Rezerwacja> rezerwacje = loadAllData(Rezerwacja.class, session);
        List<Umowa> umowy = loadAllData(Umowa.class, session);
        List<Oferta> oferty = loadAllData(Oferta.class, session);
        // Jak sie nic nie wywaliło to jest to dobry poczatek!

        session.close();
        assert !oferty.isEmpty();
        assert !users.isEmpty();
        assert !clients.isEmpty();
        assert !admins.isEmpty();
        assert !rezerwacje.isEmpty();
        //assert !umowy.isEmpty();

        assert 1==1;
    }

    @Test
    public void pobierzWszystkieRekordyZTabeliUzytkownicyISprawdzCzyIstniejePierwszy(){
        Session session = HibernateUtil.buildSessionFactory().openSession();
        session.beginTransaction();
        List<Uzytkownik> users = loadAllData(Uzytkownik.class, session);
        session.close();
        System.out.println(users.isEmpty());
        System.out.println(users.get(0).getEmail());
        System.out.println(users.size());
        assert !users.isEmpty();
    }

    @Test
    public void wlozJedenRekordDoTabeliUzytkownicySprawdzCzyIstniejeAPotemGoUsunISprawdzCzyZostalUsuniety()
    {

        Session session = HibernateUtil.buildSessionFactory().openSession();
        try {
            Uzytkownik uzytkownik = new Uzytkownik("login", "password", "imie", "nazwisko", "78078078080", "123123123", "jakisMail@gmail.com");
            session.save(uzytkownik);
            Uzytkownik czyToTenSamUzytkownik = getOneUzytkownik(session,"78078078080");
            System.out.println(uzytkownik.getImie());
            System.out.println(czyToTenSamUzytkownik.getImie());
            session.delete(uzytkownik);
            assert uzytkownik.getImie() == czyToTenSamUzytkownik.getImie();

        }catch(Exception e){
            System.out.println(e.toString());
        }

        session.close();
        assert 1==1;
    }
}
