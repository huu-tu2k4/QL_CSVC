package com.project_1.ql_trang_thi_bi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vai_tro")
public class VaiTro {

    @Id
    @Column(name = "ma_vai_tro")
    private String maVaiTro;

    @Column(name = "ma_code")
    private String maCode;

    @Column(name = "ten_vai_tro", nullable = false)
    private String tenVaiTro;

    @Column(name = "mo_ta")
    private String moTa;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "vai_tro_permission",
            joinColumns = @JoinColumn(name = "ma_vai_tro"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    private Set<Permission> permissions;
}
