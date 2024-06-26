package com.tutorial.persistence.entity;


import org.hibernate.annotations.Comment;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "users")
@Comment("使用者資料表")
public class UserEntity {

  //@GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Comment("ID")
  private Long id;

  @Comment("帳號")
  private String userName;

  @Comment("密碼")
  private String password;

  @Comment("權限")
  private String authority;

  @Comment("是否過期")
  @Column(columnDefinition = "boolean default true")
  private Boolean expired;

  @Comment("是否鎖定")
  @Column(columnDefinition = "boolean default true")
  private Boolean locked;

  @Comment("是否驗簽過期")
  @Column(nullable = false, columnDefinition = "boolean default true")
  private Boolean credentialsExpired;

  @Comment("是否鎖定")
  @Column(columnDefinition = "boolean default true")
  private Boolean enabled;

  @Comment("試用過期日")
  private LocalDate expiredAt;

  @Comment("建立者ID")
  private Long creator;

}
