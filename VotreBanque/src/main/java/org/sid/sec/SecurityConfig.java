package org.sid.sec;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.support.RegistrationPolicy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //pour definir la maniere dont on va chercher les utilisateurs
/*		auth.inMemoryAuthentication()
			.withUser("admin").password("1234").roles("ADMIN","USER");
		auth.inMemoryAuthentication()
			.withUser("user").password("1234").roles("USER"); */

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .rolePrefix("ROLE_")
                .passwordEncoder(bCryptPasswordEncoder);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // pour definir les strategies de securités,, les regles
        //on demande a Spring qu'on a besoin de passer par une authentification basée par un formulaire
        http.formLogin().loginPage("/login");
        //securiser les ressources de l'appli
        http.authorizeRequests().antMatchers("/operations", "/consulterCompte").hasRole("USER");
        http.authorizeRequests().antMatchers("/saveOperation", "/administration", "/newcompte").hasRole("ADMIN");

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**");
    }
}
