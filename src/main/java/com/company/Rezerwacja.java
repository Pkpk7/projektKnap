package com.company;

import javax.persistence.*;

@Entity
@Table(name="rezerwacja")
    public class Rezerwacja {
        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="idOferty")
        private Oferta ofertaKtoraJestZarezerwowana;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name="idKlienta")
        private Klient klientKtoryRezerwuje;

        @Column
        @Id
        private String idRezerwacji;

        public Rezerwacja(Oferta ofertaKtoraJestZarezerwowana, Klient klientKtoryRezerwuje, String idRezerwacji) {
            this.ofertaKtoraJestZarezerwowana = ofertaKtoraJestZarezerwowana;
            this.klientKtoryRezerwuje = klientKtoryRezerwuje;
            this.idRezerwacji = idRezerwacji;
        }

        @Override
        public boolean equals(Object object){
            if (this == object)
                return true;
            if (object == null)
                return false;
            if (getClass() != object.getClass())
                return false;
            Rezerwacja doPorownania = (Rezerwacja) object;
            if(doPorownania.getKlientKtoryRezerwuje().equals(this.getKlientKtoryRezerwuje()) &&
            doPorownania.getOfertaKtoraJestZarezerwowana().equals(this.getOfertaKtoraJestZarezerwowana()) &&
            doPorownania.getIdRezerwacji().equals(this.getIdRezerwacji())) return true;
            return false;
        }

        public Oferta getOfertaKtoraJestZarezerwowana() {
            return ofertaKtoraJestZarezerwowana;
        }

        public void setOfertaKtoraJestZarezerwowana(Oferta ofertaKtoraJestZarezerwowana) {
            this.ofertaKtoraJestZarezerwowana = ofertaKtoraJestZarezerwowana;
        }

        public Klient getKlientKtoryRezerwuje() {
            return klientKtoryRezerwuje;
        }

        public void setKlientKtoryRezerwuje(Klient klientKtoryRezerwuje) {
            this.klientKtoryRezerwuje = klientKtoryRezerwuje;
        }

        public String getIdRezerwacji() {
            return idRezerwacji;
        }

        public void setIdRezerwacji(String idRezerwacji) {
            this.idRezerwacji = idRezerwacji;
        }
    }
