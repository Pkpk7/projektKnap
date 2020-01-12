package com.company;

import IntegrationTest.com.company.HibernateUtil;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.w3c.dom.Text;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class frontEnd extends Application {


    private static void wygenerujPDFAzUmowa(Rezerwacja r)
    {
        Document document = new Document();

        Session session = HibernateUtil.buildSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Umowa umowa  = new Umowa(r.getOfertaKtoraJestZarezerwowana(), 0,"\n\nzawarta w ………………………… (miejsce zawarcia umowy) w dniu  ………………… (data zawarcia umowy)  r. pomiędzy:\n" +
                "\n" +
                "……………………………….. (imię i nazwisko sprzedającego), synem/ córką ……………………………… (imiona rodziców), ……………………… (stan cywilny), zamieszkałym/ą w ………………………………. (miejscowość) przy ulicy ………………………………. (ulica, numer domu, numer mieszkania), dowód seria ………… (seria) numer ……………………. (numer dowodu osobistego), zwanym dalej Sprzedającym,\n" +
                "\n" +
                "a\n" +
                "\n" +r.getKlientKtoryRezerwuje().getImie()+" "+r.getKlientKtoryRezerwuje().getNazwisko()+
                ", synem/córką …………………………(imiona rodziców), ……………………… (stan cywilny), zamieszkałym/ą w ………………………………(miejscowość)  przy ulicy ………………………………. (ulica, numer domu, numer mieszkania), dowód seria …………………. (seria) numer ………………….(numer dowodu osobistego), zwanym dalej Kupującym.\n" +
                "\n" +
                "\n" +
                "§ 1\n" +
                "\n" +
                "Sprzedający oświadcza, że:\n" +
                "\n" +
                "1)      jest właścicielem lokalu położonego w "+r.getOfertaKtoraJestZarezerwowana().getAdres()+", składającego się z ……………….. (liczba pokoi) pokoi, kuchni, łazienki z wc oraz przedpokoju o pow. ………………………….. (powierzchnia mieszkania w metrach kwadratowych)  dla którego prowadzona jest księga wieczysta …………………………………… (numer księgi wieczystej),\n" +
                "\n" +
                "2)      mieszkanie to objęte nie jest małżeńską wspólnością majątkową,\n" +
                "\n" +
                "3)      mieszkanie to nie jest obciążone i nie znajduje się w posiadaniu zależnym osób trzecich.\n" +
                "\n" +
                "§ 2\n" +
                "\n" +
                "Sprzedający oświadcza, że zobowiązuje się sprzedać Kupującemu opisane wyżej mieszkanie wraz z przypadającym na nie udziałem w nieruchomości wspólnej za cenę w kwocie "+r.getOfertaKtoraJestZarezerwowana().getCena()+" (słownie: ………………………..) zł w terminie do ………………………. (data dzienna)r., a Kupujący oświadcza, że za podaną cenę mieszkanie te wraz z przypadającym na nie udziałem w nieruchomości wspólnej zobowiązuje się w ustalonym terminie kupić.\n" +
                "\n" +
                "§ 3\n" +
                "\n" +
                "Cenę sprzedaży Kupujący zapłaci sprzedającemu w terminie do …………………. (data dzienna) dni od podpisania aktu notarialnego umowy sprzedaży z udzielonego mu kredytu bankowego bezpośrednio na rachunek bankowy Sprzedającego." , r.getKlientKtoryRezerwuje());
        session.save(umowa);
        tx.commit();
        session.close();

        try
        {
            System.out.println(System.getProperty("user.dir"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("src/main/java/com/company/umowyPDF/"+r.getKlientKtoryRezerwuje().getImie()+"_"+r.getKlientKtoryRezerwuje().getNazwisko()+"_"+r.getOfertaKtoraJestZarezerwowana().getIdOferty()+".pdf"));
            document.open();

            BaseFont helvetica = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1257, BaseFont.EMBEDDED);
            Font helvetica16=new Font(helvetica,16);
            Font helvetica8 = new Font(helvetica, 8);

            Paragraph naglowek = new Paragraph("PRZEDWSTĘPNA UMOWA SPRZEDAŻY MIESZKANIA", helvetica16);
            //naglowek.setFont(helvetica16);

            Paragraph dnia = new Paragraph("\n\nzawarta w ………………………… (miejsce zawarcia umowy) w dniu  ………………… (data zawarcia umowy)  r. pomiędzy:\n" +
                    "\n" +
                    "……………………………….. (imię i nazwisko sprzedającego), synem/ córką ……………………………… (imiona rodziców), ……………………… (stan cywilny), zamieszkałym/ą w ………………………………. (miejscowość) przy ulicy ………………………………. (ulica, numer domu, numer mieszkania), dowód seria ………… (seria) numer ……………………. (numer dowodu osobistego), zwanym dalej Sprzedającym,\n" +
                    "\n" +
                    "a\n" +
                    "\n" +r.getKlientKtoryRezerwuje().getImie()+" "+r.getKlientKtoryRezerwuje().getNazwisko()+
                    ", synem/córką …………………………(imiona rodziców), ……………………… (stan cywilny), zamieszkałym/ą w ………………………………(miejscowość)  przy ulicy ………………………………. (ulica, numer domu, numer mieszkania), dowód seria …………………. (seria) numer ………………….(numer dowodu osobistego), zwanym dalej Kupującym.\n" +
                    "\n" +
                    "\n" +
                    "§ 1\n" +
                    "\n" +
                    "Sprzedający oświadcza, że:\n" +
                    "\n" +
                    "1)      jest właścicielem lokalu położonego w "+r.getOfertaKtoraJestZarezerwowana().getAdres()+", składającego się z ……………….. (liczba pokoi) pokoi, kuchni, łazienki z wc oraz przedpokoju o pow. ………………………….. (powierzchnia mieszkania w metrach kwadratowych)  dla którego prowadzona jest księga wieczysta …………………………………… (numer księgi wieczystej),\n" +
                    "\n" +
                    "2)      mieszkanie to objęte nie jest małżeńską wspólnością majątkową,\n" +
                    "\n" +
                    "3)      mieszkanie to nie jest obciążone i nie znajduje się w posiadaniu zależnym osób trzecich.\n" +
                    "\n" +
                    "§ 2\n" +
                    "\n" +
                    "Sprzedający oświadcza, że zobowiązuje się sprzedać Kupującemu opisane wyżej mieszkanie wraz z przypadającym na nie udziałem w nieruchomości wspólnej za cenę w kwocie "+r.getOfertaKtoraJestZarezerwowana().getCena()+" (słownie: ………………………..) zł w terminie do ………………………. (data dzienna)r., a Kupujący oświadcza, że za podaną cenę mieszkanie te wraz z przypadającym na nie udziałem w nieruchomości wspólnej zobowiązuje się w ustalonym terminie kupić.\n" +
                    "\n" +
                    "§ 3\n" +
                    "\n" +
                    "Cenę sprzedaży Kupujący zapłaci sprzedającemu w terminie do …………………. (data dzienna) dni od podpisania aktu notarialnego umowy sprzedaży z udzielonego mu kredytu bankowego bezpośrednio na rachunek bankowy Sprzedającego.", helvetica8);
            //dnia.setFont(helvetica8);




            naglowek.setAlignment(Element.ALIGN_CENTER);
            document.add(naglowek);
            document.add(dnia);


            document.close();
            writer.close();
        } catch (DocumentException e)
        {
            e.printStackTrace();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Klient loggedInKlient = new Klient();
    Uzytkownik loggedInUzytkownik = new Uzytkownik();
    Admin loggedInAdmin = new Admin();


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

    public static void main(String[] args) {
        launch(args);
    }
    private double xOffset = 0;
    private double yOffset = 0;
    private boolean shouldIAddUser = true;

    public void checkIfInputIsRight(TextField textField){
        if(textField.getText().equals("")){
            textField.setStyle("-jfx-unfocus-color: RED");
            shouldIAddUser = false;
        }else{
            textField.setStyle("");
        }
    }

    public void addMovementOnDrag(Parent root, Stage primaryStage){
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

        @Override
        public void start(Stage primaryStage) throws IOException {
            UzytkownicySingleton uzy = UzytkownicySingleton.getUzytkownicySingletonInstance();

            Session session = HibernateUtil.buildSessionFactory().openSession();
            session.beginTransaction();
            UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow().addAll((ArrayList<Uzytkownik>) loadAllData(Uzytkownik.class, session));
            KlienciSingleton.getKlienciSingletonInstance().getListaKlientow().addAll(loadAllData(Klient.class, session));
            AdminiSingleton.getAdminiSingletonInstance().getListaAdminow().addAll(loadAllData(Admin.class, session));
            RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().addAll(loadAllData(Rezerwacja.class, session));
            UmowySingleton.getUmowySingletonInstance().getListaUmow().addAll(loadAllData(Umowa.class, session));
            OfertySingleton.getOfertySingletonInstance().getListaOfert().addAll(loadAllData(Oferta.class, session));
            session.close();

            Uzytkownik jakis = UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow().get(0);
            System.out.println(jakis.getLogin()+"   TO JEST MOJ LOGIN");
            for(Klient k : KlienciSingleton.getKlienciSingletonInstance().getListaKlientow()){
                System.out.println(k.getLogin()+"    TO JEST JEGO LOGIN");
            }






        Object o = new Object();
        //System.out.println(getClass());
        StackPane pseudoRoot = new StackPane();
        Scene scene = new Scene(pseudoRoot,300,250);

            System.out.println("PRZED");
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/front.fxml"));

            System.out.println("PO");
            scene = new Scene(root);
            addMovementOnDrag(root, primaryStage);
           primaryStage.initStyle(StageStyle.UNDECORATED);

            primaryStage.setScene(scene);
            primaryStage.show();

            Button button = (Button) scene.lookup("#zamknij");
            button.setOnAction((event) -> {
                primaryStage.close();
            });

            Button buttonZarejestrujSie = (Button) scene.lookup("#zarejestrujSie");

            Parent register = FXMLLoader.load(getClass().getResource("/fxml/register.fxml"));
            addMovementOnDrag(register, primaryStage);
            buttonZarejestrujSie.setOnAction((event) -> {
                    primaryStage.getScene().setRoot(register);

            });

            Button buttonZamknijRegister = (Button) register.lookup("#zamknij2");
            buttonZamknijRegister.setOnAction((event) -> {
                primaryStage.close();
            });

            Button cofnijRegister = (Button) register.lookup("#cofnij");
            cofnijRegister.setOnAction((event) -> {
                primaryStage.getScene().setRoot(root);
                Label label = (Label) register.lookup("#userAdded");
                label.setVisible(false);
            });

            Button registerButton = (Button) register.lookup("#registerButton");
            registerButton.setOnAction((event) -> {
                shouldIAddUser = true;

                TextField textField = (TextField) register.lookup("#yourName");
                checkIfInputIsRight(textField);
                String name = textField.getText();

                textField = (TextField) register.lookup("#yourSurname");
                checkIfInputIsRight(textField);
                String surname = textField.getText();

                textField = (TextField) register.lookup("#yourLogin");
                checkIfInputIsRight(textField);
                for(Klient k : KlienciSingleton.getKlienciSingletonInstance().getListaKlientow()){
                    if(k.getLogin().equals(textField.getText())){
                        textField.setStyle("-jfx-unfocus-color: RED");
                        shouldIAddUser = false;
                        break;
                    }
                }
                String login = textField.getText();

                textField = (TextField) register.lookup("#yourPassword");
                checkIfInputIsRight(textField);
                String pierwszeHaslo = textField.getText();

                String password = textField.getText();

                textField = (TextField) register.lookup("#repeatYourPassword");
                checkIfInputIsRight(textField);
                if(!pierwszeHaslo.equals(textField.getText())){
                    textField.setStyle("-jfx-unfocus-color: RED");
                    textField = (TextField) register.lookup("#yourPassword");
                    textField.setStyle("-jfx-unfocus-color: RED");
                    shouldIAddUser = false;
                }

                textField = (TextField) register.lookup("#pesel");
                checkIfInputIsRight(textField);
                if(!java.util.regex.Pattern.matches("[0-9]{11}", textField.getText())){
                    textField.setStyle("-jfx-unfocus-color: RED");
                    shouldIAddUser = false;
                }
                for(Klient k : KlienciSingleton.getKlienciSingletonInstance().getListaKlientow()){
                    if(k.getPesel().equals(textField.getText())){
                        textField.setStyle("-jfx-unfocus-color: RED");
                        shouldIAddUser = false;
                    }
                }
                String pesel = textField.getText();

                textField = (TextField) register.lookup("#numerTelefonu");
                checkIfInputIsRight(textField);
                if(!java.util.regex.Pattern.matches("[0-9]{9}", textField.getText())){
                    textField.setStyle("-jfx-unfocus-color: RED");
                    shouldIAddUser = false;
                }

                String numerTelefonu = textField.getText();

                textField = (TextField) register.lookup("#email");
                checkIfInputIsRight(textField);
                if(!java.util.regex.Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", textField.getText())){
                    textField.setStyle("-jfx-unfocus-color: RED");
                    shouldIAddUser = false;
                }
                String email = textField.getText();
                if(shouldIAddUser){
                    Label label = (Label) register.lookup("#userAdded");
                    label.setVisible(true);
                    Transaction transaction = null;
                    Session sessionSave = HibernateUtil.buildSessionFactory().openSession();
                    Klient klient = null;

                    try {
                        transaction = sessionSave.beginTransaction();
                        klient = new Klient(login, password, name, surname, pesel, numerTelefonu, email);
                        sessionSave.save(klient);
                        transaction.commit();
                        KlienciSingleton.getKlienciSingletonInstance().getListaKlientow().add(klient);
                    } catch (Exception e) {
                        e.printStackTrace();
                        transaction.rollback();
                    }
                    sessionSave.close();
                }
            });


            Parent userLogged = FXMLLoader.load(getClass().getResource("/fxml/userLogged.fxml"));
            Parent adminLogged = FXMLLoader.load(getClass().getResource("/fxml/adminLogged.fxml"));
            Parent userLoggedFirm = FXMLLoader.load(getClass().getResource("/fxml/uzytkownikLogged.fxml"));

            addMovementOnDrag(userLogged, primaryStage);
            addMovementOnDrag(userLoggedFirm, primaryStage);



            Button signin = (Button) root.lookup("#signin");
            signin.setOnAction((event) -> {
                TextField login = (TextField) root.lookup("#login");
                PasswordField passwordField = (PasswordField) root.lookup("#password");
                login.setStyle("-jfx-unfocus-color: RED");
                passwordField.setStyle("-jfx-unfocus-color: RED");

                for(Klient k : KlienciSingleton.getKlienciSingletonInstance().getListaKlientow()) {
                    if(k.getLogin().equals(login.getText()) && k.getPassword().equals(passwordField.getText())) {
                        Label witaj = (Label) userLogged.lookup("#witaj");
                        witaj.setText("Witaj "+k.getImie());



                        primaryStage.getScene().setRoot(userLogged);
                        AnchorPane anchorPane = (AnchorPane) userLogged.lookup("#glowny");
                        primaryStage.setWidth(anchorPane.getPrefWidth());
                        primaryStage.setHeight(anchorPane.getPrefHeight());
                        loggedInKlient = k;
                        login.setStyle("");
                        passwordField.setStyle("");

                        ListView<String> listaOfert = (ListView) userLogged.lookup("#oferty");
                        ListView<String> listaRezerwacji = (ListView) userLogged.lookup("#rezerwacje");

                        listaOfert.getItems().clear();
                        listaRezerwacji.getItems().clear();

                        //listaOfert.getItems().addAll(OfertySingleton.getOfertySingletonInstance().getListaOfert());
                        for(Oferta oferta : OfertySingleton.getOfertySingletonInstance().getListaOfert())
                        {
                            listaOfert.getItems().add(oferta.getZawartoscOferty());
                        }


                        for(Rezerwacja r : RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                        {
                            if(r.getKlientKtoryRezerwuje().getPesel().equals(loggedInKlient.getPesel())) {
                                listaRezerwacji.getItems().add(r.getKlientKtoryRezerwuje().getImie()+" "+r.getKlientKtoryRezerwuje().getNazwisko()+" "+r.getOfertaKtoraJestZarezerwowana().getAdres());
                                listaOfert.getItems().remove(r.getOfertaKtoraJestZarezerwowana().getZawartoscOferty());
                            }
                        }
                        //listaRezerwacji.getItems().addAll(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji());

                        listaRezerwacji.setPrefWidth(200);
                        listaRezerwacji.setPrefHeight(350);

                        listaOfert.setPrefWidth(200);
                        listaOfert.setPrefHeight(350);




                    }
                }
                // USTAWIC DLA UZYTKOWNIKA I ADMINA A NIE TYLKO DLA KLIENTA NA POTEM
                for(Admin admin : AdminiSingleton.getAdminiSingletonInstance().getListaAdminow()){
                    if(admin.getLogin().equals(login.getText()) && admin.getPassword().equals(passwordField.getText())){
                        Label witaj = (Label) adminLogged.lookup("#witaj");
                        witaj.setText("Witaj "+admin.getImie());

                        primaryStage.getScene().setRoot(adminLogged);
                        AnchorPane anchorPane = (AnchorPane) adminLogged.lookup("#glowny");
                        primaryStage.setWidth(anchorPane.getPrefWidth());
                        primaryStage.setHeight(anchorPane.getPrefHeight());
                        loggedInAdmin = admin;

                        login.setStyle("");
                        passwordField.setStyle("");

                        ListView<String> listaOfert = (ListView) adminLogged.lookup("#oferty");
                        ListView<String> listaRezerwacji = (ListView) adminLogged.lookup("#rezerwacje");

                        listaOfert.getItems().clear();
                        listaRezerwacji.getItems().clear();


                        for(Oferta oferta : OfertySingleton.getOfertySingletonInstance().getListaOfert())
                        {
                            listaOfert.getItems().add(oferta.getZawartoscOferty());
                        }


                        for(Rezerwacja r: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                        {
                            listaRezerwacji.getItems().add(r.getKlientKtoryRezerwuje().getImie()+" "+r.getKlientKtoryRezerwuje().getNazwisko()+" "+r.getOfertaKtoraJestZarezerwowana().getAdres());
                        }
                        //listaRezerwacji.getItems().addAll(RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji());

                        listaRezerwacji.setPrefWidth(200);
                        listaRezerwacji.setPrefHeight(350);

                        listaOfert.setPrefWidth(200);
                        listaOfert.setPrefHeight(350);

                    }
                    for(Uzytkownik uzytkownik: UzytkownicySingleton.getUzytkownicySingletonInstance().getListaUzytkownikow())
                    {
                        if(uzytkownik.getLogin().equals(login.getText()) && uzytkownik.getPassword().equals(passwordField.getText())){
                            Label witaj = (Label) userLoggedFirm.lookup("#witaj");
                            witaj.setText("Witaj "+uzytkownik.getImie());
                            primaryStage.getScene().setRoot(userLoggedFirm);
                            AnchorPane anchorPane = (AnchorPane) userLoggedFirm.lookup("#glowny");
                            primaryStage.setWidth(anchorPane.getPrefWidth());
                            primaryStage.setHeight(anchorPane.getPrefHeight());
                            loggedInUzytkownik = uzytkownik;

                            login.setStyle("");
                            passwordField.setStyle("");


                            ListView<String> listaRezerwacji = (ListView) userLoggedFirm.lookup("#rezerwacje");

                            listaRezerwacji.getItems().clear();

                            for(Rezerwacja r: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                            {
                                listaRezerwacji.getItems().add(r.getKlientKtoryRezerwuje().getImie()+" "+r.getKlientKtoryRezerwuje().getNazwisko()+" "+r.getOfertaKtoraJestZarezerwowana().getAdres());
                            }

                            listaRezerwacji.setPrefWidth(200);
                            listaRezerwacji.setPrefHeight(350);
                        }
                    }

                }


            });
            //////////////////////////////////////// //////////////////////////////////////// //////////////////////////////////////// //////////////////////////////////////// ////////////////////////////////////////

            Button logOut = (Button) userLogged.lookup("#logOutUser");
            logOut.setOnAction((event) -> {
                primaryStage.getScene().setRoot(root);
                AnchorPane anchorPane = (AnchorPane) root.lookup("#glowny");
                primaryStage.setWidth(anchorPane.getPrefWidth());
                primaryStage.setHeight(anchorPane.getPrefHeight());
            });

            Button buttonZamknijUser = (Button) userLogged.lookup("#zamknij3");
            buttonZamknijUser.setOnAction((event) -> {
                primaryStage.close();
            });
            ListView<String> listaOfert = (ListView) userLogged.lookup("#oferty");

            ListView<String> listaRezerwacji = (ListView) userLogged.lookup("#rezerwacje");


            Parent oferta = FXMLLoader.load(getClass().getResource("/fxml/przegladajOferte.fxml"));
            addMovementOnDrag(oferta, primaryStage);



            Button usun = (Button) userLogged.lookup("#usun");
            usun.setOnAction((event) -> {
                if(!RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().isEmpty())
                {
                Rezerwacja rezerwacjaDoUsuniecia = new Rezerwacja();
                for(Rezerwacja r: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                {
                    if(r.getOfertaKtoraJestZarezerwowana().getZawartoscOferty().equals(listaRezerwacji.getSelectionModel().getSelectedItem())) {
                        Session sessionDelete = HibernateUtil.buildSessionFactory().openSession();
                        Transaction tx = sessionDelete.beginTransaction();
                        sessionDelete.delete(r);
                        tx.commit();
                        sessionDelete.close();
                        System.out.println("WYKONAŁEM SIĘ");
                        rezerwacjaDoUsuniecia = r;
                    }
                }
                RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().remove(rezerwacjaDoUsuniecia);
                listaOfert.getItems().add(listaRezerwacji.getSelectionModel().getSelectedItem());
                listaRezerwacji.getItems().remove(listaRezerwacji.getSelectionModel().getSelectedItem());
            }});
            //////////////////////////////////////// //////////////////////////////////////// //////////////////////////////////////// ////////////////////////////////////////
            listaOfert.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent click) {

                    if (click.getClickCount() == 2) {
                        Label witaj = (Label) oferta.lookup("#witaj");
                        witaj.setText("Witaj "+loggedInKlient.getImie());
                        //Use ListView's getSelected Item
                        String currentItemSelected = listaOfert.getSelectionModel()
                                .getSelectedItem();
                        //use this to do whatever you want to. Open Link etc.
                        primaryStage.getScene().setRoot(oferta);
                        TextArea opis = (TextArea) oferta.lookup("#opis");
                        TextArea cena = (TextArea) oferta.lookup("#cena");
                        TextArea adres = (TextArea) oferta.lookup("#adres");
                        Button zamknij = (Button) oferta.lookup("#zamknij");
                        Button cofnij = (Button) oferta.lookup("#cofnij");
                        Button logout = (Button) oferta.lookup("#logOutUser");
                        Button zarezerwuj = (Button) oferta.lookup("#zarezerwuj");

                        zarezerwuj.setOnAction((event) -> {

                            listaRezerwacji.getItems().add(currentItemSelected);
                            listaOfert.getItems().remove(currentItemSelected);
                            Session sessionAddRezerwacja = HibernateUtil.buildSessionFactory().openSession();
                            Transaction tx = sessionAddRezerwacja.beginTransaction();
                            Oferta oferta = new Oferta();
                            for(Oferta o : OfertySingleton.getOfertySingletonInstance().getListaOfert())
                            {
                                if(o.getZawartoscOferty().equals(currentItemSelected))
                                    oferta = o;
                            }
                            Rezerwacja rezerwacja = new Rezerwacja(oferta, loggedInKlient, 0);
                            sessionAddRezerwacja.save(rezerwacja);
                            tx.commit();
                            RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().add(rezerwacja);
                            sessionAddRezerwacja.close();

                            primaryStage.getScene().setRoot(userLogged);
                        });

                        zamknij.setOnAction((event) -> {
                            primaryStage.close();
                            });

                        cofnij.setOnAction((event) -> {
                            primaryStage.getScene().setRoot(userLogged);
                            });

                        logout.setOnAction((event) -> {
                            primaryStage.getScene().setRoot(root);
                            AnchorPane anchorPane = (AnchorPane) root.lookup("#glowny");
                            primaryStage.setWidth(anchorPane.getPrefWidth());
                            primaryStage.setHeight(anchorPane.getPrefHeight());
                        });


                        //loggedInKlient

                        for(Oferta o : OfertySingleton.getOfertySingletonInstance().getListaOfert())
                        {
                            if(o.getZawartoscOferty().equals(currentItemSelected)) {
                                cena.setText(Integer.toString(o.getCena()));
                                opis.setText(o.getZawartoscOferty());
                                adres.setText(o.getAdres());
                            }
                        }
                        //cena.setText(OfertySingleton.getOfertySingletonInstance().getListaOfert().f);


                    }
                }});


            //////////////////////////////////////// //////////////////////////////////////// ////////////////////////////////////////


            Button logOutAdmin = (Button) adminLogged.lookup("#logOutAdmin");
            logOutAdmin.setOnAction((event) -> {
                primaryStage.getScene().setRoot(root);
                AnchorPane anchorPane = (AnchorPane) root.lookup("#glowny");
                primaryStage.setWidth(anchorPane.getPrefWidth());
                primaryStage.setHeight(anchorPane.getPrefHeight());
            });

            Button buttonZamknijAdmin = (Button) adminLogged.lookup("#zamknij4");
            buttonZamknijAdmin.setOnAction((event) -> {
                primaryStage.close();
            });

            addMovementOnDrag(adminLogged,primaryStage);

            ListView<String> listaOfertAdmin = (ListView) adminLogged.lookup("#oferty");

            ListView<String> listaRezerwacjiAdmin = (ListView) adminLogged.lookup("#rezerwacje");

            Button usunOferte = (Button) adminLogged.lookup("#usunOferte");
            usunOferte.setOnAction((event) -> {

            Oferta oferataDoUsuniecia = new Oferta();

                for(Oferta oferta1: OfertySingleton.getOfertySingletonInstance().getListaOfert())
                {
                    if(oferta1.getZawartoscOferty().equals(listaOfertAdmin.getSelectionModel().getSelectedItem()))
                    {
                        Session sessionUsunOferte = HibernateUtil.buildSessionFactory().openSession();
                        Transaction tx = sessionUsunOferte.beginTransaction();
                        sessionUsunOferte.delete(oferta1);
                        tx.commit();
                        sessionUsunOferte.close();
                        oferataDoUsuniecia = oferta1;
                        listaOfertAdmin.getItems().remove(oferta1.getZawartoscOferty());
                    }
                }
                OfertySingleton.getOfertySingletonInstance().getListaOfert().remove(oferataDoUsuniecia);
                Rezerwacja rezerwacjaDoUsuniecia = new Rezerwacja();
                for(Rezerwacja rezerwacja: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                {
                    if(rezerwacja.getOfertaKtoraJestZarezerwowana().getZawartoscOferty().equals(oferataDoUsuniecia.getZawartoscOferty()))
                        rezerwacjaDoUsuniecia = rezerwacja;
                }
                RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji().remove(rezerwacjaDoUsuniecia);
                listaRezerwacjiAdmin.getItems().remove(rezerwacjaDoUsuniecia.getKlientKtoryRezerwuje().getImie()+" "+rezerwacjaDoUsuniecia.getKlientKtoryRezerwuje().getNazwisko()+" "+rezerwacjaDoUsuniecia.getOfertaKtoraJestZarezerwowana().getAdres());
            });


            Parent dodajOferte = FXMLLoader.load(getClass().getResource("/fxml/dodajOferte.fxml"));

            Button backAdmin = (Button) dodajOferte.lookup("#backAdmin");
            backAdmin.setOnAction((event) -> {
                primaryStage.getScene().setRoot(adminLogged);
            });



            Button logOutAdminAddOferta = (Button) dodajOferte.lookup("#logOutAdmin");
            logOutAdminAddOferta.setOnAction((event) -> {
                primaryStage.getScene().setRoot(root);
                AnchorPane anchorPane = (AnchorPane) root.lookup("#glowny");
                primaryStage.setWidth(anchorPane.getPrefWidth());
                primaryStage.setHeight(anchorPane.getPrefHeight());
            });

            Button buttonZamknijAdminAddUser = (Button) dodajOferte.lookup("#exitAdmin");
            buttonZamknijAdminAddUser.setOnAction((event) -> {
                primaryStage.close();
            });

            Button dodanieOferty = (Button) adminLogged.lookup("#dodajOferte");
            dodanieOferty.setOnAction((event) -> {
                Label witaj = (Label) dodajOferte.lookup("#witaj");
                witaj.setText("Witaj "+loggedInAdmin.getImie());
                primaryStage.getScene().setRoot(dodajOferte);
            });

            addMovementOnDrag(dodajOferte,primaryStage);

            TextArea adres = (TextArea) dodajOferte.lookup("#adresOferta");
            TextArea opis = (TextArea) dodajOferte.lookup("#opisOferta");
            TextArea cena = (TextArea) dodajOferte.lookup("#cenaOferta");

            cena.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue,
                                    String newValue) {
                    if (!newValue.matches("\\d*")) {
                        cena.setText(newValue.replaceAll("[^\\d]", ""));
                    }
                }
            });


            Button addOferta = (Button) dodajOferte.lookup("#addOferta");
            addOferta.setOnAction((event) -> {
                if(!opis.getText().isEmpty() && !adres.getText().isEmpty() && !cena.getText().isEmpty()){
                int cenaInt = Integer.parseInt(cena.getText());

                Oferta ofertaDoDodania = new Oferta(0, opis.getText(), adres.getText(), cenaInt);
                Session sessionAddOferta = HibernateUtil.buildSessionFactory().openSession();
                Transaction tx = sessionAddOferta.beginTransaction();
                sessionAddOferta.save(ofertaDoDodania);
                tx.commit();
                sessionAddOferta.close();

                OfertySingleton.getOfertySingletonInstance().getListaOfert().add(ofertaDoDodania);
                listaOfertAdmin.getItems().add(ofertaDoDodania.getZawartoscOferty());
                primaryStage.getScene().setRoot(adminLogged);


            }else{
                    if(opis.getText().isEmpty()){opis.setStyle("-jfx-unfocus-color: RED");}
                    else opis.setStyle("");
                    if(adres.getText().isEmpty()){adres.setStyle("-jfx-unfocus-color: RED");}
                    else adres.setStyle("");
                    if(cena.getText().isEmpty()){cena.setStyle("-jfx-unfocus-color: RED");}
                    else cena.setStyle("");
                }});

            //Parent ofertaDodaj = FXMLLoader.load(getClass().getResource("/fxml/przegladajOferte.fxml"));
            //addMovementOnDrag(ofertaDodaj, primaryStage);
            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            ListView<String> rezerwacjeUzytkownik = (ListView<String>) userLoggedFirm.lookup("#rezerwacje");

            Button buttonZamknijUzytkownik = (Button) userLoggedFirm.lookup("#exit");
            buttonZamknijUzytkownik.setOnAction((event) -> {
                primaryStage.close();
            });

            Button logOutUzytkownik = (Button) userLoggedFirm.lookup("#logout");
            logOutUzytkownik.setOnAction((event) -> {
                primaryStage.getScene().setRoot(root);
                AnchorPane anchorPane = (AnchorPane) root.lookup("#glowny");
                primaryStage.setWidth(anchorPane.getPrefWidth());
                primaryStage.setHeight(anchorPane.getPrefHeight());
            });

            Button wygenerujUmowe = (Button) userLoggedFirm.lookup("#wygenerujUmowe");
            wygenerujUmowe.setOnAction((event) -> {
                if(!rezerwacjeUzytkownik.getSelectionModel().getSelectedItem().isEmpty())
                {
                Rezerwacja r = new Rezerwacja();
                for(Rezerwacja rezerwacja: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                {
                    if((rezerwacja.getKlientKtoryRezerwuje().getImie()+" "+rezerwacja.getKlientKtoryRezerwuje().getNazwisko()+" "+rezerwacja.getOfertaKtoraJestZarezerwowana().getAdres()).equals(rezerwacjeUzytkownik.getSelectionModel().getSelectedItem()))
                        r = rezerwacja;
                }
                wygenerujPDFAzUmowa(r);
            }});

            Button wygenerujUmoweAdmin = (Button) adminLogged.lookup("#wygenerujUmoweAdmin");
            wygenerujUmoweAdmin.setOnAction((event) -> {
                if(!listaRezerwacjiAdmin.getSelectionModel().getSelectedItem().isEmpty()){
                Rezerwacja r = new Rezerwacja();
                for(Rezerwacja rezerwacja: RezerwacjeSingleton.getRezerwacjaSingletonInstance().getListaRezerwacji())
                {
                    if((rezerwacja.getKlientKtoryRezerwuje().getImie()+" "+rezerwacja.getKlientKtoryRezerwuje().getNazwisko()+" "+rezerwacja.getOfertaKtoraJestZarezerwowana().getAdres()).equals(listaRezerwacjiAdmin.getSelectionModel().getSelectedItem()))
                        r = rezerwacja;
                }
                wygenerujPDFAzUmowa(r);
            }});

        }
    }

