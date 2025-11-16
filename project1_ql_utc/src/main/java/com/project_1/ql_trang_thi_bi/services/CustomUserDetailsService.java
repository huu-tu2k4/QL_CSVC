package com.project_1.ql_trang_thi_bi.services;

import com.project_1.ql_trang_thi_bi.models.NguoiDung;
import com.project_1.ql_trang_thi_bi.repositorys.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        NguoiDung nguoiDung = nguoiDungRepository.findByTenDangNhap(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Set<GrantedAuthority> authorities = nguoiDung.getVaiTro().getPermissions().stream()
                .map(rp -> new SimpleGrantedAuthority(rp.getCode()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                nguoiDung.getTenDangNhap(),
                nguoiDung.getMatKhau(),
                authorities
        );
    }
}
