package com.ohgiraffers.intranet.member.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.util.Collection;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements UserDetails {

    private int mem_num;
    private String mem_id;
    private String mem_pw;
    private String mem_name;
    private String mem_phone;
    private java.sql.Date mem_joinDate;
    private java.sql.Date mem_birth;
    private String mem_email;
    private String mem_address;
    private String dept_rank;
    private String dept_code;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {


        return null;
    }

    public String getMem_address() {
		return mem_address;
	}

	public void setMem_address(String mem_address) {
		this.mem_address = mem_address;
	}

	
	public String getMem_pw() {
		return mem_pw;
	}

	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}

	@Override
    public String getPassword() {
        return mem_pw;
    }

    @Override
    public String getUsername() {
        return mem_id;
    }

    // 계정 만료 여부 -> true. 만료되지 않음.
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 -> true. 잠기지 않음.
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호 만료 여부 -> true. 만료되지 않음.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        return true;
    }
}

