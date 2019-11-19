package apap.tugasakhir.siruangan.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/pengadaan-fasilitas/**").hasAnyAuthority("Admin TU","Guru")
                .antMatchers("/fasilitas/**").hasAnyAuthority("ROLE_Admin TU")
                .antMatchers("/ruangan/peminjaman/**").hasAnyAuthority("ROLE_Guru","ROLE_Siswa")
                .antMatchers("/ruangan/daftar-peminjaman-ruangan/**").hasAnyAuthority("ROLE_Admin TU","ROLE_Guru","ROLE_Siswa")
                .antMatchers("/ruangan/status-peminjaman/**").hasAnyAuthority("ROLE_Admin TU")

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configureGlobal (AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("admin").password(encoder().encode("admin"))
                .roles("Admin TU");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("kepsek").password(encoder().encode("kepsek"))
                .roles("Kepala Sekolah");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("guru").password(encoder().encode("guru"))
                .roles("Guru");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("siswa").password(encoder().encode("siswa"))
                .roles("Siswa");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("pustakawan").password(encoder().encode("pustakawan"))
                .roles("Pustakawan");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("pengurusKoperasi").password(encoder().encode("pengurusKoperasi"))
                .roles("Pengurus Koperasi");
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("anggotaKoperasi").password(encoder().encode("anggotaKoperasi"))
                .roles("Anggota Koperasi");
                
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }
}
