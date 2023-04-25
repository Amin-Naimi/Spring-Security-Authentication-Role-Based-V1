package com.Mohamed.jwtAauthentification.configuration;

import com.Mohamed.jwtAauthentification.jwt.JwtRequestFiltre;
import com.Mohamed.jwtAauthentification.services.auth.AuthentificationUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtRequestFiltre authenticationJwtTokenFilter() {
        return new JwtRequestFiltre();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    //@Override
protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            .antMatchers(
                    "/**/authenticate/login",
                    "/**/api/users/create",
                    "/v2/api-docs",
                    "/swagger-resources",
                    "/swagger-resources/**",
                    "/configuration/ui",
                    "/configuration/security",
                    "/swagger-ui.html",
                    "/webjars/**",
                    "/v3/api-docs/**",
                    "/swagger-ui/**")
            .permitAll()
            .anyRequest().authenticated();
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

}
    @Bean
    public UserDetailsService userDetailsService() {
        return new AuthentificationUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

}
/*
Un Provider en informatique est une interface qui fournit des fonctionnalités à une application.
Dans le contexte de la sécurité de l'application, un authenticationProvider est un objet qui fournit des
informations d'authentification et d'autorisation à l'application. Il existe différents types de authenticationProvider
dans Spring Security, notamment DaoAuthenticationProvider, LdapAuthenticationProvider, OpenIDAuthenticationProvider,
JaasAuthenticationProvider, etc. Chacun de ces authenticationProvider a un but spécifique et peut être utilisé en fonction
des besoins de l'application
En résumé, les Provider sont responsables de la récupération des informations d'authentification et d'autorisation
 à partir d'une source de données, tandis que les Manager gèrent le processus d'authentification et d'autorisation
  en utilisant les Provider pour vérifier les informations d'identification des utilisateurs..*/


/**
 * .and().formLogin() configure la méthode d'authentification par formulaire. Cela signifie que lorsque l'utilisateur tente
 * d'accéder à une page ou une ressource protégée, il sera redirigé vers une page de connexion personnalisée où il devra
 * entrer ses informations d'identification (nom d'utilisateur et mot de passe) pour accéder à la ressource protégée.
 * L'authentification par formulaire est généralement utilisée pour les applications web.
 * <p>
 * <p>
 * .and().httpBasic() configure la méthode d'authentification de base HTTP. Cela signifie que lorsque l'utilisateur tente
 * d'accéder à une page ou une ressource protégée, il sera invité par le navigateur à entrer ses informations d'identification
 * (nom d'utilisateur et mot de passe). L'authentification de base HTTP est utilisée pour les applications qui n'utilisent pas
 * de formulaire pour l'authentification, mais plutôt l'envoi des informations d'identification via des en-têtes HTTP.
 **/


//@Override
//protected void configure(HttpSecurity http) throws Exception {
//    http.csrf().disable();
//    http.authorizeRequests()
//            .antMatchers("/**/api/users/all").hasAuthority("ADMIN")
//            .anyRequest().authenticated()
//            .and().formLogin()
//            .and().httpBasic();
//
//}