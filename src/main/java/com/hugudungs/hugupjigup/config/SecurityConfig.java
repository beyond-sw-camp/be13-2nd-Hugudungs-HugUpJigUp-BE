package com.hugudungs.hugupjigup.config;

import com.hugudungs.hugupjigup.auth.jwt.JwtAuthenticationFilter;
import com.hugudungs.hugupjigup.auth.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests(authorize ->
//                        authorize.requestMatchers(
//                                "/v1/api-docs/**",
//                                "/swagger-ui/**",
//                                "/api/v1/docs/swagger*/**",
//                                "/swagger-resources/**")
//                                    .permitAll()
//                                    .anyRequest()
//                                    .authenticated())
//                .csrf(AbstractHttpConfigurer::disable)
//                .cors(Customizer.withDefaults())
        http
                // 모든 요청에 대한 접근 제어 설정
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/api-docs/**",          // Swagger 관련 경로 허용
                                "/swagger-ui/**",        // Swagger UI 경로 허용
                                "/v3/api-docs/**",       // OpenAPI 문서 경로 허용
                                "/swagger-resources/**"  // Swagger 리소스 허용
                        ).permitAll()               // 위 경로는 모두 허용
                        .anyRequest().permitAll()   // 나머지 요청도 모두 허용 (개발 단계에서)
                )
                .csrf(csrf -> csrf.disable())   // CSRF 보호 비활성화 (Swagger 테스트를 위해)
                .cors(Customizer.withDefaults()) // CORS 설정 기본값 사용
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(
                        new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}