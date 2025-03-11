package com.hugudungs.hugupjigup.data.entity.user;

import com.hugudungs.hugupjigup.data.entity.common.BaseEntity;
import com.hugudungs.hugupjigup.data.entity.enums.Gender;
import com.hugudungs.hugupjigup.data.entity.enums.ProfileType;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_profile")
@AttributeOverride(name = "id", column = @Column(name = "profile_id"))
public class User_profile extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User userId;

    @Column(name = "current_job")
    private String currentJob;

    @Column(name = "desired_job")
    private String  desiredJob;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "experience", columnDefinition = "TEXT")
    private String experience;

    @Column(name = "rate")
    private float rate;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_type")
    private ProfileType profileType;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
}
