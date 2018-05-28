package org.sid.metier;

import javassist.NotFoundException;
import org.bean.CompteBean;
import org.bean.PersonBean;
import org.sid.entities.Agence;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Personnel;
import org.sid.entities.Users;
import org.springframework.data.domain.Page;

public interface IBanqueMetier {

    public void addPerson(PersonBean personBean, Agence agence);

    public String addClient(CompteBean compteBean, Agence agence, Personnel personnel);

    public Personnel personnel(String username);

    public Page<Client> clients(int page, int size);

    public Compte consulterCompte(String codeCpte) throws NotFoundException;

    public void verser(String codeCpte, double montant, Personnel personnel) throws NotFoundException;

    public void retirer(String codeCpte, double montant, Personnel personnel) throws NotFoundException;

    public void virement(String codeCpte1, String codeCpte2, double montant, Personnel personnel) throws NotFoundException;

    public Page<Operation> listOperation(String codeCpte, int page, int size) throws NotFoundException;

    public Page<Users> listUsers(int page, int size);

    public Page<Agence> listAgence(int page, int size);

}
