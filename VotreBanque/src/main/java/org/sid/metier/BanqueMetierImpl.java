package org.sid.metier;

import java.util.Date;
import java.util.List;
import javassist.NotFoundException;
import org.bean.CompteBean;
import org.bean.PersonBean;
import org.sid.dao.AgenceRepository;
import org.sid.dao.ClientRepository;
import org.sid.dao.CompteRepository;
import org.sid.dao.OperationRepository;
import org.sid.dao.PersonnelRepository;
import org.sid.dao.RoleRepository;
import org.sid.dao.UserRepository;
import org.sid.dao.Users_RoleRepository;
import org.sid.entities.Agence;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.CompteCourant;
import org.sid.entities.Operation;
import org.sid.entities.Personnel;
import org.sid.entities.Retrait;
import org.sid.entities.Users;
import org.sid.entities.UsersRoles;
import org.sid.entities.UsersRolesPK;
import org.sid.entities.Versement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BanqueMetierImpl implements IBanqueMetier {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonnelRepository personnelRepository;
    @Autowired
    private Users_RoleRepository users_RoleRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public String addClient(CompteBean compteBean, Agence agence, Personnel personnel) {
        Client client = new Client(compteBean.getNom(), compteBean.getEmail());
        clientRepository.save(client);
        compteRepository.insertcompte(getIdCompte(), "CC", new Date(), 0, 0, 0, client.getCode(), agence.getIdAgence(), personnel.getIdPersonnel());
        return getIdCompte();
    }

    @Override
    public void addPerson(PersonBean personBean, Agence agence) {
        Personnel personnel = new Personnel(personBean.getNom(), personBean.getPrenom(), personBean.getAdresse(), Double.parseDouble(personBean.getNum_tel()), agence);
        Personnel p = personnelRepository.save(personnel);
        Users users = new Users(personBean.getNum_tel(), bCryptPasswordEncoder.encode(personBean.getNom()), true, p);
        Users u = userRepository.save(users);
        UsersRoles usersRoles = new UsersRoles();
        UsersRolesPK usersRolesPK = new UsersRolesPK(personBean.getNum_tel(), "USER");
        usersRoles.setUsersRolesPK(usersRolesPK);
        usersRoles.setRoles(roleRepository.getOne("USER"));
        usersRoles.setUsers(u);
        users_RoleRepository.save(usersRoles);
    }

    public String getIdCompte() {
        List<Compte> comptes = compteRepository.findAll();
        String id = "";
        for (Compte compte : comptes) {
            id = compte.getCodeCompte();
        }
        return id.concat("1");
    }

    @Override
    public Personnel personnel(String username) {
        Users user = userRepository.getOne(username);
        return user.getIdPersonnel();
    }

    @Override
    public Compte consulterCompte(String codeCpte) throws NotFoundException {
        java.util.Optional<Compte> cp = compteRepository.findById(codeCpte);
        return cp.orElseThrow(
                () -> new NotFoundException("Unable to get Account with Code =  " + codeCpte));
    }

    @Override
    public void verser(String codeCpte, double montant, Personnel personnel) throws NotFoundException {
        Compte cp = consulterCompte(codeCpte);
        Versement v = new Versement(new Date(), montant, cp, personnel);
        operationRepository.save(v);
        cp.setSolde(cp.getSolde() + montant);
        compteRepository.save(cp);

    }

    @Override
    public void retirer(String codeCpte, double montant, Personnel personnel) throws NotFoundException {
        Compte cp = consulterCompte(codeCpte);
        double facilitesCaisse = 0;
        if (cp instanceof CompteCourant) {
            facilitesCaisse = ((CompteCourant) cp).getDecouvert();
        }
        if (cp.getSolde() + facilitesCaisse < montant) {
            throw new RuntimeException("Solde insuffisant");
        }
        Retrait r = new Retrait(new Date(), montant, cp, personnel);
        operationRepository.save(r);
        cp.setSolde(cp.getSolde() - montant);
        compteRepository.save(cp);

    }

    @Override
    public void virement(String codeCpte1, String codeCpte2, double montant, Personnel personnel) throws NotFoundException {
        if (codeCpte1.equals(codeCpte2)) {
            throw new RuntimeException("Impossibile de faire un virement sur le même compte");
        }
        retirer(codeCpte1, montant, personnel);
        verser(codeCpte2, montant, personnel);

    }

    @Override
    @SuppressWarnings("deprecation")
    public Page<Client> clients(int page, int size) {
        return clientRepository.findAll(new PageRequest(page, size));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Page<Operation> listOperation(String codeCpte, int page, int size) {

        return operationRepository.listOperation(codeCpte, new PageRequest(page, size));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Page<Users> listUsers(int page, int size) {

        return userRepository.findAll(new PageRequest(page, size));
    }

    @Override
    @SuppressWarnings("deprecation")
    public Page<Agence> listAgence(int page, int size) {

        return agenceRepository.findAll(new PageRequest(page, size));
    }

}
