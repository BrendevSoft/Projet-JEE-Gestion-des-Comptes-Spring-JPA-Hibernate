package org.sid.web;

import org.bean.AgenceBean;
import org.bean.CompteBean;
import org.bean.PersonBean;
import org.sid.entities.Agence;
import org.sid.entities.Client;
import org.sid.entities.Compte;
import org.sid.entities.Operation;
import org.sid.entities.Personnel;
import org.sid.entities.Users;
import org.sid.metier.IBanqueMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BanqueController {

    @Autowired
    private IBanqueMetier banqueMetier;

    @RequestMapping("/operations")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size) {
        Page<Client> clients = banqueMetier.clients(page, size);
        model.addAttribute("clients", clients.getContent());
        int[] pages1 = new int[clients.getTotalPages()];
        model.addAttribute("pages1", pages1);
        CompteBean form = new CompteBean();
        model.addAttribute("compteBean", form);
        return "comptes";
    }

    @RequestMapping("/administration")
    public String utilisateurs(Model model, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size) {

        //tableau de personnel
        Page<Users> userses = banqueMetier.listUsers(page, size);
        model.addAttribute("users", userses.getContent());
        int[] pages = new int[userses.getTotalPages()];
        model.addAttribute("pages", pages);

        //formulaire du personnel
        PersonBean form = new PersonBean();
        model.addAttribute("personBean", form);

        //tableau de agence
        Page<Agence> agences = banqueMetier.listAgence(page, size);
        model.addAttribute("agences", agences.getContent());
        int[] pages1 = new int[agences.getTotalPages()];
        model.addAttribute("pages1", pages1);

        //formulaire de agence
        AgenceBean form1 = new AgenceBean();
        model.addAttribute("agenceBean", form1);
        return "administration";
    }

    /**
     * This method will provide the medium to add a new user.
     *
     * @param model
     * @param personBean
     * @param result
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = {"/newperson"}, method = RequestMethod.POST)
    public String newCompte(Model model, @ModelAttribute("personBean") @Validated PersonBean personBean,
            BindingResult result, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size) {
        if (result.hasErrors()) {
            return "redirect:/operations";
        } else {
            banqueMetier.addPerson(personBean, getPersonnel().getIdAgence());
            //liste des clients
            Page<Users> userses = banqueMetier.listUsers(page, size);
            model.addAttribute("personnels", userses.getContent());
            int[] pages = new int[userses.getTotalPages()];
            model.addAttribute("pages", pages);
        }

        return "redirect:/personnels";
    }

    @RequestMapping(value = {"/newcompte"}, method = RequestMethod.POST)
    public String newCompte(Model model, @ModelAttribute("compteBean") @Validated CompteBean compteBean,
            BindingResult result, @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size) {
        if (result.hasErrors()) {
            return "redirect:/operations";
        } else {
            String codeCompte = banqueMetier.addClient(compteBean, getPersonnel().getIdAgence(), getPersonnel());
            model.addAttribute("codeCompte", codeCompte);
            //liste des clients
            Page<Client> clients = banqueMetier.clients(page, size);
            model.addAttribute("clients", clients.getContent());
            int[] pages1 = new int[clients.getTotalPages()];
            model.addAttribute("pages1", pages1);
        }

        return "redirect:/operations";
    }

    @RequestMapping("/consulterCompte")
    public String consulter(Model model, String codeCompte,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "4") int size) {
        model.addAttribute("codeCompte", codeCompte);
        try {
            Compte cp = banqueMetier.consulterCompte(codeCompte);
            model.addAttribute("compte", cp);

            //liste des opérations
            Page<Operation> pageOperations = banqueMetier.listOperation(codeCompte, page, size);
            model.addAttribute("listOperations", pageOperations.getContent());
            int[] pages = new int[pageOperations.getTotalPages()];
            model.addAttribute("pages", pages);

            //liste des clients
            Page<Client> clients = banqueMetier.clients(page, size);
            model.addAttribute("clients", clients.getContent());
            int[] pages1 = new int[clients.getTotalPages()];
            model.addAttribute("pages1", pages1);

            //Element du formulaire à valider
            CompteBean form = new CompteBean();
            model.addAttribute("compteBean", form);
        } catch (Exception e) {
            //Element du formulaire à valider
            CompteBean form = new CompteBean();
            model.addAttribute("compteBean", form);
            model.addAttribute("exception", e);
        }
        return "comptes";
    }

    @RequestMapping(value = "/saveOperation", method = RequestMethod.POST)
    public String saveOperation(Model model,
            String typeOperation, String codeCompte,
            double montant, String codeCompte2) {
        try {
            if (typeOperation.equals("VERS")) {
                banqueMetier.verser(codeCompte, montant, getPersonnel());
            } else if (typeOperation.equals("RETR")) {
                banqueMetier.retirer(codeCompte, montant, getPersonnel());
            }
            if (typeOperation.equals("VIR")) {
                banqueMetier.virement(codeCompte, codeCompte2, montant, getPersonnel());
            }
            //Element du formulaire à valider

        } catch (Exception e) {
            model.addAttribute("error", e);
            CompteBean form = new CompteBean();
            model.addAttribute("compteBean", form);
            return "redirect:/consulterCompte?codeCompte=" + codeCompte
                    + "&error=" + e.getMessage();
        }
        CompteBean form = new CompteBean();
        model.addAttribute("compteBean", form);
        return "redirect:/consulterCompte?codeCompte=" + codeCompte;
    }

    private Personnel getPersonnel() {
        return banqueMetier.personnel(getPrincipal());
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
